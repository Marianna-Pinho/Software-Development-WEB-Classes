package br.com.ufc.teste;

import br.com.ufc.dao.ProprietarioDAO;
import br.com.ufc.model.Proprietario;

public class TesteProprietario {
	public static void main(String[] args) {
		ProprietarioDAO proprietarioDAO = new ProprietarioDAO();
		Proprietario proprietario = new Proprietario();
		Proprietario proprietario2 = new Proprietario();
		
		proprietario.setNome("Florinda");
		proprietario.setTelefone("55555");
		proprietario.setEmail("florinda@gmail");
		
		proprietario2.setNome("Kiko");
		proprietario2.setTelefone("8888");
		proprietario2.setEmail("kiko@gmail");
		
		//Adicionar proprietarios
		proprietarioDAO.adicionarProprietario(proprietario);
		proprietarioDAO.adicionarProprietario(proprietario2);
		
		//Remover proprietario
		proprietarioDAO.removerProprietario(proprietario2.getCodigo());
		
		//Atualizar proprietario
		proprietario.setTelefone("7777");
		proprietarioDAO.atualizarProprietario(proprietario);
		
		//Listar proprietario
		Proprietario proprietario3 = proprietarioDAO.buscarProprietario(proprietario2.getCodigo());
		
	}

}
