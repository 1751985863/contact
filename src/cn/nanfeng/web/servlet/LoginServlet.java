package cn.nanfeng.web.servlet;

import cn.nanfeng.domain.Admin;
import cn.nanfeng.domain.User;
import cn.nanfeng.service.UserService;
import cn.nanfeng.service.impl.AdminServiceImpl;
import cn.nanfeng.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");

        //2.获取数据
        //2.1获取用户填写验证码
        String verifycode = request.getParameter("verifycode");

        //3.验证码校验
        HttpSession session = request.getSession();
        Admin user = new Admin();
        if (session.getAttribute("loginUser")!=null){
           user=(Admin) session.getAttribute("loginUser");
        }
        else{
            String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
            session.removeAttribute("CHECKCODE_SERVER");//确保验证码一次性
            if(!checkcode_server.equalsIgnoreCase(verifycode)){
                //验证码不正确
                //提示信息
                request.setAttribute("login_msg","验证码错误！");
                //跳转登录页面
                request.getRequestDispatcher("/login.jsp").forward(request,response);

                return;
            }
            String lasttime = request.getParameter("lasttime");

            Map<String, String[]> map = request.getParameterMap();
            //4.封装User对象

            try {
                BeanUtils.populate(user,map);
                if (user.getPassword()==null||"".equals(user.getPassword())||user.getUsername()==null||"".equals(user.getUsername()))
                {
                    request.setAttribute("login_msg","用户名或密码错误！");
                    //跳转登录页面
                    request.getRequestDispatcher("/login.jsp").forward(request,response);
                    return;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }


        //5.调用Service查询
        AdminServiceImpl adminService=new AdminServiceImpl();
        Admin loginUser = adminService.login(user);
        //6.判断是否登录成功

        if(loginUser != null){
            //登录成功
            //将用户存入session
            Date currentTime = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateString = formatter.format(currentTime);
            user.setLasttime(dateString);
            adminService.updateLastTime(user);

            session.setAttribute("loginUser",loginUser);
            //跳转页面
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        }else{
            //登录失败
            //提示信息
            request.setAttribute("login_msg","用户名或密码错误！");
            //跳转登录页面
            request.getRequestDispatcher("/login.jsp").forward(request,response);

        }




    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
