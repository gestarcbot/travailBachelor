package business;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author boris.klett
 */
@Entity
@Table(name = "EVENEMENT")
public class Event implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "NUMERO", nullable = false, length = 50)
    private Integer id;

    @Column(name = "NOM", nullable = false, length = 100)
    private String name;

    public Event() {
    }

    public Event(String name) {
        this.name = name;
    }

    public Event(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
