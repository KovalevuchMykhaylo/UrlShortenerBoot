package com.shortener.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("userDetailsService")
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(this.userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .usernameParameter("login")
                .passwordParameter("password")
                .loginPage("/login")
                .failureUrl("/login?fail=true")
                .defaultSuccessUrl("/user/shortUrl")
                .and().logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .deleteCookies("JSESSIONID", "My_Super_Secret_Pechenko")
                .and()
                .authorizeRequests()
                .antMatchers("/user").hasRole("USER")
                .antMatchers("/user/**").hasRole("USER")
                .anyRequest().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/")
                .and()
                .sessionManagement().maximumSessions(1)
                .and().and()
                .rememberMe()
                .rememberMeParameter("rememberMe")
                .key("my super secret key")
                .rememberMeCookieName("My_Super_Secret_Pechenko");
    }

    @Override
    public void configure(WebSecurity security) {
        security.ignoring().antMatchers("/resources/**");
    }

}
