package Main;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig  {
    @Autowired
    private DataSource dataSource;
     
     
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
     
    
    @Bean
    public UserDetailsManager users(){
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
        users.setUsersByUsernameQuery("Select email, password, enabled from Registration where email=?");
        users.setAuthoritiesByUsernameQuery("Select email, authority from Registration where email=?");
        return users;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        .antMatchers("periodflow/login","/periodflow/tracker").authenticated()
        .anyRequest().permitAll()
        
			// .antMatchers("/periodflow/home", "/periodflow/PeriodGuide", "/periodflow/register").permitAll()
			// .anyRequest().authenticated()
        
        .and()
        .formLogin()
            .usernameParameter("email")
            .defaultSuccessUrl("/periodflow/home")
            .permitAll()
        .loginPage("/login")
        .permitAll()
        .and()
        .logout().logoutSuccessUrl("/login").permitAll();

        return http.build();
    }   
}
