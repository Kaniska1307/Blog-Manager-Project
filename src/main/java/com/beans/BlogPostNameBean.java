package com.beans;

public class BlogPostNameBean {
	
	String postId;
	String headlines;
	String errorStatus;
	
	public String getErrorStatus() {
		return errorStatus;
	}
	public void setErrorStatus(String errorStatus) {
		this.errorStatus = errorStatus;
	}
	public String getPostId() {
		return postId;
	}
	public void setPostId(String postId) {
		this.postId = postId;
	}
	public String getHeadlines() {
		return headlines;
	}
	public void setHeadlines(String headlines) {
		this.headlines = headlines;
	}
	
}
