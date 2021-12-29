package pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs;


import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Auth;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.User;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyEntityExistsException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyEntityNotFoundException;

import javax.ejb.Stateless;

@Stateless
public class AuthBean extends BaseBean<Auth, String> {

    @Override
    public void preCreate(Auth auth) throws MyEntityExistsException {
        if(find(auth.getUsername()) != null) {
            throw new MyEntityExistsException("Username already registred");
        }
    }

    @Override
    public Auth update(Auth auth) {
        //TODO: UPDATE À PASSWORD APENAS
        return auth;
    }

    public String generateToken() {
        //TODO
        return null;
    }




    @Override
    public void destroy(String username) throws MyEntityNotFoundException {
        //ONLY SOFDELETE USER
        Auth user = findOrFail(username);

        user.setIsActive(false);
        em.merge(user);

    }


    public Auth authenticate(final String username, final String password) throws Exception {

        Auth auth = findOrFail(username);
        System.out.println(auth.getPassword());
        if (auth != null && auth.getPassword().equals(Auth.hashPassword(password))) {
            return auth;
        }
        throw new Exception("Failed logging in with username '" + username + "':unknown username or wrong password");
    }








}
