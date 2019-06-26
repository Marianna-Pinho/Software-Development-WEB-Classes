package br.com.ufc.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.ufc.model.Item;
import br.com.ufc.model.Pedido;
import br.com.ufc.model.Prato;
import br.com.ufc.model.Usuario;
import br.com.ufc.service.ItemService;
import br.com.ufc.service.PedidoService;
import br.com.ufc.service.PratoService;
import br.com.ufc.service.UsuarioService;

@Controller
@RequestMapping("/restaurante")
public class PedidoController {
	
	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private PratoService pratoService;
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	
	//Mostra os itens do pedido atual
	@RequestMapping("/carrinhoCompras")
	public ModelAndView carrinhoCompras(HttpSession session) {
		ModelAndView mv = new ModelAndView("PedidosCarrinho");
		
		//Se houver um pedido
		if(session.getAttribute("pedido") != null) {
			List<Item> pedido = (List<Item>) session.getAttribute("pedido");
			List<Item> carrinho = new ArrayList<Item>();
			double valorTotal = 0;
			
			//Pega todos os pratos do pedido atual
			for (Item item: pedido) {
				Prato prato = pratoService.buscarPorId(item.getPratoId());
				carrinho.add(new Item(prato, item.getQuantidade()));
				
				//Calcula o valor total
				valorTotal += prato.getPreco()*item.getQuantidade();
			}
			
			mv.addObject("listaPratos", carrinho);
			mv.addObject("valorTotal", valorTotal);
		}

		return mv;
	}
	
	//Cria e atualiza o carrinho
	@RequestMapping("/adicionarCarrinho/{codigo}")
	public ModelAndView adicionarCarrinho(@PathVariable Long codigo, HttpSession session) {
		ModelAndView mv = new ModelAndView("redirect:/restaurante/carrinhoCompras");
		
		
		//Não existe um carrinho
		if(session.getAttribute("pedido") == null) {
			List<Item> pedido = new ArrayList<Item>();
			pedido.add(new Item(codigo, 1));
			session.setAttribute("pedido", pedido);
		}else {
			List<Item> pedido = (List<Item>) session.getAttribute("pedido");
			int indice = this.existeItem(codigo, pedido);
			
			//Já existe um carrinho, mas aquele prato ainda não foi adicionado
			if(indice == -1) {
				pedido.add(new Item(codigo, 1));
			}else {
				//Existe o carrinho e o prato foi pedido mais de uma vez
				int qtd = pedido.get(indice).getQuantidade() + 1;
				pedido.get(indice).setQuantidade(qtd);
			}
			
			session.setAttribute("pedido", pedido);
		}
		
		return mv;
	}
	
	//Remove pedido do carrinho
	@RequestMapping("/removerCarrinho/{codigo}")
	public ModelAndView removerCarrinho(@PathVariable Long codigo, HttpSession session) {
		ModelAndView mv = new ModelAndView("redirect:/restaurante/carrinhoCompras");
		
		List<Item> pedido = (List<Item>) session.getAttribute("pedido");
		int indice = this.existeItem(codigo, pedido);
		
		pedido.remove(indice);
		
		if(pedido.isEmpty()) {
			session.removeAttribute("pedido");
		}else {
			session.setAttribute("pedido", pedido);
		}
		
		
		return mv;
	}
	
	//Mostra o formulário de endereço
	@RequestMapping("/confirmarPedido")
	public ModelAndView confirmarPedido(HttpSession session) {
		ModelAndView mv =  new ModelAndView("ConfirmarPedido");
		
		if(session.getAttribute("pedido") != null) {
			Object auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			UserDetails user = (UserDetails) auth;
			Usuario usuario = usuarioService.buscarPorLogin(user.getUsername());
			
			mv.addObject("usuario", usuario);
		}else {
			mv = new ModelAndView("PedidosCarrinho");
			mv.addObject("mensagem", "Não há itens adicionados!");
		}
				
		return mv;
	}
	
	@RequestMapping("/salvarPedido")
	public ModelAndView salvarPedido(String endereco, HttpSession session ) {
		
		if(endereco.isEmpty()) {
			ModelAndView mv =  new ModelAndView("ConfirmarPedido");
			mv.addObject("mensagem", "Adicione um endereço!");
			return mv;
		}
		
		ModelAndView mv =  new ModelAndView("redirect:/restaurante/historicoPedidos");
		Pedido pedido = new Pedido();
		
		if(session.getAttribute("pedido") != null) {
			pedido.setItems((List<Item>)session.getAttribute("pedido"));
			
			Object auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			UserDetails user = (UserDetails) auth;
			Usuario usuario = usuarioService.buscarPorLogin(user.getUsername());

			pedido.setUsuario(usuario);
			
			pedidoService.cadastrarPedido(pedido);
			
			for(Item item : pedido.getItems()) {
				item.setPedido(pedido);
				itemService.cadastrarItem(item);
			}
			
		}

		session.removeAttribute("pedido");
		
		return mv;
	}
	
	@RequestMapping("/historicoPedidos")
	public ModelAndView historicoPedidos() {
		ModelAndView mv = new ModelAndView("HistoricoPedidos");
		
		//Pega o usuário da sessão
		Object auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails user = (UserDetails) auth;
		Usuario usuario = usuarioService.buscarPorLogin(user.getUsername());
		
		List<Pedido> pedidos = pedidoService.listarPedidosUser(usuario.getCodigo());
		List<Item> items = new ArrayList<Item>();
		List<Prato> pratos = new ArrayList<Prato>();
		
		for(Pedido pedido:pedidos) {
			for(Item item: pedido.getItems()) {
				items.add(item);
				pratos.add(pratoService.buscarPorId(item.getPratoId()));
			}
		}
		
		if(pedidos != null) {
			mv.addObject("listaPedidos", pedidos);
			mv.addObject("listaItens", items);
			mv.addObject("listaPratos", pratos);
		}
		
		return mv;
	}
	
	
	private int existeItem(Long codigo, List<Item> pedido) {
		for(int i = 0; i < pedido.size(); i++) {
			if(pedido.get(i).getPratoId() == codigo) {
				return i;
			}
		}
		
		return -1;
	}
	
	
}
