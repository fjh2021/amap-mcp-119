package github.com.fanjh.amap_mcp_119.alarm.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FireReportReqParam {
    private String fireLocation;
    private double[] coordinates;
    private LocalDateTime timestamp;
    private String source;
}
