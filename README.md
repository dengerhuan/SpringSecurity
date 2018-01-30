# SpringSecurity
> Spring Boot 整合  SpringSecurity
主要有

- 自定义过滤器，代替原有的FilterSecurityInterceptor过滤器，
- 实现 UserDetailsService （储存用户所有角色） 
- InvocationSecurityMetadataSourceService（访问资源所需要的角色集合） 
- AccessDecisionManager（判断用户请求的资源 是否能通过）
