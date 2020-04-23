package cn.nanfeng.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.logging.LogRecord;
@WebFilter("/*")
public class LoginFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws ServletException, IOException {
        //1.强制转换，将类型转换为HttpServlet类型，便于使用
        HttpServletRequest request  = (HttpServletRequest) servletRequest;

        //2.获取资源的请求路径，用于判断该资源是否是与登录相关的资源。如果是预登录相关的资源，则直接放行。
        //登录相关的资源：比如有"/login.jsp";"/loginServlet";"/checkCodeServlet",还要css,js样式等等
        String uri = request.getRequestURI();

        //3.判断是否包含登录相关路径
        if(uri.contains("/loginMore.jsp") || uri.contains("/LoginMoreServlet") ||uri.contains("/login.jsp") || uri.contains("/loginServlet") || uri.contains("/checkCodeServlet") || uri.contains("/css/") || uri.contains("/fonts/") || uri.contains("/js/")||uri.contains("/photos/")){
            //包含这些与登录相关的路径，表示用户就是要进行登录
            chain.doFilter(servletRequest, servletResponse);//放行
        }else {
            //如果不包含，表示用户不是去访问登录页面的，所以要进行验证用户是否登录，若已经登录，则放行；若没有登录，则提醒用户去登录
            Object user = request.getSession().getAttribute("loginUser");//将之前存入session中的用户信息取出来
            if(user!=null){
                //有用户信息，表示用户登录过
                chain.doFilter(servletRequest, servletResponse);//放行
            }else {
                //无用户信息，表示没有登录过，跳转到登录页面提醒用户登录
                request.setAttribute("login_error","你尚未登录，请先登录。");
                request.getRequestDispatcher("/login.jsp").forward(servletRequest,servletResponse);
            }

        }
    }

    public void destroy() {
    }
}
