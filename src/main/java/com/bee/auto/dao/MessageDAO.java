package com.bee.auto.dao;


import com.bee.auto.bean.Message;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 与Message配置文件相对应的接口
 */
@Mapper
public interface MessageDAO {
	/**
	 * 根据查询条件查询消息列表
	 */
	public List<Message> queryMessageList(Map map);

	/**
	 * 根据查询id查询消息列表
	 */
	public Message  queryMessageById (Integer id);
	
	/**
	 * 根据查询条件查询消息列表的条数
	 */
	public int count(Message message);

	public List<Message> queryAll(Message message);

	public void addMessage(Message message);

	public void addMessagesList(List<Message> messages);

	/**
	 * 单条删除
	 */
    public  void deleteOne(int id);

   /*
    批量删除
    */
    public  void deleteBatch(List<Integer> ids);

	/**
	 * 更新一条数据
	 *
	 */
    public void update(Message message);
}
