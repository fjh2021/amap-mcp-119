package github.com.fanjh.amap_mcp_119.alarm.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import github.com.fanjh.amap_mcp_119.alarm.mapper.FireReportPlanMapper;
import github.com.fanjh.amap_mcp_119.alarm.model.FireAlarmPlanEntity;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class FireAlarmPlanService {

    @Resource
    private FireReportPlanMapper fireReportPlanMapper;

    public FireAlarmPlanEntity geoCode(String address) {
        FireAlarmPlanEntity fireAlarmPlanEntity = getByAddress(address);

        return fireAlarmPlanEntity;
    }


    public FireAlarmPlanEntity getByAddress(String address) {
        LambdaQueryWrapper<FireAlarmPlanEntity> queryChainWrapper = new LambdaQueryWrapper<>();
        queryChainWrapper.eq(FireAlarmPlanEntity::getAddress, address);
        List<FireAlarmPlanEntity> list = fireReportPlanMapper.selectList(queryChainWrapper);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list.get(0);
    }

    public FireAlarmPlanEntity save(FireAlarmPlanEntity fireAlarmPlanEntity) {
        fireReportPlanMapper.insert(fireAlarmPlanEntity);
        return fireAlarmPlanEntity;
    }

}
