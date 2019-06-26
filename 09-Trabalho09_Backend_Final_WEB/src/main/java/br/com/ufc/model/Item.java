package br.com.ufc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long codigo;
	
	private Long pratoId;
	
	@Transient
	private Prato prato;
	
	private Integer quantidade;
	
	@ManyToOne()
	@JoinColumn(name = "pedido_id")
	private Pedido pedido;
	
	public Item() {
	}

	public Item(Long pratoId, int quantidade) {
		this.pratoId = pratoId;
		this.quantidade = quantidade;
	}
	
	public Item(Prato prato, int quantidade) {
		this.prato = prato;
		this.quantidade = quantidade;
	}
	
	
	
	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Prato getPrato() {
		return prato;
	}

	public void setPrato(Prato prato) {
		this.prato = prato;
	}

	public Long getPratoId() {
		return pratoId;
	}

	public void setPratoId(Long pratoId) {
		this.pratoId = pratoId;
	}
	
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	
}
