package com.yc.beans;

import java.sql.Timestamp;

public class Student{

	private Integer id;

	private Integer sno;

	private String username;

	private String school;

	private String myclass;

	private Timestamp mydate;

	private String isinhbstudent;

	private String isinwh;

	private String isinhb;

	private String islike;

	private String isconfirm;

	public void setId(Integer id){
		this.id=id;
	}
	public Integer getId(){
		return this.id;
	}
	public void setSno(Integer sno){
		this.sno=sno;
	}
	public Integer getSno(){
		return this.sno;
	}
	public void setUsername(String username){
		this.username=username;
	}
	public String getUsername(){
		return this.username;
	}
	public void setSchool(String school){
		this.school=school;
	}
	public String getSchool(){
		return this.school;
	}
	public void setMyclass(String myclass){
		this.myclass=myclass;
	}
	public String getMyclass(){
		return this.myclass;
	}
	public void setMydate(Timestamp mydate){
		this.mydate=mydate;
	}
	public Timestamp getMydate(){
		return this.mydate;
	}
	public void setIsinhbstudent(String isinhbstudent){
		this.isinhbstudent=isinhbstudent;
	}
	public String getIsinhbstudent(){
		return this.isinhbstudent;
	}
	public void setIsinwh(String isinwh){
		this.isinwh=isinwh;
	}
	public String getIsinwh(){
		return this.isinwh;
	}
	public void setIsinhb(String isinhb){
		this.isinhb=isinhb;
	}
	public String getIsinhb(){
		return this.isinhb;
	}
	public void setIslike(String islike){
		this.islike=islike;
	}
	public String getIslike(){
		return this.islike;
	}
	public void setIsconfirm(String isconfirm){
		this.isconfirm=isconfirm;
	}
	public String getIsconfirm(){
		return this.isconfirm;
	}
	@Override
	public String toString() {
		return "Student [id="+getId()+", sno="+getSno()+", username="+getUsername()+", school="+getSchool()+", myclass="+getMyclass()+", mydate="+getMydate()+", isinhbstudent="+getIsinhbstudent()+", isinwh="+getIsinwh()+", isinhb="+getIsinhb()+", islike="+getIslike()+", isconfirm="+getIsconfirm()+"]";
	}
}