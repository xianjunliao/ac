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


/**** @time2017-02-10 13:37:57
****/

@SuppressWarnings("serial")
@Entity
@Table(name ="AC_LOG")
@DynamicUpdate
public  class  AcLogEntity  implements Serializable{



	@Id
	@Column(name = "id")
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	private	Integer	id ;

	@Column(name = "ac_name")
	private	String	acName ;

	@Column(name = "ac_code")
	private	String	acCode ;

	@Column(name = "temp_id")
	private	Integer	tempId ;
	
	@Column(name = "user_id")
	private	Integer	userId ;
	
	@Column(name = "ac_type")
	private Integer acType;
	
	@Column(name = "ac_amount")
	private	Double	acAmount ;

	@Column(name = "ac_time")	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private	Date	acTime ;

	@Column(name = "ac_date")	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private	Date	acDate ;




	public	Integer	getId () {
	return	id;
	}
	public	void	setId(Integer  id) {
	this.id=id;
	}

	public	String	getAcName () {
	return	acName;
	}
	public	void	setAcName(String  acName) {
	this.acName=acName;
	}

	public	String	getAcCode () {
	return	acCode;
	}
	public	void	setAcCode(String  acCode) {
	this.acCode=acCode;
	}

	public	Integer	getUserId () {
	return	userId;
	}
	public	void	setUserId(Integer  userId) {
	this.userId=userId;
	}

	public Integer getAcType() {
		return acType;
	}
	public void setAcType(Integer acType) {
		this.acType = acType;
	}
	public	Double	getAcAmount () {
	return	acAmount;
	}
	public	void	setAcAmount(Double  acAmount) {
	this.acAmount=acAmount;
	}

	public	Date	getAcTime () {
	return	acTime;
	}
	public	void	setAcTime(Date  acTime) {
	this.acTime=acTime;
	}

	public	Date	getAcDate () {
	return	acDate;
	}
	public	void	setAcDate(Date  acDate) {
	this.acDate=acDate;
	}
	public Integer getTempId() {
		return tempId;
	}
	public void setTempId(Integer tempId) {
		this.tempId = tempId;
	}
	public	String	toString () {
	return	"AcLogEntity[id="+id+",acName="+acName+",acCode="+acCode+",userId="+userId+",acAmount="+acAmount+",acTime="+acTime+",acDate="+acDate+"]";
	}

}