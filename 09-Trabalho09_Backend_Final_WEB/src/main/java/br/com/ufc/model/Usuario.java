package br.com.ufc.model;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class Usuario implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long codigo;
	
	@NotBlank(message = "preencha com seu nome")
	private String nome;
	
	@NotBlank(message = "preencha com seu cpf")
	private String cpf;
	
	@NotNull(message = "preencha com sua data de nascimento")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	
	@NotBlank(message = "preencha com seu endereço")
	private String endereco;
	
	//Login e senha
	@NotBlank(message = "preencha com seu email")
	private String login;
	
	@NotBlank(message = "preencha com sua senha")
	private String senha;
		
	//Mapeando usuários e papeis
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable( 
	    name = "usuarios_roles", 
	    joinColumns = @JoinColumn(
	    name = "usuario_codigo", referencedColumnName = "codigo"), 
	    inverseJoinColumns = @JoinColumn(
	    name = "role_codigo", referencedColumnName = "papel")
	)
	private List<Role> roles;

	//Mapeando usuario e pedidos
	@OneToMany(mappedBy = "usuario")
	private List<Pedido> pedidos;
	
	public void addPedido(Pedido pedido) {
		this.pedidos.add(pedido);
	}
	
	public List<Pedido> getPedidos() {
		return pedidos;
	}
	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	
	//==== Métodos de autenticação
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return (Collection<? extends GrantedAuthority>) this.roles;
	}
	
	@Override
	public String getPassword() {
		
		return this.senha;
	}
	@Override
	public String getUsername() {
		
		return this.login;
	}
	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}
	@Override
	public boolean isEnabled() {
		
		return true;
	}
	
}
