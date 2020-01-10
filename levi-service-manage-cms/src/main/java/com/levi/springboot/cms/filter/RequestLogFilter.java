package com.levi.springboot.cms.filter;

import com.levi.springboot.model.RequestLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author jianghaihui
 * @date 2019/9/20 16:53
 */
@WebFilter(filterName = "RequestLogFilter",urlPatterns = "/*")
public class RequestLogFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(RequestLogFilter.class);


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("init filter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long s = System.currentTimeMillis();
        RequestLog requestLog = new RequestLog();
        //包装request和response
        HttpServletRequest httpReq = (HttpServletRequest) servletRequest;
        HttpServletResponse httpRes = (HttpServletResponse)servletResponse;
        //拼接参数
        String url = httpReq.getRequestURI().toString();
        String query = url +"?"+getQueryParameter(httpReq);
        logger.info("======request timel requestUrl :");
    }

    private String getQueryParameter(HttpServletRequest request){
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<String,String[]> entry :request.getParameterMap().entrySet()){
            sb.append(entry.getKey()).append("=").append(entry).append("&");
        }
        return sb.toString().endsWith("$") ? sb.substring(0,sb.lastIndexOf("&")):sb.toString();
    }



    @Override
    public void destroy() {
        logger.info("destroy filter");
    }
}
