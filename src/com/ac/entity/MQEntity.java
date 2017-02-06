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

/**** @time2017-02-06 13:47:38 ****/

@Entity
@Table(name = "MQ")
@DynamicUpdate
public class MQEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8538632564298461249L;

	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Integer id;

	@Column(name = "author")
	private String author;

	@Column(name = "time")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date time;

	@Column(name = "text")
	private String text;

	public MQEntity() {
	}

	public MQEntity(Integer id, String author, Date time, String text) {
		this.id = id;
		this.author = author;
		this.time = time;
		this.text = text;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String toString() {
		return "MQEntity[id=" + id + ",author=" + author + ",time=" + time
				+ ",text=" + text + "]";
	}

}