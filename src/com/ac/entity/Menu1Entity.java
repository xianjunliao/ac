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


/**** @time2017-03-29 12:07:45
****/

@SuppressWarnings("serial")
@Entity
@Table(name ="MENU1")
@DynamicUpdate
public  class  Menu1Entity  implements Serializable{



	@Id
	@Column(name = "id")
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	private	Integer	id ;

	@Column(name = "create_time")	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private	Date	createTime ;

	@Column(name = "name")
	private	String	name ;

	@Column(name = "code")
	private	String	code ;

	@Column(name = "type")
	private	String	type ;

	@Column(name = "menu_code")
	private	String	menuCode ;

	@Column(name = "colour")
	private	String	colour ;

	public	String	getColour () {
	return	colour;
	}
	public	void	setColour(String  colour) {
	this.colour=colour;
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

	public	String	getName () {
	return	name;
	}
	public	void	setName(String  name) {
	this.name=name;
	}

	public	String	getCode () {
	return	code;
	}
	public	void	setCode(String  code) {
	this.code=code;
	}

	public	String	getType () {
	return	type;
	}
	public	void	setType(String  type) {
	this.type=type;
	}


	public	String	toString () {
	return	"Menu1Entity[colour="+colour+",menuCode="+menuCode+",id="+id+",createTime="+createTime+",name="+name+",code="+code+",type="+type+"]";
	}

}