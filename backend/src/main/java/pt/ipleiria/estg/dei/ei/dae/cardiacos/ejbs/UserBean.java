package pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.User;

import javax.ejb.Stateless;

@Stateless
public class UserBean extends BaseBean<User, Long>{
    public UserBean() {
        super(User.class);
    }
}
