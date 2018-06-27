package com.baojia.connection.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baojia.backstage.common.auth.util.Result;
import com.baojia.connection.kafka.BikeSender;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/test")
@CrossOrigin(origins="*",maxAge=3600)
public class TestKafKaController {

	@Autowired
	private BikeSender bikeSender;

	@GetMapping(value = "/send", produces = { "application/json;charset=UTF-8" })
	@ApiOperation(value = "kafka发消息", notes = "kafka发消息")
	public Result listener() {
		try {
			bikeSender.sendTest();
			Result res = Result.SUCCESS.copyThis();
			return res;
		} catch (Exception e) {
			return Result.error(e.getMessage());
		}
	}
}
