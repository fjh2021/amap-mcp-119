package github.com.fanjh.amap_mcp_119.alarm.service;

import com.alibaba.fastjson2.JSONObject;
import github.com.fanjh.amap_mcp_119.alarm.HttpUtil;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;

// AmapService.java
@Service
public class AmapService {
    @Value("${amap.key}")
    private String apiKey;

    @Resource
    private FireAlarmPlanService fireAlarmPlanService;

    // 地理编码
    public JSONObject geoCode(String address) {
        String url = "https://restapi.amap.com/v3/geocode/geo?address=" +
                URLEncoder.encode(address) + "&key=" + apiKey;
        return HttpUtil.get(url);
    }

    // 周边搜索（消防站/医院）
    public JSONObject aroundSearch(double longitude, double latitude, String keywords) {
        String url = "https://restapi.amap.com/v3/place/around?key=" + apiKey +
                "&location=" + longitude + "," + latitude +
                "&keywords=" + keywords + "&radius=1000";
        return HttpUtil.get(url);
    }


    // 路径规划
    public JSONObject pathPlanning(double originLng, double originLat,
                                   double destLng, double destLat) {
        String url = "https://restapi.amap.com/v3/direction/driving?key=" + apiKey +
                "&origin=" + originLng + "," + originLat +
                "&destination=" + destLng + "," + destLat +
                "&strategy=2"; // 2表示最短距离
        return HttpUtil.get(url);
    }
}