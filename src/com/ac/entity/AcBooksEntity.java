package com.ac.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.Id;
import java.util.Date;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

/**** @time2017-01-15 18:15:57 ****/

@Entity
@Table(name = "AC_BOOKS")
@DynamicUpdate
public class AcBooksEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7413688485483006711L;

	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Integer id;

	@Column(name = "ac_name")
	private String acName;

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

	@Column(name = "ac_type")
	private Integer acType;

	@Column(name = "p_ac_id")
	private Integer pAcId;

	@Column(name = "ac_remark")
	private String acRemark;

	@Column(name = "ac_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date acDate;

	public AcBooksEntity() {
	}

	public AcBooksEntity(Integer id, String acName, String acMonth,
			Integer acWeek, Integer userId, Date acCreateTime, Double acAmount,
			Integer acType, Integer pAcId, String acRemark, Date acDate) {
		this.id = id;
		this.acName = acName;
		this.acMonth = acMonth;
		this.acWeek = acWeek;
		this.userId = userId;
		this.acCreateTime = acCreateTime;
		this.acAmount = acAmount;
		this.acType = acType;
		this.pAcId = pAcId;
		this.acRemark = acRemark;
		this.acDate = acDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAcName() {
		return acName;
	}

	public void setAcName(String acName) {
		this.acName = acName;
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

	public Integer getAcType() {
		return acType;
	}

	public void setAcType(Integer acType) {
		this.acType = acType;
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

	public String toString() {
		return "AcBooksEntity[id=" + id + ",acName=" + acName + ",acMonth="
				+ acMonth + ",acWeek=" + acWeek + ",userId=" + userId
				+ ",acCreateTime=" + acCreateTime + ",acAmount=" + acAmount
				+ ",acType=" + acType + ",pAcId=" + pAcId + ",acRemark="
				+ acRemark + ",acDate=" + acDate + "]";
	}

}