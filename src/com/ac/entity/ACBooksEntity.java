package com.ac.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Transient;

import java.util.Date;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

/**** @time2017-01-20 22:03:28 ****/

@Entity
@Table(name = "AC_BOOKS")
@DynamicUpdate
public class ACBooksEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7457567558385252686L;

	@Id
	@Column(name = "temp_tx_id")
	private Integer tempTxId;

	@Column(name = "id")
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Integer id;

	@Column(name = "ac_month")
	private String acMonth;

	@Column(name = "ac_week")
	private Integer acWeek;

	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "ac_create_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date acCreateTime;

	@Column(name = "ac_amount")
	private Double acAmount;

	@Column(name = "p_ac_id")
	private Integer pAcId;

	@Column(name = "ac_remark")
	private String acRemark;

	@Column(name = "ac_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date acDate;
	@Transient
	private String txName;

	public ACBooksEntity() {
	}

	public ACBooksEntity(Integer tempTxId, Integer id, String acMonth,
			Integer acWeek, Integer userId, Date acCreateTime, Double acAmount,
			Integer pAcId, String acRemark, Date acDate) {
		this.tempTxId = tempTxId;
		this.id = id;
		this.acMonth = acMonth;
		this.acWeek = acWeek;
		this.userId = userId;
		this.acCreateTime = acCreateTime;
		this.acAmount = acAmount;
		this.pAcId = pAcId;
		this.acRemark = acRemark;
		this.acDate = acDate;
	}

	public Integer getTempTxId() {
		return tempTxId;
	}

	public void setTempTxId(Integer tempTxId) {
		this.tempTxId = tempTxId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAcMonth() {
		return acMonth;
	}

	public void setAcMonth(String acMonth) {
		this.acMonth = acMonth;
	}

	public Integer getAcWeek() {
		return acWeek;
	}

	public void setAcWeek(Integer acWeek) {
		this.acWeek = acWeek;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getAcCreateTime() {
		return acCreateTime;
	}

	public void setAcCreateTime(Date acCreateTime) {
		this.acCreateTime = acCreateTime;
	}

	public Double getAcAmount() {
		return acAmount;
	}

	public void setAcAmount(Double acAmount) {
		this.acAmount = acAmount;
	}

	public Integer getPAcId() {
		return pAcId;
	}

	public void setPAcId(Integer pAcId) {
		this.pAcId = pAcId;
	}

	public String getAcRemark() {
		return acRemark;
	}

	public void setAcRemark(String acRemark) {
		this.acRemark = acRemark;
	}

	public Date getAcDate() {
		return acDate;
	}

	public void setAcDate(Date acDate) {
		this.acDate = acDate;
	}

	public Integer getpAcId() {
		return pAcId;
	}

	public void setpAcId(Integer pAcId) {
		this.pAcId = pAcId;
	}

	public String getTxName() {
		return txName;
	}

	public void setTxName(String txName) {
		this.txName = txName;
	}

	public String toString() {
		return "AcBooksEntity[tempTxId=" + tempTxId + ",id=" + id + ",acMonth="
				+ acMonth + ",acWeek=" + acWeek + ",userId=" + userId
				+ ",acCreateTime=" + acCreateTime + ",acAmount=" + acAmount
				+ ",pAcId=" + pAcId + ",acRemark=" + acRemark + ",acDate="
				+ acDate + "]";
	}

}