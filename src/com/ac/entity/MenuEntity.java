package com.ac.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Transient;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

/**** @time2017-03-28 15:06:38 ****/

@SuppressWarnings("serial")
@Entity
@Table(name = "MENU")
@DynamicUpdate
public class MenuEntity implements Serializable {

	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Integer id;

	@Column(name = "create_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	@Column(name = "name")
	private String name;

	@Column(name = "code")
	private String code;

	@Column(name = "type")
	private String type;

	@Column(name = "colour")
	private String colour;

	@Transient
	private List<Menu1Entity> menu1lists;

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Menu1Entity> getMenu1lists() {
		return menu1lists;
	}

	public void setMenu1lists(List<Menu1Entity> menu1lists) {
		this.menu1lists = menu1lists;
	}

	@Override
	public String toString() {
		return "MenuEntity [getColour()=" + getColour() + ", getId()="
				+ getId() + ", getCreateTime()=" + getCreateTime()
				+ ", getName()=" + getName() + ", getCode()=" + getCode()
				+ ", getType()=" + getType() + ", getMenu1lists()="
				+ getMenu1lists() + "]";
	}

}