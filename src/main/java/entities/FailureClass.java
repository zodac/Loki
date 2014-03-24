package entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the failureclass database table.
 * 
 */
@Entity
@NamedQuery(name="FailureClass.findAll", query="SELECT f FROM FailureClass f")
public class FailureClass implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int failureClass;

	@Column(length=255)
	private String description;

	//bi-directional many-to-one association to Callfailure
	@OneToMany(mappedBy="failureClass", fetch=FetchType.EAGER)
	private List<CallFailure> callFailures;

	public FailureClass() {
	}

	public int getFailureClass() {
		return this.failureClass;
	}

	public void setFailureClass(int failureClass) {
		this.failureClass = failureClass;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}