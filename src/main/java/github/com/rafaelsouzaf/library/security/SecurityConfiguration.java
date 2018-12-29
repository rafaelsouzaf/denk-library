package github.com.rafaelsouzaf.library.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
        /*
         * Disabling cookies. It's important to avoid the cookie sessions and with that
         * validate the username/password for each request. It's not the best but works well
         * with Basic Auth.
         */
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().
        /*
         * Disabling CSRF. It's necessary for use POST/PUT/DELETE http methods
         * with Basic Authentication without SSL.
         */
        httpBasic().and().csrf().disable();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select email as username, password, enabled from lib_user where email=?")
                .authoritiesByUsernameQuery("select email as username, user_role as authority from lib_user where email=?")
                .passwordEncoder(new BCryptPasswordEncoder());
    }

}