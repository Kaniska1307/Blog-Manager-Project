package com.validation;

public class AttachCategoryValidation {

	public String removeComma(String postId) {
		int length = postId.length();
		if (postId.endsWith(",")) {
			postId = postId.substring(0, length - 1);
		}
		return postId;
	}
}