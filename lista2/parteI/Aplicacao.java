package parteI;

import java.io.IOException;

public class Aplicacao {
	
	private static Cadastros cadastro = new Cadastros();

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		String arquivo = "paciente.dat";
		Paciente p = new Paciente(2, "Alice", "Rua dos Bobos", Long.parseLong("3133332222"));
		cadastro.cadastrar(p);
		p = new Paciente(2, "Filipe", "Rua dos Bobos", Long.parseLong("3133332222"));
		cadastro.cadastrar(p);
		ArquivoDeDadosLeitura leitura = new ArquivoDeDadosLeitura();
		leitura.openFile(arquivo);
		Paciente a;
		while ((a = (Paciente) leitura.getData()) != null) {
			System.out.println(a.getCodigo() + " - " + a.getNome());
		}
		leitura.closeFile();
	}
}