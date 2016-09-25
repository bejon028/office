package hello;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * Created by Taqwa It on 9/25/2016.
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/greeting/**").authenticated()
                .antMatchers(HttpMethod.POST, "/api/**").authenticated()
                .antMatchers(HttpMethod.PUT, "/api/**").authenticated()
                .antMatchers(HttpMethod.DELETE, "/api/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .httpBasic().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("ram").password("ram123").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("ravan").password("ravan123").roles("USER");
        auth.inMemoryAuthentication().withUser("kans").password("kans123").roles("USER");
    }
}

//check this
//package com.nuvola.myproject.server.spring;
//
//        import org.springframework.beans.factory.annotation.Autowired;
//        import org.springframework.context.annotation.Bean;
//        import org.springframework.context.annotation.ComponentScan;
//        import org.springframework.context.annotation.Configuration;
//        import org.springframework.security.authentication.AuthenticationManager;
//        import org.springframework.security.authentication.AuthenticationProvider;
//        import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//        import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
//        import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//        import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//        import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//        import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//        import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//        import org.springframework.security.core.userdetails.UserDetailsService;
//        import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//        import com.nuvola.myproject.server.security.AuthFailureHandler;
//        import com.nuvola.myproject.server.security.AuthSuccessHandler;
//        import com.nuvola.myproject.server.security.HttpAuthenticationEntryPoint;
//        import com.nuvola.myproject.server.security.HttpLogoutSuccessHandler;
//        import com.nuvola.myproject.server.security.NuvolaUserDetailsService;
//        import com.nuvola.myproject.shared.Parameters;
//        import com.nuvola.myproject.shared.ResourcePaths;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true)
//@ComponentScan(value = "com.nuvola.**.security")
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//    private static final String LOGIN_PATH = ResourcePaths.User.ROOT + ResourcePaths.User.LOGIN;
//
//    @Autowired
//    private NuvolaUserDetailsService userDetailsService;
//    @Autowired
//    private HttpAuthenticationEntryPoint authenticationEntryPoint;
//    @Autowired
//    private AuthSuccessHandler authSuccessHandler;
//    @Autowired
//    private AuthFailureHandler authFailureHandler;
//    @Autowired
//    private HttpLogoutSuccessHandler logoutSuccessHandler;
//
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    @Bean
//    @Override
//    public UserDetailsService userDetailsServiceBean() throws Exception {
//        return super.userDetailsServiceBean();
//    }
//
//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setUserDetailsService(userDetailsService);
//        authenticationProvider.setPasswordEncoder(new ShaPasswordEncoder());
//
//        return authenticationProvider;
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(authenticationProvider());
//    }
//
//    @Override
//    protected AuthenticationManager authenticationManager() throws Exception {
//        return super.authenticationManager();
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/").permitAll()
//                .antMatchers("/MyProject/*").permitAll()
//                .antMatchers("/favicon.ico").permitAll()
//                .antMatchers("/index.html").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .authenticationProvider(authenticationProvider())
//                .exceptionHandling()
//                .authenticationEntryPoint(authenticationEntryPoint)
//                .and()
//                .formLogin()
//                .permitAll()
//                .loginProcessingUrl(LOGIN_PATH)
//                .usernameParameter(Parameters.USERNAME)
//                .passwordParameter(Parameters.PASSWORD)
//                .successHandler(authSuccessHandler)
//                .failureHandler(authFailureHandler)
//                .and()
//                .logout()
//                .permitAll()
//                .logoutRequestMatcher(new AntPathRequestMatcher(LOGIN_PATH, "DELETE"))
//                .logoutSuccessHandler(logoutSuccessHandler)
//                .and()
//                .sessionManagement()
//                .maximumSessions(1);
//
//        http.authorizeRequests().anyRequest().authenticated();
//    }
//}

