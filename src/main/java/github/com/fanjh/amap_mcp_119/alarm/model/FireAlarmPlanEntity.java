package github.com.fanjh.amap_mcp_119.alarm.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("fire_alarm_plan")
public class FireAlarmPlanEntity implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;
    private String address;
    private Double longitude;
    private Double latitude;
    private String fireLocation;
    private String fireStations;
    private String hospitals;
    private String pathHospital;
    private String path;
}
