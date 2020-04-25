package cn.nanfeng.web.servlet;

import cn.nanfeng.dao.impl.UserDaoImpl;
import cn.nanfeng.domain.Admin;
import cn.nanfeng.domain.User;
import cn.nanfeng.service.impl.AdminServiceImpl;
import cn.nanfeng.service.impl.UserServiceImpl;
import cn.nanfeng.util.MailUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/backupServlet")
public class backupServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        HttpSession session = request.getSession();
        Admin admin = null;
        if (session.getAttribute("loginUser")!=null){
            admin=(Admin) session.getAttribute("loginUser");
        }
        AdminServiceImpl adminService=new AdminServiceImpl();
        boolean ybs = adminService.backupToEmail(email,admin);
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
