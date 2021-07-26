package ro.msg.learning.shop.security_configuration;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ro.msg.learning.shop.services.impl.UserDetailService;
import ro.msg.learning.shop.utils.MyBasicAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
@Profile("with-basic")
@AllArgsConstructor
public class SecurityConfigurationBasicHTTP extends WebSecurityConfigurerAdapter {
    @Autowired
    private final UserDetailService userDetailService;
    @Autowired
    private final MyBasicAuthenticationEntryPoint authenticationEntryPoint;


    @Override
    protected void configure(AuthenticationManagerBuilder auth){
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
                httpSecurity
                .authorizeRequests()
                .anyRequest().fullyAuthenticated()
                .and()
                .httpBasic()
                        .authenticationEntryPoint(authenticationEntryPoint)
                .and()
                .csrf().disable();

    }

    @Bean
    DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(this.userDetailService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}

}