package tp;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileInvertedIndex {

	private RandomAccessFile file;
	private RandomAccessFileAuxIndex auxIndex = new RandomAccessFileAuxIndex();
	private int numReg;
	private int tamReg;
	private int tamHead;
	private static final int STRING_MAX_TAM = 20;

	public RandomAccessFileInvertedIndex() {
		this.file = null;
		this.numReg = -1; // número de registro (-1: não há registros)
		this.tamReg = STRING_MAX_TAM + (Integer.SIZE / 8)*11;
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
			auxIndex.openFile("auxIndex.bin");
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
			auxIndex.openFileReadOnly("auxIndex.bin");
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

	public Game getDataRank(int rank) {
		if (rank >= this.numReg)
			return null;
		int pos = this.tamHead + ((rank-1) * this.tamReg);
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

	public void addOccur(String word, int indiceInvertido, int reg) {
		int pos = tamHead + (indiceInvertido-1) * tamReg;
		try {
			file.seek(pos);
			String savedWord = file.readUTF();
			if(word.equalsIgnoreCase(savedWord)) {
				pos += STRING_MAX_TAM;
				file.seek(pos);
				int i;
				for(i=0; i<10; i++) {
					if(pos < file.length()) {
						int registro = file.readInt();
						if(registro == 0) {
							file.seek(pos);
							file.writeInt(reg);
							i=10;
						}
						else if(registro == reg)
							i=10;
						pos += Integer.SIZE/8;
					}
					else {
						file.writeInt(reg);
						i=10;
					}
				}
				if(i == 10) {
					int registro;
					if(pos < file.length()) {
						registro = file.readInt();
						if(registro == 0) {
							file.seek(pos);
							file.writeInt(registro = (auxIndex.getNumReg()+1));
						}
						pos += Integer.SIZE/8;
					}
					else {
						file.writeInt(registro = (auxIndex.getNumReg()+1));
					}
					auxIndex.addOccur(registro, reg);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("o erro é aqui no InvertedIndex");
		}
	}

	public void newWord(String word, int registro) {
		int pos = tamHead + (numReg * tamReg);
		try {
//			System.out.println("Gravando a palavra " + word);
			file.seek(pos);
			file.writeUTF(word);
			file.seek(pos + STRING_MAX_TAM);
			file.writeInt(registro);
//			System.out.println("Gravou a palavra " + word);
			file.seek(0);
			this.numReg += 1;
			file.writeInt(this.numReg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getRegistros(int index, String word) {
		int pos = tamHead + (index-1) * tamReg;
		StringBuilder aux = new StringBuilder();
		try {
			file.seek(pos);
			String savedWord = file.readUTF();
			file.seek(pos + STRING_MAX_TAM);
			if(word.equalsIgnoreCase(savedWord)) {
				int i;
				for(i=0; i<10; i++) {
					int registro = file.readInt();
					if(registro != 0)
						aux.append(registro + ", ");
					else
						i=10;
				}
				if(i==10) {
					int registro = file.readInt();
					if(registro != 0)
						aux.append(auxIndex.getOccur(registro) + ", ");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return aux.substring(0, aux.length()-2);
	}

	public String printFile() {
		StringBuilder string = new StringBuilder();
		string.append("ÍNDICE INVERTIDO:\n\n"
				+ "Palavra\t\t\tRegistros\n");
		int pos = tamHead + 0;
		try {
			for(int i=1; i<numReg; i++) {
				file.seek(pos);
//				String word = file.readUTF();
//				if(!word.equals("") && word != null) {
				String palavra = file.readUTF();
				if (palavra.length() < 8)
					string.append(palavra + "\t\t\t");					
				else if (palavra.length() < 16)
					string.append(palavra + "\t\t");
				else
					string.append(palavra + "\t");
				file.seek(pos + STRING_MAX_TAM);
				StringBuilder aux = new StringBuilder();
				int j;
				for(j=0; j<10; j++) {
					int registro = file.readInt();
					if(registro != 0)
						aux.append(registro + ", ");
					else
						j=10;
				}
				if(j==10) {
					int registro = file.readInt();
					if(registro != 0)
						aux.append(auxIndex.getOccur(registro) + ", ");
				}
				System.out.println("A substring é: " + aux.toString());
				string.append(aux.substring(0, aux.length()-2) + "\n");
				pos += tamReg;
				//System.out.println("A string é: " + string.toString());
			}
//		} catch (EOFException e) {
//			System.out.println("Acabou o arquivo");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return string.substring(0, string.length()-1);
	}

	public void closeFile(String path) {
		try {
			file.close();
			auxIndex.closeFile("auxIndex.bin");
			System.out.println("Arquivo fechado com sucesso");
		} catch (IOException e) {
			System.out.println("Error");
			System.exit(0);
		}
	}
	
	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		int pos = tamHead;
		try {
			file.seek(pos);
			for(int i=0; i<numReg; i++) {
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return string.toString();
	}
}
