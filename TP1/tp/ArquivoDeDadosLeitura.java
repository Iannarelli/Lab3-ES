package tp;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ArquivoDeDadosLeitura {
	private ObjectInputStream input;

	public boolean openFile(String file) {
		try {
			input = new ObjectInputStream(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			System.out.println("pegou o erro...");
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public Object getData() throws IOException, ClassNotFoundException {
		Object data;
		try {
			data = input.readObject();
		} catch (EOFException endOfFileException) {
			return null;
		}
		return data;
	}

	public void closeFile() throws IOException {
		if (input != null)
			input.close();
	}
}