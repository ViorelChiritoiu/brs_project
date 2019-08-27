package com.brs.util;

public class RandomStringUtil {

    public static String getAlphaNumericString(int n, String inputString) {
        // chose a Character random from this String
        String inputStringUcase = inputString.trim().toUpperCase().replaceAll(" ", "")
                .concat("123456789");

        // create StringBuffer size of inputString
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            int index = (int) (inputStringUcase.length() * Math.random());
            sb.append(inputStringUcase.charAt(index));
        }
        return sb.toString();
    }
}
