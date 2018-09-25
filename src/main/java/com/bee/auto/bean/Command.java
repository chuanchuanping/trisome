package com.bee.auto.bean;

import lombok.Data;

import java.util.List;

/**
 * 与指令表对应的实体类
 */
@Data
public class Command {
	/**
	 * 主键
	 */
	private String id;
	/**
	 * 指令名称
	 */
	private String name;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 一条指令对应的自动回复内容列表
	 */
	private List<CommandContent> contentList;

}