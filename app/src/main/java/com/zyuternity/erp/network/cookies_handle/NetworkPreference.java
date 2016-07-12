package com.zyuternity.erp.network.cookies_handle;

import java.util.HashSet;

/**
 * Created by ZYuTernity on 7/12/2016.
 */
public class NetworkPreference {
    // Shared preference
    private HashSet<String> sessionId;

    public HashSet<String> getSessionId() {
        return sessionId;
    }

    public void setSessionId(HashSet<String> sessionId) {
        this.sessionId = sessionId;
    }

    private NetworkPreference() {
    }

    private static NetworkPreference instance;

    public static NetworkPreference getInstance() {
        if(instance == null) {
            instance = new NetworkPreference();
        }
        return instance;
    }
}
