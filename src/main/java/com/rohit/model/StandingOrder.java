package com.rohit.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "T_STANDINGORDER")
public class StandingOrder implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name="standingOrderGenerator", strategy="com.bnym.standingordermigration.util.SchedulerIdGenerator")
	@GeneratedValue(generator="standingOrderGenerator")
	@ApiModelProperty(notes = "The databasae generated SO ID" )
	@Column(name = "SO_ID")
	private String id;
	
	@NotNull
	@Column(name = "SO_REFERENCE")
	private String reference;
	
	@NotNull
	@Size(max=3)
	@Column(name = "SO_CURRENCY")
	private String currency;
	
	@Digits(integer=18, fraction=3)
	@Column(name = "SO_AMOUNT")
	private BigDecimal amount;
	
	@Column(name = "DB_ACCOUNT")
	private String debitAccount;
	
	@Column(name = "BENE_DETAILS")
	private String beneficiaryDETAILS;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "SCH_ID")
	private Scheduler scheduler;
	
	@Column(name = "SO_NAME")
	private String standingOrderName;
	
	@Column(name = "CRT_USER")
	private String createdUser;
	
	@Column(name = "CRT_TS")
	private Timestamp createdTimestamp;

	@Column(name = "APPRV_USER")
	private String approvedUser;
	
	@Column(name = "APPRV_TS")
	private Timestamp approvedTimestamp;
	
	@Column(name = "RELATEDREFERENCE")
	private String relatedReference;
	
	@Column(name = "CR_ACCOUNT")
	private String creditAccount;
	
	@Column(name = "INTERMEDIARY")
	private String intermediary;
	
	@Column(name = "ORD_CUSTOMER")
	private String orderCustomer;
	
	@Column(name = "DETAIL_OF_PYMT")
	private String detailsOfPayment;
	
	@Column(name = "ORD_INSTIT")
	private String orderInstitution;
	
	@Column(name = "SO_TYPE")
	private String standingOrderType;
	
	@Column(name = "BENE_BANK")
	private String beneficiaryBank;
	
	@Column(name = "BANK2BANK")
	private String bank2Bank;
	
	@Column(name = "BENE_BANK_DTL")

	private String beneBankDetails;
	
	@Column(name = "ORD_BANK_DTL")
	private String orderingBankDetails;
	

	
	@Column(name = "INTERMED_DTL")
	private String intermediaryDetails;
	 
	@Column(name = "ORD_INSTIT_DTL")
	private String orderingInstutionDetails;
	 


	public String getId() {
		return id;
	}





	public void setId(String id) {
		this.id = id;
	}





	public String getReference() {
		return reference;
	}





	public void setReference(String reference) {
		this.reference = reference;
	}





	public String getCurrency() {
		return currency;
	}





	public void setCurrency(String currency) {
		this.currency = currency;
	}





	public BigDecimal getAmount() {
		return amount;
	}





	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}





	public String getDebitAccount() {
		return debitAccount;
	}





	public void setDebitAccount(String debitAccount) {
		this.debitAccount = debitAccount;
	}





	public String getBeneficiaryDETAILS() {
		return beneficiaryDETAILS;
	}





	public void setBeneficiaryDETAILS(String beneficiaryDETAILS) {
		this.beneficiaryDETAILS = beneficiaryDETAILS;
	}





	public Scheduler getScheduler() {
		return scheduler;
	}





	public void setScheduler(Scheduler scheduler) {
		this.scheduler = scheduler;
	}





	public String getStandingOrderName() {
		return standingOrderName;
	}





	public void setStandingOrderName(String standingOrderName) {
		this.standingOrderName = standingOrderName;
	}





	public String getCreatedUser() {
		return createdUser;
	}





	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}





	public Timestamp getCreatedTimestamp() {
		return createdTimestamp;
	}





	public void setCreatedTimestamp(Timestamp createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}





	public String getApprovedUser() {
		return approvedUser;
	}





	public void setApprovedUser(String approvedUser) {
		this.approvedUser = approvedUser;
	}





	public Timestamp getApprovedTimestamp() {
		return approvedTimestamp;
	}





	public void setApprovedTimestamp(Timestamp approvedTimestamp) {
		this.approvedTimestamp = approvedTimestamp;
	}





	public String getRelatedReference() {
		return relatedReference;
	}





	public void setRelatedReference(String relatedReference) {
		this.relatedReference = relatedReference;
	}





	public String getCreditAccount() {
		return creditAccount;
	}





	public void setCreditAccount(String creditAccount) {
		this.creditAccount = creditAccount;
	}





	public String getIntermediary() {
		return intermediary;
	}





	public void setIntermediary(String intermediary) {
		this.intermediary = intermediary;
	}





	public String getOrderCustomer() {
		return orderCustomer;
	}





	public void setOrderCustomer(String orderCustomer) {
		this.orderCustomer = orderCustomer;
	}





	public String getDetailsOfPayment() {
		return detailsOfPayment;
	}





	public void setDetailsOfPayment(String detailsOfPayment) {
		this.detailsOfPayment = detailsOfPayment;
	}





	public String getOrderInstitution() {
		return orderInstitution;
	}





	public void setOrderInstitution(String orderInstitution) {
		this.orderInstitution = orderInstitution;
	}





	public String getStandingOrderType() {
		return standingOrderType;
	}





	public void setStandingOrderType(String standingOrderType) {
		this.standingOrderType = standingOrderType;
	}





	public String getBeneficiaryBank() {
		return beneficiaryBank;
	}





	public void setBeneficiaryBank(String beneficiaryBank) {
		this.beneficiaryBank = beneficiaryBank;
	}





	public String getBank2Bank() {
		return bank2Bank;
	}





	public void setBank2Bank(String bank2Bank) {
		this.bank2Bank = bank2Bank;
	}





	public String getBeneBankDetails() {
		return beneBankDetails;
	}





	public void setBeneBankDetails(String beneBankDetails) {
		this.beneBankDetails = beneBankDetails;
	}





	public String getOrderingBankDetails() {
		return orderingBankDetails;
	}





	public void setOrderingBankDetails(String orderingBankDetails) {
		this.orderingBankDetails = orderingBankDetails;
	}





	public String getIntermediaryDetails() {
		return intermediaryDetails;
	}





	public void setIntermediaryDetails(String intermediaryDetails) {
		this.intermediaryDetails = intermediaryDetails;
	}





	public String getOrderingInstutionDetails() {
		return orderingInstutionDetails;
	}





	public void setOrderingInstutionDetails(String orderingInstutionDetails) {
		this.orderingInstutionDetails = orderingInstutionDetails;
	}





	@Override
	public String toString() {
		return "StandingOrder [id=" + id + ", reference=" + reference + ", currency=" + currency + ", amount=" + amount
				+ ", debitAccount=" + debitAccount + ", beneficiaryDETAILS=" + beneficiaryDETAILS + ", scheduler="
				+ scheduler + ", standingOrderName=" + standingOrderName + ", createdUser=" + createdUser
				+ ", createdTimestamp=" + createdTimestamp + ", approvedUser=" + approvedUser + ", approvedTimestamp="
				+ approvedTimestamp + ", relatedReference=" + relatedReference + ", creditAccount=" + creditAccount
				+ ", intermediary=" + intermediary + ", orderCustomer=" + orderCustomer + ", detailsOfPayment="
				+ detailsOfPayment + ", orderInstitution=" + orderInstitution + ", standingOrderType="
				+ standingOrderType + ", beneficiaryBank=" + beneficiaryBank + ", bank2Bank=" + bank2Bank
				+ ", beneBankDetails=" + beneBankDetails + ", orderingBankDetails=" + orderingBankDetails
				+ ", intermediaryDetails=" + intermediaryDetails + ", orderingInstutionDetails="
				+ orderingInstutionDetails + "]";
	}


	

}
