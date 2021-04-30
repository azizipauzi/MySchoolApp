package com.myschool.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="students")
public class students {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="std_id")
	private long stdid;
	
	@Column(name="std_firstname")
	private String stdfname;
	
	@Column(name="std_lastname")
	private String stdlname;
	
	@Column(name="std_course")
	private String stdcourse;
	
	public students(){
		
	}
	
	public students(String stdfname, String stdlname, String stdcourse) {
		super();
		this.stdfname = stdfname;
		this.stdlname = stdlname;
		this.stdcourse = stdcourse;
	}
	
	public long getStdid() {
		return stdid;
	}
	public void setStdid(long stdid) {
		this.stdid = stdid;
	}
	public String getStdfname() {
		return stdfname;
	}
	public void setStdfname(String stdfname) {
		this.stdfname = stdfname;
	}
	public String getStdlname() {
		return stdlname;
	}
	public void setStdlname(String stdlname) {
		this.stdlname = stdlname;
	}

	public String getStdcourse() {
		return stdcourse;
	}

	public void setStdcourse(String stdcourse) {
		this.stdcourse = stdcourse;
	}
}
