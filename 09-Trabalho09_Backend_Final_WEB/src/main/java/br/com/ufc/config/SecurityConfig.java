package br.com.ufc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.ufc.security.UserDetailsServiceImplementacao;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserDetailsServiceImplementacao userDetailsImplementacao;
	
	//Faz as regras de negócio.
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//Padrão Builder
		http.csrf().disable().authorizeRequests()
		//.antMatchers("/").permitAll()
		.antMatchers("/restaurante/administrador").hasRole("ADMIN")
		.antMatchers("/restaurante/paginaInicial").permitAll()
		.antMatchers("/restaurante/listarPratos").permitAll()
		.antMatchers("/restaurante/formularioPrato").hasRole("ADMIN")
		.antMatchers("/restaurante/formularioUsuario").permitAll()
		.antMatchers("/restaurante/salvarUsuario").permitAll()
		.antMatchers("/restaurante/salvarPrato").hasRole("ADMIN")
		.antMatchers("/restaurante/carrinhoCompras").hasRole("CLIENT")
		.antMatchers("/restaurante/adicionarCarrinho").hasRole("CLIENT")
		.antMatchers("/restaurante/confirmarPedido").hasRole("CLIENT")
		.antMatchers("/restaurante/salvarPedido").hasRole("CLIENT")
		.antMatchers("/restaurante/historicoPedidos").hasRole("CLIENT")
		
		.anyRequest().authenticated()
		
		.and()
		.formLogin()
		.loginPage("/restaurante/paginaLogin").permitAll()
		.defaultSuccessUrl("/restaurante/paginaInicial").permitAll()
		
		.and()
		.logout()
		.logoutSuccessUrl("/restaurante/paginaInicial?logout")
		.permitAll();
		
	}
	
	//Faz a autenticação
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsImplementacao).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	//Ele é usado para permitir que usemos o css, o js e as imagens
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**","/js/**","/images/**","/img/**");
	}
}
