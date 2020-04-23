package cn.nanfeng.service.impl;

import cn.nanfeng.dao.impl.AdminDaoImpl;
import cn.nanfeng.domain.Admin;
import cn.nanfeng.service.AdminService;

public class AdminServiceImpl implements AdminService {
    private AdminDaoImpl adminDao=new AdminDaoImpl();

    @Override
    public Admin login(Admin user) {
        return adminDao.findUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }
    public void updateLastTime(Admin user){
        adminDao.updateLastTimeByUsernameAndPassword(user.getUsername(),user.getPassword(),user.getLasttime());
    }
    public boolean updatePassword(Admin user,String newpassword){
        Admin login = login(user);
        if (login!=null){
            adminDao.updatePassword(user.getUsername(),user.getPassword(),newpassword);
            return true;
        }
        return false;
    }
}
