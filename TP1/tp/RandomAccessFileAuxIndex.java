package tp;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class RandomAccessFileAuxIndex {

	private RandomAccessFile file;
	private int numReg;
	private int tamReg;
	private int tamHead;

	public RandomAccessFileAuxIndex() {
		this.file = null;
		this.numReg = -1; // n�mero de registro (-1: n�o h� registros)
		this.tamReg = (Integer.SIZE / 8) * 11;
		this.tamHead = 4;
	}

	public void openFile(String path) {
		File file = new File(path);
		// se arquivo no existe definie numReg como 0. Se o arquivo j� existe, mantenha
		// como -1
		if (!file.exists())
			this.numReg = 0;
		try {
			this.file = new RandomAccessFile(file, "rw");
//			if(this.numReg == 0) {
//				try {
//					this.file.seek(tamHash * tamReg);
//					System.out.println("o tamanho antes " + this.file.length());
//					this.file.setLength(tamHash * tamReg);
//					System.out.println("o tamanho depois " + this.file.length());
//					for(int i = 0; i<tamHash; i++) {
//						this.file.seek(i * tamReg);
//						String a = null;
//						this.file.writeUTF(a);
//						this.file.seek(i * tamReg + STRING_MAX_TAM);
//						this.file.write(-1);
//						this.file.write(-1);
//					}
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//				
//			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			System.exit(0);
		}
		// se numReg � igual a -1, h� registros dentro do arquivo, ent�o verifica a
		// quantidade de registros
		if (this.numReg == -1)
			this.setNumReg();
	}

	private void setNumReg() {
		try {
			this.numReg = (int) (file.length()/tamReg);
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

	public void setWord(String word, int index) {
		// calcula ponteiro para a posi��o calculada do �ndice
		int pos = this.tamHead + ((index - 1) * this.tamReg);
		file.seek(pos);
		if (file.readUTF().equalsIgnoreCase(word)) // pensando em como criar o arquivo de hash
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
				/*
				 * file.seek(0); this.numReg += 1; file.writeInt(this.numReg);
				 */
			} catch (IOException e) {
				System.out.println("Error!");
				System.exit(0);
			}
	}

	public Game getDataRank(int rank) {
		if (rank >= this.numReg)
			return null;
		int pos = this.tamHead + ((rank - 1) * this.tamReg);
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

	public boolean exist(String newWord, int hash) {
		int pos = hash * this.tamReg;
//		try {
//			System.out.println("tamanho do arquivo " + file.length());
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
//		System.out.println("a posicao � " + pos);
//		Scanner teste = new Scanner(System.in);
//		teste.nextInt();
		try {
			file.seek(pos);
			String word = "";
			if (pos < file.length()) {
				word = file.readUTF();
			}
//			try {
//				word = file.readUTF();
//			} catch (IOException e) {
//				if(e.toString().equalsIgnoreCase("java.io.EOFException")) {
//					file.seek(pos);
//					file.writeUTF("Testando �a coisa");
//					System.out.println("Passou");
//				}
//			}
//			System.out.println("a palavra � " + newWord + " e a lida foi " + word + " e parou");
			if (word.equals("") || word == null) {
//				System.out.println("t� entrando");
				return false;
			} else {
				if (newWord.equalsIgnoreCase(word))
					return true;
				else {
					pos += STRING_MAX_TAM;
					file.seek(pos);
					int nextHash = file.readInt();
					if (nextHash == -1)
						return false;
					else
						return exist(newWord, nextHash);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public int getIndiceInvertido(String word, int hash) {
		int pos = hash * tamReg;
		try {
			file.seek(pos);
			String savedWord = file.readUTF();
			if (word.equalsIgnoreCase(savedWord)) {
				file.seek(pos + STRING_MAX_TAM + (Integer.SIZE / 8));
				return file.readInt();
			} else {
				file.seek(pos + STRING_MAX_TAM);
				return getIndiceInvertido(word, file.readInt());
			}
		} catch (IOException e) {
			e.printStackTrace();
			return -1;
		}
	}

	public void addOccur(int index, int reg) {
		int pos = (index-1) * tamReg;
		try {
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
					numReg++;
					file.setLength(numReg * tamReg);
					i=10;
				}
			}
			if(i == 10) {
				int registro;
				if(pos < file.length()) {
					registro = file.readInt();
					if(registro == 0) {
						file.seek(pos);
						registro = ++numReg;
						file.setLength(numReg * tamReg);
						file.writeInt(registro);
					}
				}
				else {
//					System.out.println("O numReg �: " + numReg);
					registro = ++numReg;
					file.setLength(numReg * tamReg);
//					System.out.println("O registro �: " + registro + " e o numReg �: " + numReg);
					file.writeInt(registro);
				}
				addOccur(registro, reg);
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("o erro � aqui no AuxIndex");
		}
	}

	public void newWord(String newWord, int hash, int indiceInvertido) {
		int pos = hash * tamReg;
		try {
			file.seek(pos);
//			System.out.println("T� gravando na pos " + pos);
			String word = "";
			if (pos < file.length()) {
				word = file.readUTF();
				file.seek(pos);
			}
			if (word.equals("") || word == null) {
				file.writeUTF(newWord);
				file.seek(pos + STRING_MAX_TAM);
				file.writeInt(-1);
				file.writeInt(indiceInvertido);
//				System.out.println("t� entrando");
			} else {
				file.seek(pos + STRING_MAX_TAM);
				hash = file.readInt();
				if (hash == -1) {
					hash = (int) (file.length() / tamReg);
					file.seek(pos + STRING_MAX_TAM);
					file.writeInt(hash);
				}
				newWord(newWord, hash, indiceInvertido);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void teste() {
		try {
			System.out.println(file.length());
			file.setLength(tamHash * tamReg + tamReg);
			System.out.println(file.length());
			file.seek(tamReg * tamHash);
			String teste = "abcdeabcdeabcdeabcdeabcdeabcdeabcdeabcdeabcdeabcde";
			file.writeUTF(teste);
			System.out.println("imprimiu de boas");
			System.out.println(file.length());
			System.out.println(file.getFilePointer());
			file.writeUTF("dgsdfgdsfgvdsfgvd");
			System.out.println(file.length());
			System.out.println(file.getFilePointer());
			file.seek(file.length());
			file.writeUTF(teste);
			System.out.println(file.getFilePointer());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int calculaHash(String word) {
//		word = "ABcde";
		word = word.toLowerCase();
		int primeiro = word.charAt(0);
		int segundo = word.charAt(1);
		int terceiro = word.charAt(2);
		int ultimo = word.charAt(word.length() - 1);
		int penultimo = word.charAt(word.length() - 2);
		int antepenultimo = word.charAt(word.length() - 3);
//		System.out.println("1�: " + primeiro + " 2�: " + segundo + " 3�: " + terceiro + " 4� " + antepenultimo + " 5�: " + penultimo + " 6�: " + ultimo);
		int hash = (int) (primeiro + Math.pow(segundo, 2) + Math.pow(terceiro, 3) + Math.pow(antepenultimo, 3)
				+ Math.pow(penultimo, 2) + ultimo * word.length()) % 499;
//		System.out.println("a palavra vale " + hash);
		return hash;
	}
	
	public String getOccur(int index) {
		StringBuilder string = new StringBuilder();
		int pos = (index-1)*tamReg;
		try {
			file.seek(pos);
			int i;
			long a = file.length();
			for(i=0; i<10; i++) {
				int registro = file.readInt();
				if(registro != 0)
					string.append(registro + ", ");
				else
					i=10;
			}
			if(i==10) {
				int registro = file.readInt();
				if(registro != 0)
					string.append(getOccur(registro) + ", ");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return string.substring(0, string.length()-2);
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
