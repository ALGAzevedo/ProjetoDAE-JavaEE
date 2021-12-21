package pt.ipleiria.estg.dei.ei.dae.CardiacosBackend.ejbs;

import org.modelmapper.ModelMapper;
import pt.ipleiria.estg.dei.ei.dae.CardiacosBackend.exceptions.MyConstraintViolationException;
import pt.ipleiria.estg.dei.ei.dae.CardiacosBackend.exceptions.MyEntityExistsException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.NotFoundException;
import java.util.List;

public abstract class BaseBean<E, PK> {

    @PersistenceContext
    protected EntityManager em;

    private Class<E> entityClass;

    public BaseBean(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    public ModelMapper getModelBuilder() {
        return new ModelMapper();
    }

    public E find(PK primaryKey) {
        return em.find(entityClass, primaryKey);
    }

    public E findOrFail(PK primaryKey) {
        var entity = find(primaryKey);

        if (entity == null) {
            throw new NotFoundException(
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

    public E create(E entity) throws MyEntityExistsException, MyConstraintViolationException {
        entity = preCreate(entity);

        em.persist(entity);
        return postCreate(entity);
    }
}