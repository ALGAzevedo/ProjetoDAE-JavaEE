package pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs;


import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.PasswordCreateDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.*;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.*;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.UUID;

@Stateless
public class AuthBean extends BaseBean<Auth, String> {

    @EJB
    private UserBean userBean;

    @Override
    public void preCreate(Auth auth) throws MyEntityExistsException {
        if (find(auth.getUsername()) != null) {
            throw new MyEntityExistsException("Username already registred");
        }
    }

    public Auth addUser(Auth auth, User user) throws MyConstraintViolationException, MyEntityNotFoundException, MyEntityExistsException, MyUniqueConstraintViolationException, MyIllegalArgumentException {
        Auth authToReturn = auth;

        if(user instanceof Administrator)
            authToReturn = create(new AuthAdministrator(auth.getUsername(), auth.getToken()));
        if(user instanceof Patient)
            authToReturn = create(new AuthPatient(auth.getUsername(), auth.getToken()));
        if(user instanceof HealthcareProfessional)
            authToReturn = create(new AuthHealthcareProfessional(auth.getUsername(), auth.getToken()));

        return authToReturn;
    }




    @Override
    public void destroy(String username) throws MyEntityNotFoundException {
        //ONLY SOFDELETE USER
        Auth user = findOrFail(username);

        user.setActive(false);
        em.merge(user);

    }
    public Auth confirmToken(PasswordCreateDTO passDTO) throws Exception {

        Auth auth = findByToken(passDTO.getToken());
        if (auth != null && !auth.isExpired()){

            if (!passDTO.getPassword().equals(passDTO.getConfirmPassword())) {
                throw new Exception("Password and confirm password do not match");
            }
            //TODO: Update password on Patient table  or patient password in Auth table  only
            try {
                auth.setPassword(passDTO.getPassword());
                auth.setActive(true);
                auth.setExpired(true);
                update(auth);
                return auth;
            }catch (Exception e){
                throw new Exception("Unable to update Patient password!");
            }
        }
        throw new Exception("Invalid token");
    }

    public Auth newPasswordToken(String username) throws Exception {
        Auth user = findOrFail(username);

        try {
            //TODO: Continua a fazer login caso n pretenda alterar a password, ou podemos obrigar
            //user.setPassword(null);
            //user.setActive(false);
            user.setExpired(false);
            user.setToken(generateToken());
            update(user);
            return user;
        }catch (Exception e){
            throw new Exception("Failed to generate user new password token!");
        }

    }

    public Auth authenticate(final String username, final String password) throws Exception {

        Auth auth = findOrFail(username);
        if (auth != null && auth.isActive()) {
            if (auth.getPassword().equals(Auth.hashPassword(password))){
                System.out.println("Provided password: "+ auth.getPassword());
                return auth;
            }
        }
        throw new Exception("Failed logging in with username '" + username + "':unknown username or wrong password");
    }


    public String generateToken(){
        return UUID.randomUUID().toString();
    }

    public User userMe(String subject) throws MyEntityNotFoundException {
        //userBean is generic,we cant use it here, thats way we used em
        User usr = em.find(User.class, subject);
        if(usr == null) {
            throw new MyEntityNotFoundException("User not found");
        }
        return usr;


    }


    public Auth findByToken(String token) {
        try {
            Query query = em.createNamedQuery("findToken");
            query.setParameter("token", token);
            return (Auth) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
