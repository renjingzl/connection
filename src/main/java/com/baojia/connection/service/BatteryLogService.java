package com.baojia.connection.service;

import com.baojia.backstage.common.auth.util.PageUtils;
import com.baojia.connection.domain.dto.BatteryLogDto;
import com.baojia.connection.domain.po.BatteryLog;
import com.baomidou.mybatisplus.service.IService;

public interface BatteryLogService extends IService<BatteryLog>{

	PageUtils getBatteryLog(BatteryLogDto batteryDto);
	
}
