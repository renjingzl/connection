package com.baojia.connection.domain.dto;

/**
* @Title: BatteryLogDto  
* @Description:  电池操作日志查询列表前端传人参数
* @author renjing  
* @date 2018年5月31日 下午6:52:35
 */
public class BatteryLogDto extends BaseDto{
    /**
     * 电池编号
     */
    private String batteryNo;

    /**
     * 开始时间
     */
    private String startTime;
    
    /**
     * 结束时间
     */
    private String endTime;


    public String getBatteryNo() {
        return batteryNo;
    }

    public void setBatteryNo(String batteryNo) {
        this.batteryNo = batteryNo == null ? null : batteryNo.trim();
    }

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
    
}