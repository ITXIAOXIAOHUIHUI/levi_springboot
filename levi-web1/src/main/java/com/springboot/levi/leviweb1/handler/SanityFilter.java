//package com.springboot.levi.leviweb1.handler;
//
//import com.google.common.collect.Sets;
//import com.springboot.levi.leviweb1.utils.SecurityUtil;
//import org.apache.commons.io.IOUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.util.Set;
//
///**
// * 明智的过滤器
// */
//@Component
//public final class SanityFilter implements Filter {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(SanityFilter.class);
//
//
//    @Override
//    public void init(FilterConfig filterConfig) {
//    }
//
//    static void addSourceToIgnore(Set<String> ignore) {
//        ignore.add("/favicon.ico");
//        ignore.add("/**/*.css");
//        ignore.add("/**/*.js");
//        ignore.add("/**/*.gif");
//        ignore.add("/**/*.png");
//        ignore.add("/**/*.jpg");
//        ignore.add("/**/*.ttf");
//    }
//
//    static void addSwaggerUrlToIgnore(Set<String> ignore) {
//        ignore.add("/swagger-ui.html");
//        ignore.add("/doc.html");
//        ignore.add("/doc");
//        ignore.add("/api");
//        ignore.add("/");
//        ignore.add("/**/swagger-ui.html");
//        ignore.add("/**/doc.html");
//        ignore.add("/swagger-resources/**");
//        ignore.add("/webjars/**");
//        ignore.add("/v2/**");
//    }
//
//
//
//
//    static final String SIGN_PARAM_NAME = "sign";
//
//    /**
//     * 开始拦截方法
//     *
//     * @param request
//     * @param response
//     * @param chain
//     * @throws IOException
//     * @throws ServletException
//     */
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response,
//                         FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//        String uri = httpServletRequest.getRequestURI();
//
//        String qType = ((HttpServletRequest) request).getHeader("Quicktron-type");
//        if (StringUtils.isNotBlank(qType) && qType.equals("download.excel")) {
//            LOGGER.info("Request header Quicktron-type is : {}", qType);
//            chain.doFilter(httpServletRequest, response);
//            return;
//        }
//
//
//        String reqBody = IOUtils.toString(httpServletRequest.getInputStream(), StandardCharsets.UTF_8);
//
//        String securityContent = SecurityUtil.md5sum(reqBody);
//
//        LOGGER.info("securityContent:{}",securityContent);
//
//        chain.doFilter(request, response);
//    }
//
//    @Override
//    public void destroy() {
//    }
//
//}
