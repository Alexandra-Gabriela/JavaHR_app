package metamodel;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public abstract class AbstractRepository {
    private EntityManagerFactory emf;
    private EntityManager em;

    public AbstractRepository() {
        this.emf = Persistence.createEntityManagerFactory("AplicatieHR");
        this.em = emf.createEntityManager();
    }

    public void beginTransaction() {
        em.getTransaction().begin();
    }

    public void commitTransaction() {
        em.getTransaction().commit();
    }

    public void rollbackTransaction() {
        em.getTransaction().rollback();
    }

    public Object create(Object o) {
        em.persist(o);
        return o;
    }

    public Object update(Object o) {
        return em.merge(o);
    }

    public void delete(Object o) {
        em.remove(o);
    }

    public EntityManager getEm() {
        return em;
    }

    public void close() {
        if (em.isOpen()) {
            em.close();
        }
        if (emf.isOpen()) {
            emf.close();
        }
    }
}
