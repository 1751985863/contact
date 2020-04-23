package cn.nanfeng.web.servlet;

import cn.nanfeng.dao.impl.UserDaoImpl;
import cn.nanfeng.domain.User;
import cn.nanfeng.service.impl.UserServiceImpl;
import cn.nanfeng.util.MailUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/backupServlet")
public class backupServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        UserServiceImpl userService=new UserServiceImpl();
        List<User> all = userService.findAll();
        StringBuilder stringBuilder=new StringBuilder();
        for (User user : all) {
            stringBuilder.append(user.toString()+"\n");
        }
        String s = stringBuilder.toString();
        boolean ybs = MailUtils.sendMail(email, s, "联系人数据管理备份");
        if (!ybs)
            request.setAttribute("msg","邮箱输入错误，发送异常");
        else
            request.setAttribute("msg","发送成功");
        request.getRequestDispatcher("/backup.jsp").forward(request,response);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
this.doPost(request,response);
    }
}
