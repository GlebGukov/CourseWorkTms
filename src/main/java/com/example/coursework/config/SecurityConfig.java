package com.example.coursework.config;

import com.example.coursework.security.MyHandler;
import com.example.coursework.service.impl.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true,jsr250Enabled = true)
public class SecurityConfig {

    public final UserDetailsServiceImpl userDetailsServiceImpl;
    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
        security.csrf().disable()
                .authorizeRequests()
                .antMatchers("/","/registration").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .userDetailsService(userDetailsServiceImpl)
                .formLogin().loginPage("/login").permitAll().defaultSuccessUrl("/")
                .and()
                .exceptionHandling().accessDeniedHandler(new MyHandler())
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout","POST"))
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/");
        return security.build();
    }


}
