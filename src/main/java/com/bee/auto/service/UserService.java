package com.bee.auto.service;

import com.bee.auto.bean.SysUser;
import com.bee.auto.dao.ISysUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class UserService {

    @Resource
    public ISysUser sysUserDao;
    //查询登录
    public SysUser getSysUser(String username,String pswd){
        SysUser sysUser = new SysUser();
        sysUser.setUsername(username);
        sysUser.setPswd(pswd);
        return sysUserDao.getSysUser(sysUser);
    }

    //查询知否存在openid
    public SysUser getOpenId(String openId){
        SysUser sysUser = new SysUser();
        sysUser.setOpenid(openId);

        return sysUserDao.getSysUser(sysUser);
    }

    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    public  void addUser(String username, String pswd, String email){
        SysUser sysUser = new SysUser();
        sysUser.setUsername(username);
        sysUser.setPswd(pswd);
        sysUser.setEmail(email);

        sysUserDao.addSysUser(sysUser);
    }

    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    public  void addWXInfo(SysUser sysUser){


        sysUserDao.addWXInfo(sysUser);
    }

    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    public  void motifyUser(String username,  String pswd){
        SysUser sysUser = new SysUser();
        sysUser.setUsername(username);
        sysUser.setPswd(pswd);

        sysUserDao.motifySysUser(sysUser);
    }

    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    public  int motifyWXInfo(SysUser sysUser){

        return sysUserDao.motifyWXInfo(sysUser);
    }


}
