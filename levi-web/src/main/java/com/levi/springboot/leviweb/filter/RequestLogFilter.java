package com.levi.springboot.leviweb.filter;

import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;


@WebFilter(urlPatterns = "/*",filterName = "RequestLogFilter")
@Log4j2
public class RequestLogFilter implements Filter {
    private Logger logger =LoggerFactory.getLogger(RequestLogFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("request  logger init");
    }

    /**
     * 处理需要得路径
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long s = System.currentTimeMillis();
        RequestLog log = new RequestLog();

        // 包装request和response
        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpServletResponse httpResp = (HttpServletResponse) response;
        // 拼接参数
        String url = httpReq.getRequestURL().toString();
        String query = url + "?" + getQueryParameter(httpReq);
//		logger.info("===request time1 requestUrl:" + query + " time: " + getDateFormatString(s));
        // 根据uri去接口配置表里取name
        String uri = httpReq.getRequestURI();

        // 排除在外不用记录数据库的请求
        if (isExcludedPages(uri)) {
            try {
                chain.doFilter(httpReq, httpResp);
                return;
            } catch (Exception e1) {
                log.setErrorMsg(ExceptionUtil.getStackTrace(e1));
                throw e1;
            }
        }

        RequestWrapper reqWrapper = new RequestWrapper(httpReq);
        ResponseWrapper respWrapper = new ResponseWrapper(httpResp);

        List<APIConfig> apiControls = ResourceManager.getApiControls();
        Optional<APIConfig> optional = apiControls.stream().filter(a -> a.getApiUri().equals(uri)).findFirst();

        // 获取content
        ServletInputStream sis = reqWrapper.getInputStream();
        sis.mark(0);
        String content = IOUtils.toString(sis, CharsetConst.UTF_8.name());
        sis.reset();

        // 执行filter链
        try {
            chain.doFilter(reqWrapper, respWrapper);
        } catch (Exception e1) {
            log.setErrorMsg(ExceptionUtil.getStackTrace(e1));
            throw e1;
        }

        // 构建日志实体
        long e = System.currentTimeMillis();
//		logger.info("===request time2 requestUrl:" + query + " time: " + getDateFormatString(e));
        if (optional.isPresent()) {
            APIConfig apiConfig = optional.get();
            log.setApiConfigId(apiConfig.getId());
        }
        log.setInterfaceName(query);
        log.setInterfaceType(this.getClass().getSimpleName());
        log.setRequest(content);
        log.setProcessResult(httpResp.getStatus() + "");

        PrintWriter out = respWrapper.getSuperWriter();
        if (httpResp.getStatus() == HttpServletResponse.SC_OK) {
            // 获取用户的返回值
            String result1 = respWrapper.getWriteResult();
            String result2 = new String(respWrapper.getOutputStreamResult().toByteArray(), CharsetConst.UTF_8);
            String result = StringUtil.isEmpty(result1) ? result2 : result1;

            log.setResponse(result);

            // 输出最终的结果
            out.write(result);
        } else {
            log.setResponse(null);
        }
        out.flush();
        out.close();

//		logger.info("===request time3 requestUrl:" + query + " time: " + getDateFormatString(System.currentTimeMillis()));
        log.setProcessTime(e - s);
        if (requestLogService == null) {
            requestLogService = ApplicationContextUtils.getBean(RequestLogService.class);
        }
        requestLogService.save(log);
    }

    @Override
    public void destroy() {
        logger.info("request  logger destroy");
    }
}
