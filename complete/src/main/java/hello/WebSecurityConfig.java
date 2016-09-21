package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    AuthenticationManagerBuilder authenticationManagerBuilder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(new CustomFilter(), UsernamePasswordAuthenticationFilter.class)
            .authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .successHandler(getCustomerSuccessHandler())
                .and()
            .logout()
                .permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
    }


    @Bean
    public AuthenticationManager AuthenticationManagerBean() {
        try {
            return authenticationManagerBuilder.build();
        }catch (Exception ex){
            System.out.println("error creating AuthenticationManagerBean");
        }finally {
            return null;
        }
    }

    @Autowired
    public CustomFilter getFilter(){
        return new CustomFilter();
    }
    @Autowired
    public CustomSuccessHandler getCustomerSuccessHandler(){
        CustomSuccessHandler customSuccessHandler = new CustomSuccessHandler("/");
        return customSuccessHandler;
    }
}
