package cn.nanfeng.dao.impl;

import cn.nanfeng.dao.AdminDao;
import cn.nanfeng.domain.Admin;
import cn.nanfeng.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class AdminDaoImpl implements AdminDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public Admin findUserByUsernameAndPassword(String username, String password) {
        try {
            String sql = "select * from admin where username = ? and password = ?";
            Admin user = template.queryForObject(sql, new BeanPropertyRowMapper<Admin>(Admin.class), username, password);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void updateLastTimeByUsernameAndPassword(String username, String password, String lasttime) {
        String sql = "update admin set lasttime = ? where username = ? and password=?";
        template.update(sql,lasttime,username,password);
    }

    @Override
    public void updatePassword(String username, String password, String newpassword) {
        String sql = "update admin set password = ? where username = ? and password=?";
        template.update(sql,newpassword,username,password);

    }
}
