package pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.Email;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;

@Stateless(name = "EmailEJB")
public class EmailBean {
    @Resource(name = "java:/jboss/mail/fakeSMTP")
    private Session mailSession;
    private static final Logger logger = Logger.getLogger("EmailBean.logger");
    public void send(String to, String subject, String name, String link) {
        Thread emailJob = new Thread(() -> {
            Message message = new MimeMessage(mailSession);
            Date timestamp = new Date();
            try {
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
                message.setSubject(subject);
                message.setContent(buildEmail(name, link),
                        "text/html");
                message.setSentDate(timestamp);
                Transport.send(message);
                logger.info("Sent message successfully....");
            } catch (MessagingException e) {
                logger.log(Level.SEVERE, e.getMessage());
            }
        });
        emailJob.start();
    }
    private String buildEmail(String name, String link) {
        return "<div style=\"font-family:Arial,sans-serif;font-size:16px;\">\n" +
                "<p>Hi " + name + ",</p>" +
                "<p> Thank you for registering. Please click on the below link to activate your account: </p>" +
                "<blockquote style=\" margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\">" +
                "<p> " +
                "<a style=\"line-height:1.5;text-decoration:none;word-break:break-word;font-weight:700;display:block;font-family:Arial,sans-serif;font-size:16px;\"  href=\"" + link + "\">Confirm Now</a> </p>" +
                "</blockquote>" +
                "<small>If you did not request to verify this email address, please disregard this message.</small>"+
                "<p>Best<br>" +
                "Cardiacos Team.</p>" +
                "</div>";
    }
}