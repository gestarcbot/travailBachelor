package service;

import java.io.Serializable;
import javax.ejb.Stateful;
import javax.inject.Inject;
import persistence.EventPersistence;

/**
 *
 * @author boris.klett
 */
@Stateful
public class Services implements Serializable {

    public Services() {
    }

    private static final long serialVersionUID = 1L;
    @Inject
    EventPersistence ePersistence;

    public String getEventName(int id) {
        return ePersistence.getEventById(id).getName();
    }
}
