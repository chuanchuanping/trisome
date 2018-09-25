package com.bee.auto.dao;


import com.bee.auto.bean.Command;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ICommand {

    List<Command> queryCommandList(Command command);
}
