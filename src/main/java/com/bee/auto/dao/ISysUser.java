package com.bee.auto.dao;

import com.bee.auto.bean.SysUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ISysUser {

     SysUser getSysUser(SysUser sysUser);
     void motifySysUser(SysUser sysUser);
     int motifyWXInfo(SysUser sysUser);
     void addSysUser(SysUser sysUser);
     void addWXInfo(SysUser sysUser);
}
