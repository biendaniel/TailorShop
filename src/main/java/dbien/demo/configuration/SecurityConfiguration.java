package dbien.demo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;

@Configuration
//@EnableWebSecurity
@EnableTransactionManagement
@EnableSpringConfigured
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {



//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**").allowCredentials(true);
//            }
//        };
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Override
//    protected void configure(final HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeRequests()
//                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//                .antMatchers("/customers/registration/new").permitAll()
//                .antMatchers("/users/authenticate").permitAll()
//                .antMatchers("/register/new").permitAll()
//                .antMatchers("/customers/login/social").permitAll()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .httpBasic();
//    }


//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication()
//                .dataSource(dataSource)
//                .usersByUsernameQuery("select username,password,enabled from user where username=?")
//                .authoritiesByUsernameQuery("select U.username, R.name as 'role' from user U left join role R on U.role_id=R.id where U.username=?")
//                .passwordEncoder(new BCryptPasswordEncoder());
//    }
}
