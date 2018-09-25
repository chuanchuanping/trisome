package com.bee.auto.bean;

import lombok.Data;

/**
 * 与消息表对应的实体类
 */
@Data
public class Message {
	/**
	 * 主键
	 */
	private String id;
	/**
	 * 指令名称
	 */
	private String command;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 内容
	 */
	private String content;



	@Override
	public String toString() {
		return "{" +
				"id='" + id + '\'' +
				", command='" + command + '\'' +
				", description='" + description + '\'' +
				", content='" + content + '\'' +
				'}';
	}
}
