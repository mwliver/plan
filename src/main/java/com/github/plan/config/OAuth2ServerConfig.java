package com.github.plan.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

@Configuration
public class OAuth2ServerConfig {
    protected static final String RESOURCE_ID = "oauthdemo";

    @Configuration
    @EnableResourceServer
    protected static class ResourceServer extends
            ResourceServerConfigurerAdapter {

        @Autowired
        private TokenStore tokenStore;

        @Override
        public void configure(ResourceServerSecurityConfigurer resources)
                throws Exception {
            resources.tokenStore(tokenStore).resourceId(RESOURCE_ID);
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            // http.requestMatchers().antMatchers("/resources/**").and()
            // .authorizeRequests().anyRequest()
            // .access("#oauth2.hasScope('read')");

            http.authorizeRequests().anyRequest().permitAll();
        }
    }

    @Configuration
    @EnableAuthorizationServer
    protected static class OAuth2Config extends
            AuthorizationServerConfigurerAdapter {

        @Autowired
        private DataSource dataSource;

        @Autowired
        private AuthenticationManager authenticationManager;

        @Bean
        public JdbcTokenStore tokenStore() {
            return new JdbcTokenStore(dataSource);
        }

        @Bean
        protected AuthorizationCodeServices authorizationCodeServices() {
            return new JdbcAuthorizationCodeServices(dataSource);
        }

        @Override
        public void configure(AuthorizationServerSecurityConfigurer oauthServer)
                throws Exception {
            oauthServer.allowFormAuthenticationForClients();
        }

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints)
                throws Exception {
            endpoints.authorizationCodeServices(authorizationCodeServices())
                    .authenticationManager(authenticationManager)
                    .tokenStore(tokenStore()).approvalStoreDisabled();
        }

        @Override
        public void configure(ClientDetailsServiceConfigurer clients)
                throws Exception {
            clients.inMemory().withClient("client")
                    .authorizedGrantTypes("password", "refresh_token", "authorization_code")
                    .authorities("ROLE_USER").scopes("read")
                    .resourceIds(RESOURCE_ID).secret("secret")
                    .accessTokenValiditySeconds(3600);
        }
    }
//	@Configuration
//	@Order(Ordered.LOWEST_PRECEDENCE - 20)
//	protected static class AuthenticationManagerConfiguration extends
//			GlobalAuthenticationConfigurerAdapter {
//		@Autowired
//		private DataSource dataSource;
//
//		@Autowired
//		private CustomUserDetailsService userDetailsService;
//
//		@Override
//		public void init(AuthenticationManagerBuilder auth) throws Exception {
//			// @formatter:off
//			auth.jdbcAuthentication().dataSource(dataSource);
////			.withUser("dave")
////					.password("secret").roles("USER");
//			// @formatter:on
//		}
//	}
}