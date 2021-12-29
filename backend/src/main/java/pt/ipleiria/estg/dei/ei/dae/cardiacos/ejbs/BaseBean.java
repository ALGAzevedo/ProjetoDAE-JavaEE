package pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs;


import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.BaseEntity;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.*;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.utils.TypeResolver;

import javax.annotation.PostConstruct;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;

import java.util.List;

public abstract class BaseBean<E extends BaseEntity, PK> {

    @PersistenceContext
    protected EntityManager em;

    protected Class<E> entityClass;

    @PostConstruct
    private void instantiateClasses() {
        entityClass = TypeResolver.getGenericClazz(this, 0);
    }

    public Class<E> getEntityClass() {
        return entityClass;
    }


    public E find(PK primaryKey) {
        return em.find(entityClass, primaryKey);
    }

    public E findOrFail(PK primaryKey) throws MyEntityNotFoundException {
        var entity = find(primaryKey);

        if (entity == null) {
            throw new MyEntityNotFoundException(
                    entityClass.getSimpleName() + " with primary key '" + primaryKey + "' not found."
            );
        }

        return entity;
    }

    public List<E> all() {
        return em.createNamedQuery("getAll" + entityClass.getSimpleName() + "s", entityClass).getResultList();
    }

    public void preCreate(E entity) throws MyEntityExistsException, MyUniqueConstraintViolationException, MyEntityNotFoundException,MyIllegalArgumentException {

    }
    public void postCreate(E entity) throws MyEntityNotFoundException, MyConstraintViolationException {

    }

    public E create(E entity) throws MyConstraintViolationException, MyEntityExistsException, MyUniqueConstraintViolationException, MyEntityNotFoundException, MyIllegalArgumentException {
        preCreate(entity);
        try {
            em.persist(entity);
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(e);
        }
        postCreate(entity);
        return entity;
    }

    public void preUpdate(E entity) {}
    public void postUpdate(E entity) {}

    public E update(E entity) throws MyConstraintViolationException {

        try {
            preUpdate(entity);
            em.merge(entity);
            em.flush();
            postUpdate(entity);
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(e);
        }


        return entity;
    }
    public void preDestroy(E entity) {}
    public void postDestroy(E entity) {}

    public void destroy(PK primaryKey) throws MyEntityNotFoundException, MyConstraintViolationException {
        var entity = findOrFail(primaryKey);
        preDestroy(entity);
        em.remove(entity);
        postDestroy(entity);
    }

}