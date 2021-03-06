package it.prova.gestionepermessi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
    private CustomAuthenticationSuccessHandlerImpl successHandler;
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
 
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
         .userDetailsService(customUserDetailsService);
         //.passwordEncoder(passwordEncoder());
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	 http.authorizeRequests()
         .antMatchers("/assets/**").permitAll()
         .antMatchers("/login").permitAll()
         .antMatchers("/permesso/insert", "/permesso/edit", "/permesso/delete").hasAnyRole("DIPENDENTE_USER")
         .antMatchers("/utente/list", "/utente/search", "/utente/show/**").hasAnyRole("BO_USER", "ADMIN")
         .antMatchers("/dipendente/list", "/dipendente/search", "/dipendente/show/**").hasAnyRole("ADMIN", "BO_USER")
         .antMatchers("/messaggio/**").hasAnyRole("BO_USER")
         .antMatchers("/dipendente/**").hasAnyRole("BO_USER")
         .antMatchers("/utente/**").hasAnyRole("ADMIN")
         .antMatchers("/permesso/**").hasAnyRole("BO_USER", "DIPENDENTE_USER")
         .antMatchers("/**").hasAnyRole("BO_USER", "ADMIN", "DIPENDENTE_USER")
         //.antMatchers("/anonymous*").anonymous()
         .anyRequest().authenticated()
         .and().exceptionHandling().accessDeniedPage("/accessDenied")
         .and()
         	.formLogin()
         	.loginPage("/login")
         	//.defaultSuccessUrl("/home",true)
         	//uso un custom handler perch?? voglio mettere delle user info in session
         	.successHandler(successHandler)
			.failureUrl("/login?error=true")
         	.permitAll()
         .and()
         	.logout()
         	.logoutSuccessUrl("/executeLogout")
            .invalidateHttpSession(true)
            .permitAll()
         .and()
            .csrf()
            .disable();
    }
}
