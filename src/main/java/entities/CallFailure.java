package entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

/**
 * The persistent class for the callfailures database table.
 */
@Entity
@NamedQueries( {
	@NamedQuery(name="CallFailure.findAll",
				query="SELECT c FROM CallFailure c"),
	@NamedQuery(name = "CallFailure.NumOfFailuresAndDuration",
				query= "SELECT o.imsi, COUNT(o.imsi), SUM(o.duration) FROM CallFailure o WHERE o.date>=:fromDate and o.date<=:toDate GROUP BY o.imsi"),
	@NamedQuery(name = "CallFailure.AllIMSIsByTimePeriod",
				query= "SELECT o.imsi FROM CallFailure o WHERE o.date>=:fromDate and o.date<=:toDate GROUP BY o.imsi"),
	@NamedQuery(name = "CallFailure.FailuresByModelOverTime",
				query = "SELECT COUNT(o) FROM CallFailure o JOIN o.ueType d WHERE d.model=:model AND o.date>=:fromDate AND o.date<=:toDate"),
	@NamedQuery(name = "CallFailure.EventCauseByIMSI",
				query = "SELECT o.eventCause FROM CallFailure o WHERE o.imsi=:imsi GROUP BY o.eventCause"),
	@NamedQuery(name = "CallFailure.NumOfFailuresByIMSIByTimePeriod",
				query= "SELECT COUNT(o) FROM CallFailure o WHERE o.imsi=:imsi and o.date>=:fromDate and o.date<=:toDate"),				
})
public class CallFailure implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	private int cellId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	private int duration;

	@Column(name="HIER3_ID")
	private long hier3Id;

	@Column(name="HIER32_ID")
	private long hier32Id;

	@Column(name="HIER321_ID")
	private long hier321Id;

	private BigInteger imsi;

	@Column(length=255)
	private String neVersion;

	//bi-directional many-to-one association to Eventcause
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="Cause_Code", referencedColumnName="b_Cause_Code", nullable=false),
		@JoinColumn(name="Event_ID", referencedColumnName="a_Event_ID", nullable=false)
	})
	private EventCause eventCause;

	//bi-directional many-to-one association to Failureclass
	@ManyToOne
	@JoinColumn(name="Failure_Class", nullable=false)
	private FailureClass failureClass;

	//bi-directional many-to-one association to MccMnc
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="Market", referencedColumnName="MCC", nullable=false),
		@JoinColumn(name="Operator", referencedColumnName="MNC", nullable=false)
		})
	private MccMnc mccMnc;

	//bi-directional many-to-one association to Uetype
	@ManyToOne
	@JoinColumn(name="UE_Type", nullable=false)
	private UEType ueType;

	public CallFailure() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCellId() {
		return this.cellId;
	}

	public void setCellId(int cellId) {
		this.cellId = cellId;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getDuration() {
		return this.duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public long getHier3Id() {
		return this.hier3Id;
	}

	public void setHier3Id(long hier3Id) {
		this.hier3Id = hier3Id;
	}

	public long getHier32Id() {
		return this.hier32Id;
	}

	public void setHier32Id(long hier32Id) {
		this.hier32Id = hier32Id;
	}

	public long getHier321Id() {
		return this.hier321Id;
	}

	public void setHier321Id(long hier321Id) {
		this.hier321Id = hier321Id;
	}

	public BigInteger getImsi() {
		return this.imsi;
	}

	public void setImsi(BigInteger imsi) {
		this.imsi = imsi;
	}

	public String getNEVersion() {
		return this.neVersion;
	}

	public void setNEVersion(String neVersion) {
		this.neVersion = neVersion;
	}

	public EventCause getEventCause() {
		return this.eventCause;
	}

	public void setEventCause(EventCause eventCause) {
		this.eventCause = eventCause;
	}

	public FailureClass getFailureClass() {
		return this.failureClass;
	}

	public void setFailureClass(FailureClass failureClass) {
		this.failureClass = failureClass;
	}

	public MccMnc getMccMnc() {
		return this.mccMnc;
	}

	public void setMccMnc(MccMnc mccMnc) {
		this.mccMnc = mccMnc;
	}

	public UEType getUEtype() {
		return this.ueType;
	}

	public void setUEType(UEType ueType) {
		this.ueType = ueType;
	}

}