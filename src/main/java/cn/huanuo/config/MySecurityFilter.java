package cn.huanuo.config;

import javax.annotation.PostConstruct;
import javax.servlet.*;

import cn.huanuo.service.sys.CustomInvocationSecurityMetadataSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MySecurityFilter extends AbstractSecurityInterceptor implements Filter {

    @Autowired
    CustomInvocationSecurityMetadataSourceService customInvocationSecurityMetadataSourceService;


    @Autowired
    CustomAccessDecisionManager customAccessDecisionManager;


    @Autowired
    AuthenticationManager authenticationManager;


    @PostConstruct
    public void init() {
        super.setAuthenticationManager(authenticationManager);
        super.setAccessDecisionManager(customAccessDecisionManager);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("---init---");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        FilterInvocation filterInvocation = new FilterInvocation(servletRequest, servletResponse, filterChain);

        invoke(filterInvocation);
    }

    public void invoke(FilterInvocation fi) throws IOException, ServletException {
        System.out.println("filter..........................");
        InterceptorStatusToken token = super.beforeInvocation(fi);
        try {
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        } finally {
            super.afterInvocation(token, null);
        }

    }

    @Override
    public void destroy() {
        System.out.println("---destory---");
    }

    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return this.customInvocationSecurityMetadataSourceService;
    }
}
