package github.com.fanjh.amap_mcp_119.alarm;

import com.alibaba.fastjson2.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

// HttpUtil.java
public class HttpUtil {
    private static final CloseableHttpClient httpClient = HttpClients.createDefault();

    public static JSONObject get(String url) {
        HttpGet httpGet = new HttpGet(url);
        try {
            HttpResponse response = httpClient.execute(httpGet);
            String result = EntityUtils.toString(response.getEntity());
            return JSONObject.parseObject(result);
        } catch (IOException e) {
            throw new RuntimeException("HTTP请求失败", e);
        }
    }
}