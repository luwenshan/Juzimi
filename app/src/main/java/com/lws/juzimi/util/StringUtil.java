package com.lws.juzimi.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Wenshan.Lu on 2016/12/21.
 */

public class StringUtil {
    public static boolean isEmpty(String value) {
        return isEmpty(value, null);
    }

    public static boolean isEmpty(String value, String ignore) {
        if (value == null || value.trim().length() == 0) {
            return true;
        } else {
            if (ignore != null && value.equalsIgnoreCase(ignore)) {
                return true;
            }
        }
        return false;
    }

    public static String inputStreamToString(InputStream inputStream) {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
