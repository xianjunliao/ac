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


/**** @time2017-01-21 00:34:16
****/

@Entity
@Table(name ="SEQUENCE")
@DynamicUpdate
public  class  SequenceEntity  implements Serializable{



/**
	 * 
	 */
	private static final long serialVersionUID = -1948569567734814090L;

@Id
@Column(name = "increment")
private	Integer	increment ;

@Column(name = "name")
private	String	name ;

@Column(name = "current_value")
private	Integer	currentValue ;


public	SequenceEntity() {}

public	SequenceEntity(
Integer increment,String name,Integer currentValue){this.increment=increment;
this.name=name;
this.currentValue=currentValue;
	}

	public	Integer	getIncrement () {
	return	increment;
	}
	public	void	setIncrement(Integer  increment) {
	this.increment=increment;
	}

	public	String	getName () {
	return	name;
	}
	public	void	setName(String  name) {
	this.name=name;
	}

	public	Integer	getCurrentValue () {
	return	currentValue;
	}
	public	void	setCurrentValue(Integer  currentValue) {
	this.currentValue=currentValue;
	}


public	String	toString () {
	return	"SequenceEntity[increment="+increment+",name="+name+",currentValue="+currentValue+"]";
	}

}