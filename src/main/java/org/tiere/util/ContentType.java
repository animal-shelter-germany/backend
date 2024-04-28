package org.tiere.util;

import java.util.HashMap;
import java.util.Map;

public class ContentType {

    public static String find(String extension) {
        Map<String, String> contentTypes = new HashMap<>();
        contentTypes.put("jpeg", "image/jpeg");
        return contentTypes.get(extension);
    }

}
