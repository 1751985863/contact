package cn.nanfeng.web.servlet;

import cn.nanfeng.domain.Admin;
import cn.nanfeng.service.impl.AdminServiceImpl;
import cn.nanfeng.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/changePasswordServlet")
public class changePasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String password = request.getParameter("password");
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");
        if (!StringUtils.compare(password1,password2)){
            request.setAttribute("msg","两次密码输入不一致");
            request.getRequestDispatcher("/change.jsp").forward(request,response);
            return;
        }
        Admin loginUser = (Admin) request.getSession().getAttribute("loginUser");
        String password3 = loginUser.getPassword();
        if (!StringUtils.compare(password,password3)){
            request.setAttribute("msg","旧密码输入错误");
            request.getRequestDispatcher("/change.jsp").forward(request,response);
            return;
        }
        AdminServiceImpl adminService=new AdminServiceImpl();
        adminService.updatePassword(loginUser,password1);

        request.setAttribute("msg","修改成功");
        request.getSession().removeAttribute("loginUser");
        request.setAttribute("login_msg","密码修改成功请重新登录！");
        request.getRequestDispatcher("/login.jsp").forward(request,response);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
this.doPost(request,response);
    }
}
