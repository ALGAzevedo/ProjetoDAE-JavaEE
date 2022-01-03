package pt.ipleiria.estg.dei.ei.dae.cardiacos.ws;

import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.BiomedicalIndicators.DocumentDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.DocumentBean;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.PatientBean;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Document;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Patient;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyEntityNotFoundException;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Path("documents")
public class DocumentsService {
    @EJB
    private PatientBean patientBean;
    @EJB
    private DocumentBean documentBean;
    @POST
    @Path("upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response upload(MultipartFormDataInput input) throws MyEntityNotFoundException,
            IOException {
        Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
        // Get file data to save
        String username = uploadForm.get("username").get(0).getBodyAsString();
        Patient patient = patientBean.find(username);
        if(patient == null) {
            throw new MyEntityNotFoundException("Patient with username " + username + " not found.");
        }
        List<InputPart> inputParts = uploadForm.get("file");
        for (InputPart inputPart : inputParts) {
            try {
                MultivaluedMap<String, String> header = inputPart.getHeaders();
                String filename = getFilename(header);
                // convert the uploaded file to inputstream
                InputStream inputStream = inputPart.getBody(InputStream.class, null);
                byte[] bytes = IOUtils.toByteArray(inputStream);
                String path = System.getProperty("user.home") + File.separator + "uploads";
                File customDir = new File(path);
                if (!customDir.exists()) {
                    customDir.mkdir();
                }
                String filepath = customDir.getCanonicalPath() + File.separator + filename;
                writeFile(bytes, filepath);
                documentBean.create(new Document(filename,path, patient, LocalDate.now()));
                return Response.status(200).entity("Uploaded file name : " +
                        filename).build();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    @GET
    @Path("download/{id}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response download(@PathParam("id") Long id) throws MyEntityNotFoundException {
        Document document = documentBean.findDocument(id);
        File fileDownload = new File(document.getFilepath() + File.separator +
                document.getFilename());
        Response.ResponseBuilder response = Response.ok((Object) fileDownload);
        response.header("Content-Disposition", "attachment;filename=" +
                document.getFilename());
        return response.build();
    }
    @GET
    @Path("{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<DocumentDTO> getDocuments(@PathParam("username") String username) throws
            MyEntityNotFoundException {
        Patient patient = patientBean.find(username);
        if(patient == null) {
            throw new MyEntityNotFoundException("Student with username " + username + " not found.");
        }
        return documentsToDTOs(documentBean.getAllPatientDocuments(username));
    }
    @GET
    @Path("{username}/exists")
    public Response hasDocuments(@PathParam("username") String username) throws
            MyEntityNotFoundException {
        Patient patient = patientBean.find(username);
        if(patient == null) {
            throw new MyEntityNotFoundException("Patient with username " + username + " not found.");
        }
        return Response.status(Response.Status.OK).entity(new Boolean(!patient.getDocuments().isEmpty())).build();
    }
    DocumentDTO toDTO(Document document) {
        return new DocumentDTO(
                document.getId(),
                document.getFilename(),
                document.getFilepath(),
                document.getDate());
    }
    List<DocumentDTO> documentsToDTOs(List<Document> documents) {
        return documents.stream().map(this::toDTO).collect(Collectors.toList());
    }
    private String getFilename(MultivaluedMap<String, String> header) {
        String[] contentDisposition = header.getFirst("Content-Disposition").split(";");
        for (String filename : contentDisposition) {
            if ((filename.trim().startsWith("filename"))) {
                String[] name = filename.split("=");
                String finalFileName = name[1].trim().replaceAll("\"", "");
                return finalFileName;
            }
        }
        return "unknown";
    }
    private void writeFile(byte[] content, String filename) throws IOException {
        File file = new File(filename);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream fop = new FileOutputStream(file);
        fop.write(content);
        fop.flush();
        fop.close();
        System.out.println("Written: " + filename);
    }
}