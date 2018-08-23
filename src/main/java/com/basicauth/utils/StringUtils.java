package com.basicauth.utils;

import java.util.List;

/**
 * Created by IPC_Server on 5/10/2017.
 */
public class StringUtils {

    public static String concatenateStringListUsing(String separator, List<String> stringList) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < stringList.size(); i++) {
            String string = stringList.get(i);
            if (i != 0 && i != stringList.size() - 1) stringBuilder.append(separator);
            stringBuilder.append(string);
        }
        return stringBuilder.toString();
    }
}
