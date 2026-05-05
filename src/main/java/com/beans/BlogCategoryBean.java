package com.beans;

public class BlogCategoryBean {

	String categoryId;
	String categoryName;
	String categoryStatus;
	String categoryIndex;
	String categoryPage;
	String categoryCreatedDate;
	String categoryErrorName;
	int categoryErrorCode;

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryStatus() {
		return categoryStatus;
	}

	public void setCategoryStatus(String categoryStatus) {
		this.categoryStatus = categoryStatus;
	}

	public String getCategoryIndex() {
		return categoryIndex;
	}

	public void setCategoryIndex(String categoryIndex) {
		this.categoryIndex = categoryIndex;
	}

	public String getCategoryPage() {
		return categoryPage;
	}

	public void setCategoryPage(String pageNo) {
		this.categoryPage = pageNo;
	}

	public String getCategoryCreatedDate() {
		return categoryCreatedDate;
	}

	public void setCategoryCreatedDate(String categoryCreatedDate) {
		this.categoryCreatedDate = categoryCreatedDate;
	}

	public String getCategoryErrorName() {
		return categoryErrorName;
	}

	public void setCategoryErrorName(String categoryErrorName) {
		this.categoryErrorName = categoryErrorName;
	}

	public int getCategoryErrorCode() {
		return categoryErrorCode;
	}

	public void setCategoryErrorCode(int categoryErrorCode) {
		this.categoryErrorCode = categoryErrorCode;
	}

}
