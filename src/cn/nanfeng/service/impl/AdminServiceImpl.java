package cn.nanfeng.service.impl;

import cn.nanfeng.dao.impl.AdminDaoImpl;
import cn.nanfeng.domain.Admin;
import cn.nanfeng.domain.User;
import cn.nanfeng.service.AdminService;
import cn.nanfeng.util.MailUtils;

import java.util.List;

public class AdminServiceImpl implements AdminService {
    private AdminDaoImpl adminDao=new AdminDaoImpl();

    @Override
    public Admin login(Admin user) {
        return adminDao.findUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }


    public boolean backupToEmail(String email, Admin admin) {
        UserServiceImpl userService=new UserServiceImpl();
        List<User> all = userService.findAll();
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("联系人备份\r\n"+admin.getUsername()+"于"+admin.getLasttime()+"提交备份"+"\r\nID--姓名--性别--年龄--地址--QQ--邮箱\n");
        for (User user : all) {
            stringBuilder.append(user.toString());
        }
        String s = stringBuilder.toString();
        return MailUtils.sendMail(email, s, "联系人数据管理备份");
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
