package exerc3;

public class Cliente {

	private int Numero;
	private String Nome;

	public Cliente(String nome, int numero) {
		this.Nome = nome;
		this.Numero = numero;
	}
	
	public String getNome() {
		return Nome;
	}
}
