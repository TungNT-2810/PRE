package com.zyuternity.erp.network.cookies_handle;

import java.io.IOException;
import java.net.CookieManager;
import java.net.URI;
import java.util.List;
import java.util.Map;
//import java.util.prefs.Preferences;

/**
 * Created by ZYuTernity on 7/12/2016.
 */
public class MyCookieManager extends CookieManager {
    @Override
    public void put(URI uri, Map<String, List<String>> stringListMap) throws IOException {
        super.put(uri, stringListMap);
        if (stringListMap != null && stringListMap.get("Set-Cookie") != null)
            for (String string : stringListMap.get("Set-Cookie")) {
                if (string.contains("JSESSIONID")) {
                       // NetworkPreference.getInstance().setSessionId(string);
                }
            }
    }
}
