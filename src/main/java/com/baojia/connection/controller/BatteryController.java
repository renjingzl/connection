package com.baojia.connection.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baojia.backstage.common.auth.util.PageUtils;
import com.baojia.backstage.common.auth.util.Result;
import com.baojia.connection.common.redis.RedisUtil;
import com.baojia.connection.domain.dto.BatteryLogDto;
import com.baojia.connection.service.BatteryLogService;

import io.swagger.annotations.ApiOperation;

/**
 * @Title: BatteryController
 * @Description: 电池管理
 * @author renjing
 * @date 2018年5月29日 下午6:15:47
 */
@RestController
@RequestMapping("/battery")
@CrossOrigin(origins="*",maxAge=3600)
public class BatteryController {

	@Autowired
	private RedisUtil redisUtils;
	@Autowired
	private BatteryLogService batteryLogService;



	@GetMapping(value = "/log", produces = { "application/json;charset=UTF-8" })
	@ApiOperation(value = "电池日志查询列表", notes = "根据查询条件查询电池日志列表")
	public Result getBatteryLog() {
		try {
            BatteryLogDto batteryLogDto = new BatteryLogDto();
            batteryLogDto.setBatteryNo("666");
			PageUtils list = batteryLogService.getBatteryLog(batteryLogDto);
			Result res = Result.SUCCESS.copyThis();
			res.setContext(list);
			return res;
		} catch (Exception e) {
			return Result.error(e.getMessage());
		}
	}
}
