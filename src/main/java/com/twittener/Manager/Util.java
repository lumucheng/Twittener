
package com.twittener.Manager;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Util {
    
    public static List<String> extractUrls(String text) {
        
        List<String> containedUrls = new ArrayList<>();
        String urlRegex = "((https?|ftp|gopher|telnet|file):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
        Pattern pattern = Pattern.compile(urlRegex, Pattern.CASE_INSENSITIVE);
        Matcher urlMatcher = pattern.matcher(text);

        while (urlMatcher.find()) {
            containedUrls.add(text.substring(urlMatcher.start(0), urlMatcher.end(0)));
        }

        return containedUrls;
    }
    
    public static Boolean validVideo(String url) {
        Boolean valid = false;
        
        if (url.toLowerCase().contains("youtube")) {
            valid = true;
        }
        else if (url.toLowerCase().contains("youtu.be")) {
            valid = true;
        }
        else if (url.toLowerCase().contains("vimeo")) {
            valid = true;
        }
        
        return valid;
    }
}
