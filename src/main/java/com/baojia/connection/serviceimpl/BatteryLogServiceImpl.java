package com.baojia.connection.serviceimpl;


import java.util.List;

import org.springframework.stereotype.Service;

import com.baojia.backstage.common.auth.util.PageUtils;
import com.baojia.connection.dao.BatteryLogMapper;
import com.baojia.connection.domain.dto.BatteryLogDto;
import com.baojia.connection.domain.po.BatteryLog;
import com.baojia.connection.service.BatteryLogService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
* @Title: BatteryLogServiceImpl  
* @Description:  电池日志查询列表实现类
* @author renjing  
* @date 2018年5月31日 下午7:02:50
 */
@Service
public class BatteryLogServiceImpl  extends ServiceImpl<BatteryLogMapper, BatteryLog> implements BatteryLogService{

	@Override
	public PageUtils getBatteryLog(BatteryLogDto batteryDto) {
		PageHelper.startPage(batteryDto.getPageNum(),batteryDto.getPageSize());
		List<BatteryLog> log = baseMapper.selectLog(batteryDto);
		PageInfo<BatteryLog> pageInfo = new PageInfo<>(log);
		PageUtils page = new PageUtils(pageInfo.getList(), (int)pageInfo.getTotal(), pageInfo.getPageSize(), pageInfo.getPageNum());
        return page;
	}

}
