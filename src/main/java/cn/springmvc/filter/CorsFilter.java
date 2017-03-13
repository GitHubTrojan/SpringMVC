package cn.springmvc.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Vincent Wang (王言斌)
 * @Email 751770735@qq.com
 * @Description 实现请求跨域转发
 * @version 1.0.0
 * @since 2017年3月8日 上午9:19:02
 */
public class CorsFilter implements Filter{

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        //	尝试通过过滤器实现请求转发
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String contextPath = req.getContextPath();
        String requestURI = req.getRequestURI();

//        System.out.println("项目名称: getContextPath: " + contextPath);
//        System.out.println("请求的URI: getRequestURI: " + requestURI.substring(contextPath.length()));

        String effectiveRequest = requestURI.substring(requestURI.indexOf(contextPath) + contextPath.length());
        System.out.println("有效请求: " + effectiveRequest);

        if (effectiveRequest != null && !"".equals(effectiveRequest)){
            ServletContext context2 = req.getServletContext().getContext("http://www.i-fully.cn/ilife");
            if (context2 != null) {
                System.out.println("转发后路径信息：getContextPath: " + context2);
                RequestDispatcher requestDispatcher;
                requestDispatcher = context2.getRequestDispatcher(effectiveRequest);
                requestDispatcher.forward(req, resp);
                System.out.println("转发执行未报错，显示...");
            }
            return;
        }
        chain.doFilter(request, response);

    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }

}