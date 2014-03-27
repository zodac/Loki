package entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;


/**
 * The persistent class for the eventcause database table.
 * 
 */
@Entity
@NamedQuery(name="EventCause.findAll", query="SELECT e FROM EventCause e")
public class EventCause implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EventCausePK id;

	@Column(length=255)
	private String description;

	//bi-directional many-to-one association to Callfailure
	@OneToMany(mappedBy="eventCause")
	private List<CallFailure> callFailures;

	public EventCause() {
	}

	public EventCausePK getId() {
		return this.id;
	}

	public void setId(EventCausePK id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}