package com.gsau.sparkproject.domain;

import java.io.Serializable;

 /**  
   * @Description: 任务信息封装
   * @author: wangguoqing
   * @date: 2018/10/29 13:29
  */
public class Task implements Serializable {
	
	private static final long serialVersionUID = 3518776796426921776L;

	private long taskid;
	private String taskName;                               //任务名称
	private String createTime;                             //创建时间
	private String startTime;                              //开始时间
	private String finishTime;                             //完成时间
	private String taskType;                               //任务类型
	private String taskStatus;                             //任务状态
	private String taskParam;                              //任务参数
	
	public long getTaskid() {
		return taskid;
	}
	public void setTaskid(long taskid) {
		this.taskid = taskid;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}
	public String getTaskType() {
		return taskType;
	}
	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}
	public String getTaskStatus() {
		return taskStatus;
	}
	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}
	public String getTaskParam() {
		return taskParam;
	}
	public void setTaskParam(String taskParam) {
		this.taskParam = taskParam;
	}
	
}
