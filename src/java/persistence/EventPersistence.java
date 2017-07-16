package persistence;

import business.Event;
import javax.ejb.Stateful;
import javax.inject.Inject;
import persistence.connexion.Connexion;

/**
 *
 * @author boris.klett
 */
@Stateful
public class EventPersistence {

    public EventPersistence() {
    }

    private static final long serialVersionUID = 1L;

    @Inject
    Connexion con;

    public Event getEventById(Integer id) {
        Event event = new Event();
        event = (Event) con.getEM().find(Event.class, id);
        return event;

    }

}
