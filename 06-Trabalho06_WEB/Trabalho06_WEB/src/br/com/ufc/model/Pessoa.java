package br.com.ufc.model;

public class Pessoa {
	private String nome;
	private String time;
	private String [] hobbies;
	
	public Pessoa(){
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String[] getHobbies() {
		return hobbies;
	}

	public void setHobbies(String[] hobbies) {
		this.hobbies = hobbies;
	}
	
	public String showPessoa() {
		String toString = "Nome: " + this.nome + "\nTime: " + this.time + "\nHobbies: ";
		for(String h: this.hobbies) {
			toString += h + " ";
		}
		
		return toString;
	}
}
