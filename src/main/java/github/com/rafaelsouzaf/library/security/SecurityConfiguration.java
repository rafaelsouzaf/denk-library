package github.com.rafaelsouzaf.library.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableGlobalMethodSecurity(
    securedEnabled = true,
    jsr250Enabled = true,
    prePostEnabled = true
)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and().csrf().disable();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        System.out.println("================================ " + passwordEncoder().encode("password")); // $2a$10$XOhN2CvVncf0FThogN4QA.vJj3hfdiYaXlFLlRKJqYL1JhDPBVpYa

//        auth
//                .jdbcAuthentication()
//                .dataSource(dataSource)
//                .passwordEncoder(passwordEncoder())
//                .usersByUsernameQuery("SELECT email as username, password FROM lib_user where email=?")
//                .authoritiesByUsernameQuery("SELECT users.email as username,user_role.role_code as user_role FROM users inner join user_role on users.user_id=user_role.user_id where users.email=?")
//                .authoritiesByUsernameQuery("SELECT email as username, 'ADMIN' as user_role FROM lib_user where email=?");
//                .and()
//                .inMemoryAuthentication()
//                .passwordEncoder(null)
//                .withUser("user").password(passwordEncoder().encode("password")).roles("USER").and()
//                .withUser("admin").password(passwordEncoder().encode("password")).roles("ADMIN");


        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select email as username, password, true as enabled from lib_user where email=?")
                .authoritiesByUsernameQuery("select email as username, 'ROLE_ADMIN' as authority from lib_user where email=?")
                .passwordEncoder(new BCryptPasswordEncoder());

    }


}