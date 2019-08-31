package parteI;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ArquivoDeDadosEscrita {
	private ObjectOutputStream output;

	public void openFile(String file) throws IOException {
		output = new ObjectOutputStream(new FileOutputStream(file));
	}

	public void setData(Object data) throws IOException {
		output.writeObject(data);
	}

	public void closeFile() throws IOException {
		if (output != null)
			output.close();
	}
}