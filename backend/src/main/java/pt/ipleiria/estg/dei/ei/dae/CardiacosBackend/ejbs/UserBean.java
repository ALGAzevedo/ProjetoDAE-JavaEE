package pt.ipleiria.estg.dei.ei.dae.CardiacosBackend.ejbs;

import pt.ipleiria.estg.dei.ei.dae.CardiacosBackend.entities.User;

import javax.ejb.Stateless;

@Stateless
public class UserBean extends BaseBean<User, Long>{
    public UserBean() {
        super(User.class);
    }
}
