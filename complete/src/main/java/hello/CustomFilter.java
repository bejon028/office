package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by Taqwa It on 9/21/2016.
 */
@Component
public class CustomFilter implements Filter {

    @Autowired
    AuthenticationManager authenticationManager;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException{

        HttpServletRequest httpServletRequest=null;
        if(request instanceof HttpServletRequest){
            System.out.println("inside custom filter");
            httpServletRequest = (HttpServletRequest)request;
            httpServletRequest.setAttribute("token","101010");
            System.out.println("inside custom filter"+httpServletRequest.getAttribute("token"));
        }
        chain.doFilter(httpServletRequest,response);
    }

    @Override
    public void destroy() {

    }
}
