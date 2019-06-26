	package br.com.ufc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.ufc.model.Prato;
import br.com.ufc.model.Usuario;
import br.com.ufc.service.PratoService;
import br.com.ufc.service.UsuarioService;

@Controller
@RequestMapping("/restaurante")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private PratoService pratoService;
	
	@RequestMapping("/administrador")
	public ModelAndView formAdm() {
		ModelAndView mv = new ModelAndView("Administracao");
		
		return mv;
	}
	
	
	@RequestMapping("/paginaLogin")
	public ModelAndView paginaLogin() {
		ModelAndView mv = new ModelAndView("LoginCliente");
		
		return mv;		
	}
	
	@RequestMapping("/paginaInicial")
	public ModelAndView paginaInicial() {
		ModelAndView mv = new ModelAndView("PaginaInicial");
		
		List<Prato> pratos = pratoService.listarPratos();
		
		mv.addObject("listaPratos", pratos);
		
		return mv;
	}
	
	//formulario de cadastro de usuário
	@RequestMapping("/formularioUsuario")
	public ModelAndView formularioUsuario(Usuario usuario) {
		ModelAndView mv = new ModelAndView("FormularioUsuario");
		mv.addObject("usuario", usuario);
		
		return mv;
	}
	
	@RequestMapping("/salvarUsuario")
	public ModelAndView salvarUsuario(@Validated Usuario usuario, BindingResult result) {
		ModelAndView mv = new ModelAndView("FormularioUsuario");
		
		if(result.hasErrors()) {
			return mv;//formularioUsuario(usuario);
		}
		
		mv.addObject("mensagem", "Usuário cadastrado com sucesso!");
		usuarioService.cadastrarUsuario(usuario);
		
		return mv;
	}
	/*
	 * @RequestMapping("/") public ModelAndView formularioUsuario() { ModelAndView
	 * mv = new ModelAndView("PaginaInicial");
	 * 
	 * return mv; }
	 */

	
}
