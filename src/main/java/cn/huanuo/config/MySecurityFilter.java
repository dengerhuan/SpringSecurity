package cn.huanuo.config;

import javax.annotation.PostConstruct;
import javax.servlet.*;

import cn.huanuo.service.sys.CustomInvocationSecurityMetadataSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
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


    @Autowired
    private ClientDetailsService clientDetailsService;

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

    @Bean
    TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }

    @Bean
    @Autowired
    public TokenStoreUserApprovalHandler userApprovalHandler(TokenStore tokenStore){
        TokenStoreUserApprovalHandler handler = new TokenStoreUserApprovalHandler();
        handler.setTokenStore(tokenStore);
        handler.setRequestFactory(new DefaultOAuth2RequestFactory(clientDetailsService));
        handler.setClientDetailsService(clientDetailsService);
        return handler;
    }

    @Bean
    @Autowired
    public ApprovalStore approvalStore(TokenStore tokenStore) throws Exception {
        TokenApprovalStore store = new TokenApprovalStore();
        store.setTokenStore(tokenStore);
        return store;
    }
}
