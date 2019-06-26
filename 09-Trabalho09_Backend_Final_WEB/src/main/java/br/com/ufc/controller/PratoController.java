package br.com.ufc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.com.ufc.model.Prato;
import br.com.ufc.service.PratoService;

@Controller
@RequestMapping("/restaurante")
public class PratoController {
	
	@Autowired
	private PratoService pratoService;
	
	
	//Formulario de cadastro de prato
	@RequestMapping("/formularioPrato")
	public ModelAndView formularioPrato(Prato prato) {
		ModelAndView mv = new ModelAndView("FormularioPrato");
		mv.addObject("prato", prato);
		
		return mv;
	}
	
	@RequestMapping("/salvarPrato")
	public ModelAndView salvarPrato(@Validated Prato prato, BindingResult result, @RequestParam(value="imagem") MultipartFile imagem) {
		ModelAndView mv = new ModelAndView("FormularioPrato");
		
		if(result.hasErrors()) {
			return formularioPrato(prato);
		}
		
		mv.addObject("mensagem", "Prato cadastrado com sucesso!");
		pratoService.cadastrarPrato(prato, imagem);
		
		return mv;
	}
	
	@RequestMapping("/listarPratos")
	public ModelAndView listarPratos() {
		ModelAndView mv = new ModelAndView("ListagemPrato");
		
		List<Prato> pratos = pratoService.listarPratos();
		mv.addObject("listaDePratos", pratos);
		
		return mv;
	}
	
	@RequestMapping("/removerPrato/{codigo}")
	public ModelAndView removerPrato(@PathVariable Long codigo) {
		ModelAndView mv = new ModelAndView("redirect:/restaurante/listarPratos");
		pratoService.removerPrato(codigo);
		
		return mv;
	}
	
	@RequestMapping("/atualizarPrato/{codigo}")
	public ModelAndView atualizarPrato(@PathVariable Long codigo) {
		ModelAndView mv = new ModelAndView("FormularioPrato");
		
		Prato prato = pratoService.buscarPorId(codigo);
		mv.addObject("prato", prato);
		
		return mv;
	}
}
