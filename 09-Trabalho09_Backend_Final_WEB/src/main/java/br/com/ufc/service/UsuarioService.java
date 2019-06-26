package br.com.ufc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.ufc.model.Role;
import br.com.ufc.model.Usuario;
import br.com.ufc.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public void cadastrarUsuario(Usuario usuario) {
		
		usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
		
		//Com isso, sempre que uma nova pessoa se cadastrar, dizemos que seu papel é de usuário
		Role role = new Role();
		role.setPapel("ROLE_CLIENT");
		
		//Como uma pessoa pode ter mais de um papel, adicionamos o novo papel ao final da lista de papeis
		List<Role>roles = usuario.getRoles();
		if(roles ==  null) {
			roles = new ArrayList<Role>();
		}
		roles.add(role);
		
		usuario.setRoles(roles);
		
		usuarioRepository.save(usuario);
	}
	
	public Usuario buscarPorLogin(String username) {
		return usuarioRepository.findByLogin(username);
	}
	
}
