package com.bee.auto.service;


import com.bee.auto.bean.Message;
import com.bee.auto.dao.MessageDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 维护相关的业务功能
 */
@Service
public class MaintainService {

	 @Autowired
	 private MessageDAO message;
	/**
	 * 单条删除
	 */
	@Transactional(rollbackFor = {RuntimeException.class, Exception.class})
	public void deleteOne(String id) {
		if(id != null && !"".equals(id.trim())) {
			message.deleteOne(Integer.valueOf(id));
		}
	}
	
	/**
	 * 批量删除
	 */
	@Transactional(rollbackFor = {RuntimeException.class, Exception.class})
	public void deleteBatch(String[] ids) {
		List<Integer> idList = new ArrayList<Integer>();
		for(String id : ids) {
			idList.add(Integer.valueOf(id));
		}
		message.deleteBatch(idList);
	}

	//增加一条
	@Transactional(rollbackFor = {RuntimeException.class, Exception.class})
	public void addOne(Message msg){

		message.addMessage(msg);
//		int a = 1/0;
	}

	//修改一条数据
	@Transactional(rollbackFor = {RuntimeException.class, Exception.class})
	public void updateOne(Message msg){

		message.update(msg);
//		int a = 1/0;
	}
}
