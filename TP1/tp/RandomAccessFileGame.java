package tp;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.DecimalFormat;

public class RandomAccessFileGame {

	private RandomAccessFile file;
	private int numReg;
	private int tamReg;
	private int tamHead;
	private static final int STRING_MAX_NAME = 140;
	private static final int STRING_MAX_PLATFORM = 5;
	private static final int STRING_MAX_GENRE = 15;
	private static final int STRING_MAX_PUBLISHER = 40;

	public RandomAccessFileGame() {
		this.file = null;
		this.numReg = -1; // número de registro (-1: não há registros)
		this.tamReg = (Integer.SIZE / 8) + STRING_MAX_NAME + STRING_MAX_PLATFORM + (Integer.SIZE / 8) + STRING_MAX_GENRE
				+ STRING_MAX_PUBLISHER + (Double.SIZE / 8) * 5;
		this.tamHead = 4;
	}

	public void openFile(String path) {
		File file = new File(path);
		// se arquivo no existe definie numReg como 0. Se o arquivo já existe, mantenha
		// como -1
		if (!file.exists())
			this.numReg = 0;
		try {
			this.file = new RandomAccessFile(file, "rw");
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			System.exit(0);
		}
		// se numReg é igual a -1, há registros dentro do arquivo, então verifica a
		// quantidade de registros
		if (this.numReg == -1)
			this.setNumReg();
	}

	private void setNumReg() {
		try {
			this.numReg = file.readInt();
		} catch (EOFException e) {
			this.numReg = 0;
		} catch (IOException e) {
			System.out.println("Error!");
			System.exit(0);
		}
	}

	public void openFileReadOnly(String path) {
		try {
			file = new RandomAccessFile(new File(path), "r");
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			System.exit(0);
		}
		this.setNumReg();
	}

	public int getNumReg() {
		return numReg;
	}

	public void setData(Game game) {
		// calcula ponteiro para a primeira posição vazia do arquivo
		int pos = this.tamHead + (this.numReg * this.tamReg);
		try {
			file.seek(pos);
			file.writeInt(game.getRank());
			file.writeUTF(game.getName());
			file.seek(pos + (Integer.SIZE / 8) + STRING_MAX_NAME);
			file.writeUTF(game.getPlatform());
			file.seek(pos + (Integer.SIZE / 8) + STRING_MAX_NAME + STRING_MAX_PLATFORM);
			file.writeInt(game.getYear());
			file.writeUTF(game.getGenre());
			file.seek(pos + (Integer.SIZE / 8) + STRING_MAX_NAME + STRING_MAX_PLATFORM + (Integer.SIZE / 8)
					+ STRING_MAX_GENRE);
			file.writeUTF(game.getPublisher());
			file.seek(pos + (Integer.SIZE / 8) + STRING_MAX_NAME + STRING_MAX_PLATFORM + (Integer.SIZE / 8)
					+ STRING_MAX_GENRE + STRING_MAX_PUBLISHER);
			file.writeDouble(game.getNASales());
			file.writeDouble(game.getEUSales());
			file.writeDouble(game.getJPSales());
			file.writeDouble(game.getOtherSales());
			file.writeDouble(game.getGlobalSales());
			file.seek(0);
			this.numReg += 1;
			file.writeInt(this.numReg);
		} catch (IOException e) {
			System.out.println("Error!");
			System.exit(0);
		}
	}

	public Game getDataIndice(int indice) {
		if (indice > this.numReg)
			return null;
		int pos = this.tamHead + ((indice-1) * this.tamReg);
		Game game = new Game();
		try {
			file.seek(pos);
			game.setRank(file.readInt());
			game.setName(file.readUTF());
			file.seek(pos + (Integer.SIZE / 8) + STRING_MAX_NAME);
			game.setPlatform(file.readUTF());
			file.seek(pos + (Integer.SIZE / 8) + STRING_MAX_NAME + STRING_MAX_PLATFORM);
			game.setYear(file.readInt());
			game.setGenre(file.readUTF());
			file.seek(pos + (Integer.SIZE / 8) + STRING_MAX_NAME + STRING_MAX_PLATFORM + (Integer.SIZE / 8)
					+ STRING_MAX_GENRE);
			game.setPublisher(file.readUTF());
			file.seek(pos + (Integer.SIZE / 8) + STRING_MAX_NAME + STRING_MAX_PLATFORM + (Integer.SIZE / 8)
					+ STRING_MAX_GENRE + STRING_MAX_PUBLISHER);
			game.setNASales(file.readDouble());
			game.setEUSales(file.readDouble());
			game.setJPSales(file.readDouble());
			game.setOtherSales(file.readDouble());
			game.setGlobalSales(file.readDouble());
		} catch (IOException e) {
			System.out.println("Error");
			System.exit(0);
		}
		return game;
	}

	public String listaOcorrencias(String word, String registros) {
		StringBuilder ocorrencias = new StringBuilder();
		for (String registro : registros.split(" |,")) {
			if (ManipuladorDeDados.isNumeric(registro))
				ocorrencias.append(getDataIndice(Integer.parseInt(registro)).toString() + "\n");
		}
		return ocorrencias.substring(0, ocorrencias.length()-1);
	}

	public String printFile() {
		DecimalFormat df = new DecimalFormat();
		df.setMinimumFractionDigits(2);
		StringBuilder string = new StringBuilder();
		string.append("RELAÇÃO COMPLETA DE JOGOS:\n\n"
				+ "Título\nRank\tPlataforma\tAno\tGênero\t\tFabricante\t\t\t\tNA Sales    EU Sales\tJP Sales    Other\tGlobal\n");
		int pos = tamHead;
		try {
			for(int i=0; i<numReg; i++) {
				file.seek(pos);
				int rank = file.readInt();
				string.append(file.readUTF() + "\n" + rank + "\t");
				file.seek(pos + Integer.SIZE/8 + STRING_MAX_NAME);
				string.append(file.readUTF() + "\t\t");
				file.seek(pos + Integer.SIZE/8 + STRING_MAX_NAME + STRING_MAX_PLATFORM);
				string.append(file.readInt() + "\t");
				String aux = file.readUTF(); //gereno
				if (aux.length() < 8)
					string.append(aux + "\t\t");
				else
					string.append(aux + "\t");
				file.seek(pos + Integer.SIZE/8 + STRING_MAX_NAME + STRING_MAX_PLATFORM + Integer.SIZE/8
						+ STRING_MAX_GENRE);
				aux = file.readUTF(); //fabricante
				if (aux.length() < 8)
					string.append(aux + "\t\t\t\t\t");
				else if (aux.length() < 16)
					string.append(aux + "\t\t\t\t");
				else if (aux.length() < 24)
					string.append(aux + "\t\t\t");
				else if (aux.length() < 32)
					string.append(aux + "\t\t");
				else
					string.append(aux + "\t");
				file.seek(pos + Integer.SIZE/8 + STRING_MAX_NAME + STRING_MAX_PLATFORM + Integer.SIZE/8
						+ STRING_MAX_GENRE + STRING_MAX_PUBLISHER);
				string.append(df.format(file.readDouble()) + "\t    ");
				string.append(df.format(file.readDouble()) + "\t");
				string.append(df.format(file.readDouble()) + "\t    ");
				string.append(df.format(file.readDouble()) + "\t");
				string.append(df.format(file.readDouble()));
				if(i < numReg-1)
					string.append("\n\n");
				pos += tamReg;
				//System.out.println("A string é: " + string.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return string.toString();
	}

	public void closeFile(String path) {
		try {
			file.close();
			System.out.println("Arquivo fechado com sucesso");
		} catch (IOException e) {
			System.out.println("Error");
			System.exit(0);
		}
	}
}