package com.bee.auto.dao;


import com.bee.auto.bean.CommandContent;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * 与CommandContent配置文件相对应的接口
 */
@Mapper
public interface ICommandContent {
	/**
	 * 单条新增
	 */
	public void insertOne(CommandContent content);
	
	/**
	 * 批量新增
	 */
	public void insertBatch(List<CommandContent> content);
}
