package persistence.connexion;

import java.io.Serializable;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author boris.klett
 */
@Stateful
public class Connexion implements Serializable {

    private static final long serialVersionUID = 1L;

    @PersistenceContext(unitName = "TBPUBK")
    private EntityManager entityManagerTB;

    /**
     * This is the constructor of the class Connexion
     */
    public Connexion() {
    }

    /**
     * This method get the entity manager to connect my app to my database
     *
     * @return an instance of EntityManager
     */
    public EntityManager getEM() {
        return this.entityManagerTB;
    }

    /**
     * This method close the connexion to my database
     */
//    public void closeConnexion() {
//        em.clear();
//        em.close();
//    }
}
