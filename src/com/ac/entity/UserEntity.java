package com.ac.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.Date;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

/**** @time2017-01-14 23:17:59 ****/

@Entity
@Table(name = "USER")
@DynamicUpdate
public class UserEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9003290567448380979L;
	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Integer id;

	@Column(name = "create_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	@Column(name = "mail")
	private String mail;

	@Column(name = "update_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;

	@Column(name = "status")
	private Integer status;

	@Column(name = "name")
	private String name;

	@Column(name = "type")
	private Integer type;

	@Column(name = "password")
	private String password;

	@Column(name = "ip")
	private String ip;

	public UserEntity() {
	}

	public UserEntity(Integer id, Date createTime, String mail,
			Date updateTime, Integer status, String name, Integer type,
			String password, String ip) {
		this.id = id;
		this.createTime = createTime;
		this.mail = mail;
		this.updateTime = updateTime;
		this.status = status;
		this.name = name;
		this.type = type;
		this.password = password;
		this.ip = ip;
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

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String toString() {
		return "UserEntity[id=" + id + ",createTime=" + createTime + ",mail="
				+ mail + ",updateTime=" + updateTime + ",status=" + status
				+ ",name=" + name + ",type=" + type + ",password=" + password
				+ ",ip=" + ip + "]";
	}

}