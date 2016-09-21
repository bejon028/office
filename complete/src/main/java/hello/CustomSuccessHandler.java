package hello;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Taqwa It on 9/21/2016.
 */
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

    private final String defaultTargetUrl;

    public CustomSuccessHandler(String defaultTargetUrl){
        this.defaultTargetUrl = defaultTargetUrl;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        if(request.getAttribute("token")!=null){
            System.out.println("inside custom authenticationSuccess");
            response.setHeader("token",(String)request.getAttribute("token"));
            System.out.println(response.toString());
        }
        response.sendRedirect(request.getContextPath()+defaultTargetUrl);
    }
}
