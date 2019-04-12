package com.rohit.model;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "T_SCHEDULER")
public class Scheduler {
	
	@Id
	@GenericGenerator(name="schedulerGenerator", strategy="com.bnym.standingordermigration.util.SchedulerIdGenerator")
	@GeneratedValue(generator="schedulerGenerator")
	@Column(name = "SCH_ID", nullable=false)
	private String schedulerId;
	
	@Column(name = "SCH_TYPE")
	private String schedulerType;
	
	@Column(name = "SCH_DATE")
	private Date schedulerDate;
	
	@Column(name = "SCH_TIME")
	private String schedulerTime;
	
	@Column(name = "SCH_DAY")
	private String schedulerDay;
	
	@Column(name = "SCH_INTERVAL")
	private int schedulerInterval;
	
	@Column(name = "FIRST_ACTIVATION")
	private Timestamp firstActivation;
	
	@Column(name = "LAST_ACTIVATION")
	private Timestamp lastActivation;
	
	@Column(name = "CRT_USER")
	private String createUser;
	
	@Column(name = "CRT_TS")
	private Timestamp createTS;
	
	@Column(name = "SCH_ADDL_ATTRB")
	private String schedulerAddlAttrb;
	
	@Column(name = "SCH_HLDY_FL")
	private char schedulerHldyFl; 

	@Column(name = "TIMEZONE")
	private String timezone;

	public String getSchedulerId() {
		return schedulerId;
	}

	public void setSchedulerId(String schedulerId) {
		this.schedulerId = schedulerId;
	}

	public String getSchedulerType() {
		return schedulerType;
	}

	public void setSchedulerType(String schedulerType) {
		this.schedulerType = schedulerType;
	}

	

	public Date getSchedulerDate() {
		return schedulerDate;
	}

	public void setSchedulerDate(Date schedulerDate) {
		this.schedulerDate = schedulerDate;
	}

	public String getSchedulerTime() {
		return schedulerTime;
	}

	public void setSchedulerTime(String schedulerTime) {
		this.schedulerTime = schedulerTime;
	}

	public String getSchedulerDay() {
		return schedulerDay;
	}

	public void setSchedulerDay(String schedulerDay) {
		this.schedulerDay = schedulerDay;
	}

	public int getSchedulerInterval() {
		return schedulerInterval;
	}

	public void setSchedulerInterval(int schedulerInterval) {
		this.schedulerInterval = schedulerInterval;
	}

	public Timestamp getFirstActivation() {
		return firstActivation;
	}

	public void setFirstActivation(Timestamp firstActivation) {
		this.firstActivation = firstActivation;
	}

	public Timestamp getLastActivation() {
		return lastActivation;
	}

	public void setLastActivation(Timestamp lastActivation) {
		this.lastActivation = lastActivation;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Timestamp getCreateTS() {
		return createTS;
	}

	public void setCreateTS(Timestamp createTS) {
		this.createTS = createTS;
	}

	public String getSchedulerAddlAttrb() {
		return schedulerAddlAttrb;
	}

	public void setSchedulerAddlAttrb(String schedulerAddlAttrb) {
		this.schedulerAddlAttrb = schedulerAddlAttrb;
	}

	public char getSchedulerHldyFl() {
		return schedulerHldyFl;
	}

	public void setSchedulerHldyFl(char schedulerHldyFl) {
		this.schedulerHldyFl = schedulerHldyFl;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	@Override
	public String toString() {
		return "Scheduler [schedulerId=" + schedulerId + ", schedulerType=" + schedulerType + ", schedulerDate="
				+ schedulerDate + ", schedulerTime=" + schedulerTime + ", schedulerDay=" + schedulerDay
				+ ", schedulerInterval=" + schedulerInterval + ", firstActivation=" + firstActivation
				+ ", lastActivation=" + lastActivation + ", createUser=" + createUser + ", createTS=" + createTS
				+ ", schedulerAddlAttrb=" + schedulerAddlAttrb + ", schedulerHldyFl=" + schedulerHldyFl + ", timezone="
				+ timezone + "]";
	}



	
	

}
