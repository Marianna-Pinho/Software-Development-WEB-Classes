package br.com.ufc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ufc.model.Item;
import br.com.ufc.repository.ItemRepository;

@Service
public class ItemService {

	@Autowired
	private ItemRepository itemRepository;
	
	public void cadastrarItem(Item item) {
		itemRepository.save(item);
	}
	
	public List<Item> listarItens(Long pedidoId){
		List<Item> itensPedido = new ArrayList<Item>();
		
		//Pega todos os itens de um determinado pedido
		for(Item item : itemRepository.findAll()) {
			if(item.getPedido().getCodigo() == pedidoId) {
				itensPedido.add(item);
			}
		}
		
		return itensPedido;
	}
}
