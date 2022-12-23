package com.tesla.reposervicesecurity.security;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


//@Slf4j
public class BeforeAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    public BeforeAuthenticationFilter() {
        super.setUsernameParameter("email");
        super.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String email = request.getParameter("email");
        System.out.println("attemptAuthentication email: " + email);
//        log.info("USER login email = " + email);
        return super.attemptAuthentication(request, response);
    }
}
