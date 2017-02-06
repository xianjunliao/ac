package com.ac.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "AC_TEMP")
@DynamicUpdate
public class ACTempEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5342309878744307578L;
	@Id
	@Column(name = "tx_name")
	private String txName;

	@Column(name = "id")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Integer id;

	@Column(name = "create_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	@Column(name = "tx_type")
	private Integer txType;

	@Column(name = "remark")
	private String remark;

	@Column(name = "user_id")
	private Integer userId;

	public ACTempEntity() {
	}

	public ACTempEntity(String txName, Integer id, Date createTime,
			Integer txType, String remark, Integer userId) {
		this.txName = txName;
		this.id = id;
		this.createTime = createTime;
		this.txType = txType;
		this.remark = remark;
		this.userId = userId;
	}

	public String getTxName() {
		return txName;
	}

	public void setTxName(String txName) {
		this.txName = txName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getTxType() {
		return txType;
	}

	public void setTxType(Integer txType) {
		this.txType = txType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String toString() {
		return "ACTempEntity[txName=" + txName + ",id=" + id + ",createTime="
				+ createTime + ",txType=" + txType + ",remark=" + remark
				+ ",userId=" + userId + "]";
	}

}