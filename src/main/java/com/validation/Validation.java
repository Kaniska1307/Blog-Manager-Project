package com.validation;

public class Validation {

	public Boolean textValidation(String text)
	{
		if(text!=null)
		{
			int length = text.length();
			if(length>15)
				return false;
			else if(length>0 && length<=15)
				return true;
		}
		return false;
	}
}
