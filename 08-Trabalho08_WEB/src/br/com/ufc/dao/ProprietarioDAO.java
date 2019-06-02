package br.com.ufc.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.ufc.model.Proprietario;

public class ProprietarioDAO {

	public void adicionarProprietario(Proprietario proprietario) {
		EntityManagerFactory fabric = Persistence.createEntityManagerFactory("mapeamento-jpa-hibernate");
		EntityManager manager = fabric.createEntityManager();
		
		manager.getTransaction().begin();
		manager.persist(proprietario);
		manager.getTransaction().commit();
		
		fabric.close();
		manager.close();
	}
	
	public void removerProprietario(Long codigo) {
		EntityManagerFactory fabric = Persistence.createEntityManagerFactory("mapeamento-jpa-hibernate");
		EntityManager manager = fabric.createEntityManager();
		
		Proprietario proprietario = manager.find(Proprietario.class, codigo);
		
		manager.getTransaction().begin();
		manager.remove(proprietario);
		manager.getTransaction().commit();
		
		fabric.close();
		manager.close();
	}
	
	public void atualizarProprietario(Proprietario proprietario) {
		EntityManagerFactory fabric = Persistence.createEntityManagerFactory("mapeamento-jpa-hibernate");
		EntityManager manager = fabric.createEntityManager();
		
		manager.getTransaction().begin();
		manager.merge(proprietario);
		manager.getTransaction().commit();
		
		fabric.close();
		manager.close();
	}
	
	public Proprietario buscarProprietario(Long codigo) {
		EntityManagerFactory fabric = Persistence.createEntityManagerFactory("mapeamento-jpa-hibernate");
		EntityManager manager = fabric.createEntityManager();
		
		Proprietario proprietario = manager.find(Proprietario.class, codigo);
		
		fabric.close();
		manager.close();
		
		return proprietario;
	}
	
}
