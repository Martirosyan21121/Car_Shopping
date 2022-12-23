package com.tesla.reposervicesecurity.configuration;


import com.tesla.reposervicesecurity.security.BeforeAuthenticationFilter;
import com.tesla.reposervicesecurity.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@ComponentScan(value = {"com.tesla.reposervicesecurity"})
@Configuration
@EnableWebSecurity
public class SecurConfig {

//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    private UserDetailsServiceImpl userDetailsService;


    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.GET, "/admin111")
                .hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/account")
                .hasAnyAuthority("ADMIN", "USER")
                .antMatchers(HttpMethod.GET, "/message")
                .hasAnyAuthority("ADMIN", "USER")
                .antMatchers(HttpMethod.GET, "/adminMessage")
                .hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/addCars")
                .hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST, "/addCars/add")
                .hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST, "/send")
                .hasAnyAuthority("ADMIN", "USER")
                .antMatchers(HttpMethod.GET, "/adminMessage")
                .hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/adminMessage")
                .hasAuthority("ADMIN")
                .antMatchers("/login").permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
//                .addFilterBefore(getCustomFilter(), BeforeAuthenticationFilter.class)
                .formLogin();
//                .loginPage("/login")
//                .defaultSuccessUrl("/");
        return http.build();
    }

//    @Bean
//    protected AuthenticationManagerBuilder configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder);
//    }
//
//
//    private BeforeAuthenticationFilter getCustomFilter() throws Exception {
//        BeforeAuthenticationFilter filter = new BeforeAuthenticationFilter();
//        filter.setAuthenticationManager(au);
//        return filter;
//    }
}
