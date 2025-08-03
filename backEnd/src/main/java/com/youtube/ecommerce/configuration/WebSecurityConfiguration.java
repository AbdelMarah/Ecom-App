package com.youtube.ecommerce.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    private UserDetailsService jwtService;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors();
        httpSecurity.csrf().disable()
                .authorizeRequests().antMatchers("/authenticate", "/registerNewUser", "/getAllProducts", "/getProductDetailsById/{productId}").permitAll()
                .antMatchers(HttpHeaders.ALLOW).permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //utiliser Stateless et pas Statefull
        ;
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        //On peut utiliser ça :
        //httpSecurity.authorizeRequests().antMatchers(HttpMethod.GET,"/users/**").hasAuthority("USER");
        //httpSecurity.authorizeRequests().antMatchers(HttpMethod.POST,"/users/**").hasAuthority("ADMIN");
        //sinon : on ajoute l annotation "@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)" et configuerer ça dans ls Controllers

        //RefreshToken : on l'utilise si AccessTocke est expiré !!! pour ne pas utiliser les methodes standarts qui est fourni avrc SpringSecurity
        //=> c'est mieux pour prendre les nouvelles mais bp de travail pour le serveur si on demande chaque fois un AccessToken


        //httpSecurity.csrf().disable()
        //httpSecurity.headers().frameOptions().disable();
        //httpSecurity.authorizeRequests().anyRequest().permitAll();

        //httpSecurity.formLogin(); afficher une formulaire au début / pas utiliser dans Statefull

        //Amelioration : Creer un Class Util pour declarer tous les variables necessaires comme Expire_Access_Token en ms (5*60*1000=5min)(long)
        //OAuth2 : connecter a une application à l'aide de Facebook par exemple / keyCloack

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(jwtService).passwordEncoder(passwordEncoder());
        //https://www.youtube.com/watch?v=3q3w-RT1sg0&t=7080s
    }
}
