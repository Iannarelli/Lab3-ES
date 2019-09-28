package tp;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ArquivoTextoLeitura {

	BufferedReader input;
	
	public boolean openFile(String file){	
		try {
			input = new BufferedReader(new FileReader(file));
		}
		catch (FileNotFoundException e) {
//			System.out.println("Arquivo nÃ£o encontrado");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean closeFile() {
		try {
			input.close();
		}
		catch (IOException e) {
//			System.out.println("Erro no fechamento do arquivo de leitura: " + e);
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public String read() {
		String inputText;
		try {
			inputText = input.readLine();
		}
		catch (EOFException e) { //Exceção de final de arquivo.
			return null;
		}
		catch (IOException e) {
//			System.out.println("Erro de leitura: " + e);
			e.printStackTrace();
			return null;
		}
		return inputText;
	}
}
