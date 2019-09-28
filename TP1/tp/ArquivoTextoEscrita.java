package tp;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class ArquivoTextoEscrita {

	BufferedWriter output;
		
	public void openFile(String file){	
		
		try {
			output = new BufferedWriter(new FileWriter(file));
		}
		catch (FileNotFoundException e) {
			System.out.println("Arquivo não encontrado");
		}
		catch (IOException e) {
			System.out.println("Erro na abertura do arquivo de escrita: " + e);
		}
	}
	
	public void closeFile() {
		
		try {
			output.close();
		}
		catch (IOException e) {
			System.out.println("Erro no fechamento do arquivo de escrita: " + e);	
		}
	}
	
	public void write(String text) {
	
		try {
			output.write(text);
		}
		catch (IOException e){
			System.out.println("Erro de entrada/saída " + e);
		}
	}
}
