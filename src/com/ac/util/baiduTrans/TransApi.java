package com.ac.util.baiduTrans;

import java.util.HashMap;
import java.util.Map;

public class TransApi {
    private static final String TRANS_API_HOST = "https://fanyi-api.baidu.com/api/trans/vip/translate";
    private static final String APP_ID = "20170328000043593";
    private static final String SECURITY_KEY = "CQl3H8LS8lRclKNW3GmO";


    public String getTransResult(String query, String from, String to) {
        Map<String, String> params = buildParams(query, from, to);
        return HttpGet.get(TRANS_API_HOST, params);
    }

    private Map<String, String> buildParams(String query, String from, String to) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("q", query);
        params.put("from", from);
        params.put("to", to);

        params.put("appid", APP_ID);

        // 随机数
        String salt = String.valueOf(System.currentTimeMillis());
        params.put("salt", salt);

        // 签名
        String src = APP_ID + query + salt + SECURITY_KEY; // 加密前的原文
        params.put("sign", MD5.md5(src));

        return params;
    }

}
