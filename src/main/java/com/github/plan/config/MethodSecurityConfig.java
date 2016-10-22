package main.java.com.github.plan.config;

import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

//@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true)
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {
    // @Autowired
    // private SecurityConfig securityConfig;

    // @Override
    // protected MethodSecurityExpressionHandler createExpressionHandler() {
    // return new OAuth2MethodSecurityExpressionHandler();
    // }
}
