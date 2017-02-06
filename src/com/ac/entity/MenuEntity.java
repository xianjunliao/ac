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


/**** @time2017-02-06 16:56:48
****/

@Entity
@Table(name ="MENU")
@DynamicUpdate
public  class  MenuEntity  implements Serializable{



/**
	 * 
	 */
	private static final long serialVersionUID = -6906140534850721603L;

@Id
@Column(name = "menu_code")
private	String	menuCode ;

@Column(name = "id")@GeneratedValue(generator="increment")
@GenericGenerator(name="increment", strategy = "increment")
private	Integer	id ;

@Column(name = "create_time")@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
private	Date	createTime ;

@Column(name = "menu_order")
private	Integer	menuOrder ;

@Column(name = "update_time")@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
private	Date	updateTime ;

@Column(name = "user_id")
private	Integer	userId ;

@Column(name = "src")
private	String	src ;

@Column(name = "is_show")
private	Integer	isShow ;

@Column(name = "menu_name")
private	String	menuName ;
@Column(name = "type")
private Integer type;
public	MenuEntity() {}

public	MenuEntity(
String menuCode,Integer id,Date createTime,Integer order,Date updateTime,Integer userId,String src,Integer isShow,String menuName){this.menuCode=menuCode;
this.id=id;
this.createTime=createTime;
this.menuOrder=order;
this.updateTime=updateTime;
this.userId=userId;
this.src=src;
this.isShow=isShow;
this.menuName=menuName;
	}

	public	String	getMenuCode () {
	return	menuCode;
	}
	public	void	setMenuCode(String  menuCode) {
	this.menuCode=menuCode;
	}

	public	Integer	getId () {
	return	id;
	}
	public	void	setId(Integer  id) {
	this.id=id;
	}

	public	Date	getCreateTime () {
	return	createTime;
	}
	public	void	setCreateTime(Date  createTime) {
	this.createTime=createTime;
	}


	public Integer getMenuOrder() {
		return menuOrder;
	}

	public void setMenuOrder(Integer menu_order) {
		this.menuOrder = menu_order;
	}

	public	Date	getUpdateTime () {
	return	updateTime;
	}
	public	void	setUpdateTime(Date  updateTime) {
	this.updateTime=updateTime;
	}

	public	Integer	getUserId () {
	return	userId;
	}
	public	void	setUserId(Integer  userId) {
	this.userId=userId;
	}

	public	String	getSrc () {
	return	src;
	}
	public	void	setSrc(String  src) {
	this.src=src;
	}

	public	Integer	getIsShow () {
	return	isShow;
	}
	public	void	setIsShow(Integer  isShow) {
	this.isShow=isShow;
	}

	public	String	getMenuName () {
	return	menuName;
	}
	public	void	setMenuName(String  menuName) {
	this.menuName=menuName;
	}

public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

public	String	toString () {
	return	"MenuEntity[menuCode="+menuCode+",id="+id+",createTime="+createTime+",menuOrder="+menuOrder+",updateTime="+updateTime+",userId="+userId+",src="+src+",isShow="+isShow+",menuName="+menuName+"]";
	}

}