package com.bee.auto.bean;

import lombok.Data;

/**
 * 与指令内容表对应的实体类
 */
@Data
public class CommandContent {
	/**
	 * 主键
	 */
	private String id;
	
	/**
	 * 自动回复的内容
	 */
	private String content;
	
	/**
	 * 关联的指令表主键
	 */
	private String commandId;

}
