package github.com.fanjh.amap_mcp_119.alarm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import github.com.fanjh.amap_mcp_119.alarm.model.FireAlarmEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FireReportMapper extends BaseMapper<FireAlarmEntity> {
}
