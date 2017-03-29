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


/**** @time2017-03-29 14:23:59
****/

@SuppressWarnings("serial")
@Entity
@Table(name ="POOL")
@DynamicUpdate
public  class  PoolEntity  implements Serializable{



	@Id
	@Column(name = "id")
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	private	Integer	id ;

	@Column(name = "create_time")	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private	Date	createTime ;
	
	@Column(name = "menuCode")
	private	String	menuCode ;
	
	@Column(name = "menu1Code")
	private	String	menu1Code ;

	@Column(name = "name")
	private	String	name ;

	@Column(name = "baidu_type")
	private	String	baiduType ;

	@Column(name = "code")
	private	String	code ;

	@Column(name = "type")
	private	String	type ;




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

	public	String	getMenu1Code () {
	return	menu1Code;
	}
	public	void	setMenu1Code(String  menu1Code) {
	this.menu1Code=menu1Code;
	}

	public	String	getName () {
	return	name;
	}
	public	void	setName(String  name) {
	this.name=name;
	}

	public	String	getBaiduType () {
	return	baiduType;
	}
	public	void	setBaiduType(String  baiduType) {
	this.baiduType=baiduType;
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
	return	"PoolEntity[menuCode="+menuCode+",id="+id+",createTime="+createTime+",menu1Code="+menu1Code+",name="+name+",baiduType="+baiduType+",code="+code+",type="+type+"]";
	}

}