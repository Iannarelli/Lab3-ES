package parteI;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Paciente implements Serializable {
	private int codigo;
	private String nome;
	private String endereco;
	private long telefone;

	public Paciente(int codigo, String nome, String endereco, long telefone) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.endereco = endereco;
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

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public long getTelefone() {
		return telefone;
	}

	public void setTelefone(long telefone) {
		this.telefone = telefone;
	}
}