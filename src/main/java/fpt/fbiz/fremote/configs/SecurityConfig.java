package fpt.fbiz.fremote.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//@EnableGlobalMethodSecurity(securedEnabled = true)
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/v2/api-docs", "/configuration/ui", "/swagger-resources/**",
                "/configuration/security", "/swagger-ui.html", "/webjars/**", "/v2/swagger.json"
        );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.addFilterBefore(tokenAuthorizationFilter(), BasicAuthenticationFilter.class).authorizeRequests()
//                .antMatchers("/public").permitAll()
//                .antMatchers("/private").authenticated()
//                .and().csrf().disable();
    }
}
