package pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs;


import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.PasswordCreateDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Auth;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyEntityExistsException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyEntityNotFoundException;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

@Stateless
public class AuthBean extends BaseBean<Auth, String> {

    @Override
    public void preCreate(Auth auth) throws MyEntityExistsException {
        if (find(auth.getUsername()) != null) {
            throw new MyEntityExistsException("Username already registred");
        }
    }

    @Override
    public void destroy(String username) throws MyEntityNotFoundException {
        //ONLY SOFDELETE USER
        Auth user = findOrFail(username);

        user.setActive(false);
        em.merge(user);

    }
    public Auth confirmToken(String token, PasswordCreateDTO passDTO) throws Exception {

        Auth auth = findByToken(token);
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



    public Auth authenticate(final String username, final String password) throws Exception {

        Auth auth = findOrFail(username);
        System.out.println(auth.getPassword());
        if (auth != null && auth.getPassword().equals(Auth.hashPassword(password))) {
            return auth;
        }
        throw new Exception("Failed logging in with username '" + username + "':unknown username or wrong password");
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
