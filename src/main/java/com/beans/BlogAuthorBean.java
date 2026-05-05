package com.beans;

import java.util.ArrayList;
import java.util.Arrays;

public class BlogAuthorBean {

	String authorFirstName;
	String authorLastName;
	String authorQualification;
	String authorEmail;
	String authorHomeTown;
	String authorPhoneNo;
	String authorIndex;
	String authorPage;
	String authorFullName;
	String authorId;
	String authorErrorCode;
	String authorErrorName;
	
	ArrayList<String> listForQualification = new ArrayList<>(Arrays.asList("Teacher", "Doctor", "Engineer", "Policemen", "Driver", "IT", "Army", "Collector","Fireman","Student"));
	
	public String getAuthorName() {
		return authorFullName;
	}
	public void setAuthorFullName(String authorFullName) {
		this.authorFullName = authorFullName;
	}
	public String getAuthorId() {
		return authorId;
	}
	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}
	public String getAuthorIndex() {
		return authorIndex;
	}
	public void setAuthorIndex(String authorIndex) {
		this.authorIndex = authorIndex;
	}
	public String getAuthorPage() {
		return authorPage;
	}
	public void setAuthorPage(String authorPage) {
		this.authorPage = authorPage;
	}
	
	public ArrayList<String> getListForQualification() {
		return listForQualification;
	}
	public void setListForQualification(ArrayList<String> listForQualification) {
		this.listForQualification = listForQualification;
	}
	public String getAuthorFirstName() {
		return authorFirstName;
	}
	public void setAuthorFirstName(String authorFirstName) {
		this.authorFirstName = authorFirstName;
	}
	public String getAuthorLastName() {
		return authorLastName;
	}
	public void setAuthorLastName(String authorLastName) {
		this.authorLastName = authorLastName;
	}
	public String getAuthorQualification() {
		return authorQualification;
	}
	public void setAuthorQualification(String authorQualification) {
		this.authorQualification = authorQualification;
	}
	public String getAuthorEmail() {
		return authorEmail;
	}
	public void setAuthorEmail(String authorEmail) {
		this.authorEmail = authorEmail;
	}
	public String getAuthorHomeTown() {
		return authorHomeTown;
	}
	public void setAuthorHomeTown(String authorHomeTown) {
		this.authorHomeTown = authorHomeTown;
	}
	public String getAuthorPhoneNo() {
		return authorPhoneNo;
	}
	public void setAuthorPhoneNo(String authorPhoneNo) {
		this.authorPhoneNo = authorPhoneNo;
	}
	
}