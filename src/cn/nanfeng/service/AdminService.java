package cn.nanfeng.service;

import cn.nanfeng.domain.Admin;

public interface AdminService {
    public Admin login(Admin user);
    public boolean backupToEmail(String email, Admin admin);
}
