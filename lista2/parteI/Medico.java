package parteI;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Medico implements Serializable {
	private int codigo;
	private String nome;
	private long telefone;

	public Medico(int codigo, String nome, long telefone) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.telefone = telefone;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public long getTelefone() {
		return telefone;
	}

	public void setTelefone(long telefone) {
		this.telefone = telefone;
	}
}