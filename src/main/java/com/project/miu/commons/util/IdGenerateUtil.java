package com.project.miu.commons.util;

import java.util.UUID;

public class IdGenerateUtil {
    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        String uuidStr = str.replace("-", "");
        return uuidStr;
    }
}
