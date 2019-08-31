package parteI;

import java.io.IOException;

public class Cadastros {

	public void cadastrar(Paciente p) throws IOException, ClassNotFoundException {
		String arquivo = "paciente.dat";
		ArquivoDeDadosLeitura leitura = new ArquivoDeDadosLeitura();
		boolean existeArquivo = false;
		existeArquivo = leitura.openFile(arquivo);
		if (existeArquivo == true) {
			Paciente a;
			boolean existente = false;
			while ((a = (Paciente) leitura.getData()) != null) {
				if (a.getCodigo() == p.getCodigo()) {
					System.out.println("Existe paciente com codigo " + p.getCodigo() + "Paciente não cadastrato.");
					existente = true;
				}
			}
			leitura.closeFile();
			if (existente == false) {
				ArquivoDeDadosEscrita escrita = new ArquivoDeDadosEscrita();
				escrita.openFile(arquivo);
				escrita.setData(p);
				escrita.closeFile();
				System.out.println("Paciente cadastrado com sucesso.");
			}
		}
	}
}