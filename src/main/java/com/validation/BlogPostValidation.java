package com.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BlogPostValidation {
	
	public String checkDate(String date) {
        String result = "success"; 
        String regex = "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(date);
        if (matcher.matches() == false) {
            result = "incorrect date";
        }
        return result;
    }
	
	public String checkURL(String url) {
        String result = "success"; 
        String regex = "((http|https|ftp)://)(www.)?"
        	     + "[a-zA-Z0-9@:%._\\+~#?&//=]"
        	     + "{2,256}\\.[a-z]"
        	     + "{2,6}\\b([-a-zA-Z0-9@:%"
        	     + "._\\+~#?&//=]*)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(url);
        if (matcher.matches() == false) {
            result = "incorrect url";
        }
        return result;
    }
	
	public String countLength(String text) {
		String result="success";
		int count=text.length();
		if(count>4000)
		{
			result="incorrect length";
		}
		return result;
	}
	
     
	
}
