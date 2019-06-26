package br.com.ufc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ufc.model.Pedido;
import br.com.ufc.repository.PedidoRepository;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	public void cadastrarPedido(Pedido pedido) {
		pedidoRepository.save(pedido);
	}
	
	public List<Pedido> listarPedidos(){
		return pedidoRepository.findAll();
	}
	
	public void removerPedido(Long codigo) {
		pedidoRepository.deleteById(codigo);
	}
	
	public Pedido buscarPorId(Long codigo) {
		return pedidoRepository.getOne(codigo);
	}
	
	public List<Pedido> listarPedidosUser(Long codigo){
		List<Pedido> pedidos = pedidoRepository.findAll();
		List<Pedido> pedidosUsuario = new ArrayList<Pedido>();
		
		
		//Pega todos os pedidos do usuario de código = codigo
		for (Pedido p : pedidos) {
			if(p.getUsuario().getCodigo() == codigo) {
				pedidosUsuario.add(p);
			}
		}
		
		return pedidosUsuario;
	}

}
