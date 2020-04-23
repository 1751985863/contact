package cn.nanfeng.dao;

import cn.nanfeng.domain.Admin;

public interface AdminDao {
    Admin findUserByUsernameAndPassword(String username, String password);
    void updateLastTimeByUsernameAndPassword(String username, String password,String lasttime);

    void updatePassword(String username, String password,String newpassword);
}
