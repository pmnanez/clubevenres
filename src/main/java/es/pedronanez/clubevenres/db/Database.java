package es.pedronanez.clubevenres.db;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 * Database management singleton class
 * 
 *
 */
public final class Database {

    /**
     * Creates entity managers.
     */
    private final transient EntityManagerFactory emf;

    /**
     * Current instance of the Singleton
     */
    private static Database instance;

    /**
     * Private constructor for singleton
     */
    private Database() {
        emf = Persistence.createEntityManagerFactory("clubevenres");
    }

    /**
     * Starts the database
     */
    protected static void initInstance() {
        if (instance == null) {
            instance = new Database();
        }
    }

    /**
     * Ends with the database.
     */
    protected static void closeConnection() {
        instance.getEntityManagerFactory().close();
    }

    /**
     * Stores a new object in the database.
     * 
     * @param object
     *            Object to persist.
     * @return The persisted object with the generated id.
     */
    public static <T> T create(final T object) {
        EntityManager em = null;
        try {
            em = instance.getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
            return object;
        } finally {
            if (em != null) {
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
                em.close();
            }
        }
    }

    /**
     * Updates the object in the database. If the object has any unpersisted
     * object associated; the object will be persisted as a new object.
     * 
     * @param object
     */
    public static <T> T update(T object) {
        EntityManager em = null;
        try {
            em = instance.getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();
            T result = em.merge(object);
            em.getTransaction().commit();
            return result;
        } finally {
            if (em != null) {
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
                em.close();
            }
        }
    }

    /**
     * Deletes the given object from the database.
     * 
     * @param object
     */
    public static <T> void delete(final T object) {
        EntityManager em = null;
        try {
            em = instance.getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();
            em.remove(object);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
                em.close();
            }
        }
    }

    /**
     * Get all the entries in the database of the given class.
     * 
     * @param clazz
     *            Class which entries in DB will be showed.
     * @return List of T objects.
     */
    public static <T> List<T> getAll(final Class<T> clazz) {
        EntityManager em = null;
        try {
            em = instance.getEntityManagerFactory().createEntityManager();
            TypedQuery<T> query = em.createQuery(
                    "select q from " + clazz.getName() + " q", clazz);
            return query.getResultList();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     * Find the element of the given class with the selected id
     * 
     * @param clazz
     *            Class of the entity desired.
     * @param id
     *            Id to look for.
     * @return The object matching the class and the id.
     */
    public static <T> T getElementById(final Class<T> clazz, final Object id) {
        EntityManager em = null;
        try {
            em = instance.getEntityManagerFactory().createEntityManager();
            return em.find(clazz, id);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     * Gets the entity manager factory.
     * 
     * @return The Entity Manager Factory.
     */
    private EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }

}
