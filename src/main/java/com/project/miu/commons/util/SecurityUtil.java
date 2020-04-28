package com.project.miu.commons.util;

import com.project.miu.bean.dto.SessionUserDTO;
import org.springframework.core.NamedThreadLocal;

public class SecurityUtil {
    private static ThreadLocal<SessionUserDTO> localUser = new NamedThreadLocal<>("sessionUser");

    public static SessionUserDTO getUser() {
        return localUser.get();
    }

    public static void setUser(SessionUserDTO user) {
        localUser.set(user);
    }

    public static void clear() {
        localUser.remove();
    }

    public static String getMemberId() {
        return localUser.get().getMemberId();
    }
}
