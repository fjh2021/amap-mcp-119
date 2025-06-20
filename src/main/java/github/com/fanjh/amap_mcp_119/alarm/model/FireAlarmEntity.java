package github.com.fanjh.amap_mcp_119.alarm.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("fire_alarm")
public class FireAlarmEntity implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String reporter;
    private String phone;
    private String address;
    private Double longitude;
    private Double latitude;
    private Integer level;
    private String description;
    private Integer status; // 0-未处理 1-处理中 2-已处理
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime reportTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime handleTime;
}
