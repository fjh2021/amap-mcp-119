package github.com.fanjh.amap_mcp_119.alarm.service;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import github.com.fanjh.amap_mcp_119.alarm.mapper.FireReportMapper;
import github.com.fanjh.amap_mcp_119.alarm.model.FireAlarmEntity;
import github.com.fanjh.amap_mcp_119.alarm.model.FireAlarmPlanEntity;
import github.com.fanjh.amap_mcp_119.alarm.pojo.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class FireAlarmService {

    @Autowired
    private FireReportMapper fireReportMapper;

    @Autowired
    private AmapService amapService;

    @Autowired
    private FireAlarmPlanService fireAlarmPlanService;

    public void save(FireAlarmEntity fireAlarmEntity) {
        fireReportMapper.insert(fireAlarmEntity);
    }

    public List<FireAlarmEntity> list(QueryWrapper<FireAlarmEntity> query) {
        return fireReportMapper.selectList(query);
    }
    public List<ResponseData> handleEmergency(List<FireAlarmEntity> list) {
        List<ResponseData> responseDataList = new ArrayList<>();
        for (var fireAlarmEntity : list) {
            //1. 地址转坐标
            FireAlarmPlanEntity fireAlarmPlanEntity = fireAlarmPlanService.getByAddress(fireAlarmEntity.getAddress());
            if (Objects.isNull(fireAlarmPlanEntity) || Objects.isNull(fireAlarmPlanEntity.getLatitude())) {
                JSONObject geoResult = amapService.geoCode(fireAlarmEntity.getAddress());
                String location = geoResult.getJSONArray("geocodes").getJSONObject(0).getString("location");
                String[] lnglat = location.split(",");
                fireAlarmPlanEntity = new FireAlarmPlanEntity();
                fireAlarmPlanEntity.setLongitude(Double.parseDouble(lnglat[0]));
                fireAlarmPlanEntity.setLatitude(Double.parseDouble(lnglat[1]));
                fireAlarmPlanEntity.setAddress(fireAlarmEntity.getAddress());
            }
            JSONObject aroundResult;
            // 2. 搜索周边消防站
            if (Objects.isNull(fireAlarmPlanEntity) || Objects.isNull(fireAlarmPlanEntity.getFireStations())) {
                aroundResult = amapService.aroundSearch(fireAlarmPlanEntity.getLongitude(),
                        fireAlarmPlanEntity.getLatitude(), "消防站");
                fireAlarmPlanEntity.setFireStations(aroundResult.toJSONString());
            } else {
                aroundResult = JSONObject.parseObject(fireAlarmPlanEntity.getFireStations());
            }

            JSONObject aroundHospitalResult;
            // 周边搜索（医院）
            if (Objects.isNull(fireAlarmPlanEntity) || Objects.isNull(fireAlarmPlanEntity.getHospitals())) {
                aroundHospitalResult = amapService.aroundSearch(fireAlarmPlanEntity.getLongitude(),
                        fireAlarmPlanEntity.getLatitude(), "医院");
                fireAlarmPlanEntity.setHospitals(aroundHospitalResult.toJSONString());
            } else {
                aroundHospitalResult = JSONObject.parseObject(fireAlarmPlanEntity.getHospitals());
            }

            // 3. 计算最优路径（取最近的消防站）
            JSONObject firstPOI = aroundResult.getJSONArray("pois").getJSONObject(0);
            JSONObject pathResult;
            if (Objects.isNull(fireAlarmPlanEntity) || Objects.isNull(fireAlarmPlanEntity.getPath())) {
                pathResult = amapService.pathPlanning(
                        fireAlarmPlanEntity.getLongitude(),
                        fireAlarmPlanEntity.getLatitude(),
                        Double.parseDouble(firstPOI.getString("location").split(",")[0]),
                        Double.parseDouble(firstPOI.getString("location").split(",")[1]));
                fireAlarmPlanEntity.setPath(pathResult.toJSONString());
            } else {
                pathResult = JSONObject.parseObject(fireAlarmPlanEntity.getPath());
            }


            // 3. 计算最优路径（取最近的消防站）
            JSONObject firstHosptialPOI = aroundHospitalResult.getJSONArray("pois").getJSONObject(0);
            JSONObject pathHospitalResult;
            if (Objects.isNull(fireAlarmPlanEntity) || Objects.isNull(fireAlarmPlanEntity.getPathHospital())) {
                pathHospitalResult = amapService.pathPlanning(
                        fireAlarmPlanEntity.getLongitude(),
                        fireAlarmPlanEntity.getLatitude(),
                        Double.parseDouble(firstHosptialPOI.getString("location").split(",")[0]),
                        Double.parseDouble(firstHosptialPOI.getString("location").split(",")[1]));
                fireAlarmPlanEntity.setPathHospital(pathHospitalResult.toJSONString());
            } else {
                pathHospitalResult = JSONObject.parseObject(fireAlarmPlanEntity.getPathHospital());
            }

            // 4. 组装返回数据
            Double[] lnglat = {fireAlarmPlanEntity.getLongitude(), fireAlarmPlanEntity.getLatitude()};
            ResponseData responseData = ResponseData.success()
                    .put("fireLocation", lnglat)
                    .put("address", fireAlarmEntity.getAddress())
                    .put("fireStations", aroundResult.getJSONArray("pois"))
                    .put("hospitals", aroundHospitalResult.getJSONArray("pois"))
                    .put("pathHospital", pathHospitalResult.getJSONObject("route").getJSONArray("paths").get(0))
                    .put("path", pathResult.getJSONObject("route").getJSONArray("paths").get(0));


            responseDataList.add(responseData);
            if (fireAlarmPlanEntity.getId() == null) {
                fireAlarmPlanService.save(fireAlarmPlanEntity);
            }
        }
        return responseDataList;

    }
}
