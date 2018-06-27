package com.baojia.connection.domain.po;

import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
* @Title: BatteryLog  
* @Description: 电池操作日志表 
* @author renjing  
* @date 2018年6月14日 下午12:27:51
 */
@TableName("battery_log")
public class BatteryLog implements Serializable {
    private Long id;

    /**
     * 电池编号
     */
    private String batteryNo;

    /**
     * 操作人id
     */
    private Long operatorId;

    /**
     * 操作人姓名
     */
    private String operatorName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 操作内容
     */
    private String operateContent;
    
    /**
     * 角色
     */
    private String role;

    /**
     * 0 其他 1 电池录入 3 电池绑定车辆 5 卡车发电池 7 库管入库 9 库管出库 11  安装电池 13 车辆中取下电池 15 上报丢失
     */
    private Integer operateType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBatteryNo() {
        return batteryNo;
    }

    public void setBatteryNo(String batteryNo) {
        this.batteryNo = batteryNo == null ? null : batteryNo.trim();
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName == null ? null : operatorName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getOperateContent() {
        return operateContent;
    }

    public void setOperateContent(String operateContent) {
        this.operateContent = operateContent == null ? null : operateContent.trim();
    }

    public Integer getOperateType() {
        return operateType;
    }

    public void setOperateType(Integer operateType) {
        this.operateType = operateType;
    }

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}