package com.ac.entity;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;


/**** @time2017-02-06 09:15:12
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