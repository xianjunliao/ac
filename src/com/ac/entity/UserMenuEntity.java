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


/**** @time2017-02-06 23:11:28
****/

@Entity
@Table(name ="USER_MENU")
@DynamicUpdate
public  class  UserMenuEntity  implements Serializable{



/**
	 * 
	 */
	private static final long serialVersionUID = 5089889457135163275L;

@Id
@Column(name = "id")
@GeneratedValue(generator="increment")
@GenericGenerator(name="increment", strategy = "increment")
private	Integer	id ;

@Column(name = "user_id")
private	Integer	userId ;

@Column(name = "menu_id")
private	Integer	menuId ;

@Column(name = "is_show")
private	Integer	isShow ;

@Column(name = "um_order")
private	Integer	umOrder ;
public	UserMenuEntity() {}

public	UserMenuEntity(
Integer id,Integer userId,Integer menuId,Integer isShow){this.id=id;
this.userId=userId;
this.menuId=menuId;
this.isShow=isShow;
	}

	public	Integer	getId () {
	return	id;
	}
	public	void	setId(Integer  id) {
	this.id=id;
	}

	public	Integer	getUserId () {
	return	userId;
	}
	public	void	setUserId(Integer  userId) {
	this.userId=userId;
	}

	public	Integer	getMenuId () {
	return	menuId;
	}
	public	void	setMenuId(Integer  menuId) {
	this.menuId=menuId;
	}

	public	Integer	getIsShow () {
	return	isShow;
	}
	public	void	setIsShow(Integer  isShow) {
	this.isShow=isShow;
	}



public Integer getUmOrder() {
		return umOrder;
	}

	public void setUmOrder(Integer umOrder) {
		this.umOrder = umOrder;
	}

public	String	toString () {
	return	"UsermenuEntity[id="+id+",userId="+userId+",menuId="+menuId+",isShow="+isShow+"]";
	}

}