package pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Administrator;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.User;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyConstraintViolationException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyEntityExistsException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyEntityNotFoundException;

import javax.ejb.Stateless;
import javax.validation.ConstraintViolationException;

@Stateless
public class UserBean<E extends User, PK extends String> extends BaseBean<E, String>{
    public UserBean() {
        super((Class<E>)User.class );
    }

    public UserBean(Class<E> entityClass) {
        super(entityClass);

    }

    //CREATE
    public E create(E entity) throws MyConstraintViolationException, MyEntityExistsException, MyEntityNotFoundException
    {
        if(find(entity.getUsername()) != null) {
            throw new MyEntityExistsException("Administrator with username: " + entity.getUsername() + "already exists");
        }

        try {
            super.create(entity);
            return findOrFail(entity.getUsername());

        }catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(e);
        }
    }

    //UPDATE
    public E edit(E entityIn) throws MyConstraintViolationException, MyEntityNotFoundException {
        DtosMapper<E, E> mapper = new DtosMapper<>(entityClass);
        E entityToEdit  = findOrFail(entityIn.getUsername());

        try {
            super.edit(mapper.getMappedEntity(entityIn));
            return findOrFail(entityIn.getUsername());
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(e);
        }
    }
}
