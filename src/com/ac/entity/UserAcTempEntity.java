package com.ac.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

/**** @time2017-02-10 10:57:27 ****/

@Entity
@Table(name = "USER_AC_TEMP")
@DynamicUpdate
public class UserAcTempEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1632798335053294562L;

	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Integer id;

	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "temp_id")
	private Integer tempId;
	
	@Column(name = "ac_status")
	private Integer acStatus;
	
	public UserAcTempEntity() {
	}

	public UserAcTempEntity(Integer id, Integer userId, Integer tempId) {
		this.id = id;
		this.userId = userId;
		this.tempId = tempId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getTempId() {
		return tempId;
	}

	public void setTempId(Integer tempId) {
		this.tempId = tempId;
	}

	public Integer getAcStatus() {
		return acStatus;
	}

	public void setAcStatus(Integer acStatus) {
		this.acStatus = acStatus;
	}

	@Override
	public String toString() {
		return "UserAcTempEntity [getId()=" + getId() + ", getUserId()="
				+ getUserId() + ", getTempId()=" + getTempId()
				+ ", getAcStatus()=" + getAcStatus() + "]";
	}
}