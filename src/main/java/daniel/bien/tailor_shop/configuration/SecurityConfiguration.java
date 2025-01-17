package daniel.bien.tailor_shop.configuration;

import daniel.bien.tailor_shop.model.user.RoleName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtFilter jwtFilter;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/users/authenticate").permitAll()
//                .antMatchers(HttpMethod.POST,"/customers").permitAll()
//                .antMatchers(HttpMethod.POST,"/employees").permitAll()
//                .antMatchers(HttpMethod.POST,"/textiles").hasAnyRole("EMPLOYEE", "MANAGER")
//                .antMatchers(HttpMethod.POST, "/visits/addWeek").hasAnyRole("EMPLOYEE", "MANAGER")
//                .antMatchers(HttpMethod.POST, "/visits/add").hasAnyRole("EMPLOYEE", "MANAGER")
//                .antMatchers("/order/{id}/assign").hasRole("MANAGER")
                .anyRequest().permitAll()
                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }
}

