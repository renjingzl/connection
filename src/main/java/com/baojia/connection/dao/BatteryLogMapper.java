package com.baojia.connection.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.baojia.connection.domain.dto.BatteryLogDto;
import com.baojia.connection.domain.po.BatteryLog;
import com.baomidou.mybatisplus.mapper.BaseMapper;

@Repository
public interface BatteryLogMapper extends BaseMapper<BatteryLog>{

    List<BatteryLog> selectLog(BatteryLogDto batteryDto);

}