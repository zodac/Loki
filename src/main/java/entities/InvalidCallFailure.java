package entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;


/**
 * The persistent class for the invalidcallfailure database table.
 * 
 */
@Entity
@NamedQuery(name="InvalidCallFailure.findAll", query="SELECT i FROM InvalidCallFailure i")
public class InvalidCallFailure implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(length=255)
	private String causeCode;

	private int cellId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	private int duration;

	private int eventId;

	private String failureClass;

	@Column(name="HIER3_ID")
	private BigInteger hier3Id;

	@Column(name="HIER32_ID")
	private BigInteger hier32Id;

	@Column(name="HIER321_ID")
	private BigInteger hier321Id;

	private BigInteger imsi;

	private int market;

	@Column(length=255)
	private String neVersion;

	private int operator;

	private int ueType;

	public InvalidCallFailure() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCauseCode() {
		return this.causeCode;
	}

	public void setCauseCode(String causeCode) {
		this.causeCode = causeCode;
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

	public int getEventId() {
		return this.eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public String getFailureClass() {
		return this.failureClass;
	}

	public void setFailureClass(String failureClass) {
		this.failureClass = failureClass;
	}

	public BigInteger getHier3Id() {
		return this.hier3Id;
	}

	public void setHier3Id(BigInteger hier3Id) {
		this.hier3Id = hier3Id;
	}

	public BigInteger getHier32Id() {
		return this.hier32Id;
	}

	public void setHier32Id(BigInteger hier32Id) {
		this.hier32Id = hier32Id;
	}

	public BigInteger getHier321Id() {
		return this.hier321Id;
	}

	public void setHier321Id(BigInteger hier321Id) {
		this.hier321Id = hier321Id;
	}

	public BigInteger getImsi() {
		return this.imsi;
	}

	public void setImsi(BigInteger imsi) {
		this.imsi = imsi;
	}

	public int getMarket() {
		return this.market;
	}

	public void setMarket(int market) {
		this.market = market;
	}

	public String getNEVersion() {
		return this.neVersion;
	}

	public void setNEVersion(String neVersion) {
		this.neVersion = neVersion;
	}

	public int getOperator() {
		return this.operator;
	}

	public void setOperator(int operator) {
		this.operator = operator;
	}

	public int getUEType() {
		return this.ueType;
	}

	public void setUEType(int ueType) {
		this.ueType = ueType;
	}
}