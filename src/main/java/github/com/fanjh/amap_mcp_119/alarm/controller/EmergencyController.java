package github.com.fanjh.amap_mcp_119.alarm.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import github.com.fanjh.amap_mcp_119.alarm.model.FireAlarmEntity;
import github.com.fanjh.amap_mcp_119.alarm.pojo.FireReportReqParam;
import github.com.fanjh.amap_mcp_119.alarm.pojo.ResponseData;
import github.com.fanjh.amap_mcp_119.alarm.service.AmapService;
import github.com.fanjh.amap_mcp_119.alarm.service.FireAlarmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin
// EmergencyController.java
@RestController
@RequestMapping("/api/emergency")
@Slf4j
public class EmergencyController {
    @Autowired
    private AmapService amapService;
    @Autowired
    private FireAlarmService fireAlarmService;

    @CrossOrigin
    @PostMapping("/fire-reports")
    public String fireReport(@RequestBody FireReportReqParam fireReport) {
        FireAlarmEntity fireAlarmEntity = new FireAlarmEntity();
        fireAlarmEntity.setAddress(fireReport.getFireLocation());
        fireAlarmEntity.setLongitude(fireReport.getCoordinates()[0]);
        fireAlarmEntity.setLatitude(fireReport.getCoordinates()[1]);
        fireAlarmEntity.setReportTime(LocalDateTime.now());
        fireAlarmEntity.setReporter("u");
        fireAlarmEntity.setPhone("13243616101");
        fireAlarmService.save(fireAlarmEntity);
        return "";
    }

    @CrossOrigin
    @GetMapping("/list")
    public List<ResponseData> list(@RequestParam(required = false) Integer status) {
        QueryWrapper<FireAlarmEntity> query = new QueryWrapper<>();
        return fireAlarmService.handleEmergency(fireAlarmService.list(query));
    }
}