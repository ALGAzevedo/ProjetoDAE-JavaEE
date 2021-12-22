package pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs;

import org.modelmapper.ModelMapper;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyConstraintViolationException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyEntityExistsException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyEntityNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.NotFoundException;
import java.util.List;

public abstract class BaseBean<E, PK> {

    @PersistenceContext
    protected EntityManager em;

    protected Class<E> entityClass;

    public BaseBean(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    public ModelMapper getModelBuilder() {
        return new ModelMapper();
    }

    public E find(PK primaryKey) {
        return em.find(entityClass, primaryKey);
    }

    public E findOrFail(PK primaryKey) throws MyEntityNotFoundException {
        var entity = find(primaryKey);

        if (entity == null) {
            throw new MyEntityNotFoundException(
                    entityClass.getSimpleName() + "with primary key '" + primaryKey + "' not found."
            );
        }

        return entity;
    }

    public List<E> all() {
        return em.createNamedQuery("getAll" + entityClass.getSimpleName() + "s", entityClass).getResultList();
    }

    public E preCreate(E entity) {
        return entity;
    }

    public E postCreate(E entity) {
        return entity;
    }

    public E create(E entity) throws MyEntityExistsException, MyConstraintViolationException, MyEntityNotFoundException {
        entity = preCreate(entity);

        em.persist(entity);
        return postCreate(entity);
    }
    public E edit(E entity) throws MyConstraintViolationException, MyEntityNotFoundException {
        em.merge(entity);
        return postCreate(entity);
    }
    public void remove(E entity) {
        em.remove(entity);
    }

}