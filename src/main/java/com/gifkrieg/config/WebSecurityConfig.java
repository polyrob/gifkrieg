package com.gifkrieg.config;


import com.gifkrieg.handler.AuthSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;


/**
 * Created by Rob on 4/2/2017.
 */
@Configuration
@EnableWebSecurity
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new AuthSuccessHandler();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

//    @Override
//    public void configure(WebSecurity web) {
//        web.ignoring().antMatchers("/resources/**", "/js/**", "/css/**", "/img/**", "/fonts/**", "/libs/**");
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                csrf().disable().
                authorizeRequests().
                antMatchers(HttpMethod.GET, "/api/pub/**").permitAll().
                antMatchers(HttpMethod.GET, "/api/login").permitAll().
                antMatchers(HttpMethod.POST, "/api/register").permitAll().
                antMatchers(HttpMethod.GET, "/api/logout").authenticated().
                antMatchers(HttpMethod.GET, "/api/**").hasAuthority("USER").
                antMatchers(HttpMethod.POST, "/api/**").hasAuthority("USER").
                antMatchers(HttpMethod.GET, "/admin/**").hasAuthority("ADMIN").
                antMatchers(HttpMethod.POST, "/admin/**").hasAuthority("ADMIN").
                anyRequest().permitAll().

                and().formLogin().permitAll().and().
                logout().
//                .authorizeRequests()
//                .antMatchers("/", "/login.html", "logout", "/register.html", "/register", "/api/leaderboard", "/leaderboard.html", "/home.html", "/user").permitAll() // #4
//                //.antMatchers("/api/**").hasRole("USER") // #6
//                .anyRequest().authenticated()
                and().httpBasic();

//                .formLogin()  // #8
//                .loginPage("/login") // #9
//                .permitAll(); // #5
    }


}