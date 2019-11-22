package club.wlqzz.blog.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@WebFilter(urlPatterns ={"/user/*","/writeBlog","/diary"})
public class LoginFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(LoginFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response= (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        if(session.getAttribute("loginUser")!=null){
            filterChain.doFilter(servletRequest,servletResponse);
            logger.info("登录放行！");
        }
       else {
            request.getRequestDispatcher("/reg.html").forward(request,response);
            logger.info("登录已被拦截！");
        }
    }

    @Override
    public void destroy() {

    }
}
