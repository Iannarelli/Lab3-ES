package tp;

public class Aplicacao {

	public static void main(String[] args) {

		String file = "vgsales-50registros.";
		
		//instancia manipuladores de dados e de arquivos
		ManipuladorDeDados manipulador = new ManipuladorDeDados();
		ArquivoTextoLeitura leitorTexto = new ArquivoTextoLeitura();
		RandomAccessFileGame leitorBancoDados = new RandomAccessFileGame();
		RandomAccessFileHash leitorHash = new RandomAccessFileHash();
		RandomAccessFileInvertedIndex leitorIndiceInvertido = new RandomAccessFileInvertedIndex();

		//abre arquivos
		leitorTexto.openFile(file + "csv");
		leitorBancoDados.openFile(file + "bin");
		leitorHash.openFile("hashTable.bin");
		leitorIndiceInvertido.openFile("indiceInvertido.bin");

		//l� arquivo texto e cria arquivo bin�rios de dados
		String line = leitorTexto.read();
		while((line = leitorTexto.read()) != null) {
			leitorBancoDados.setData(manipulador.geraGame(line));
		}
		
		//fecha arquivo texto, e fecha arquivo bin�rio de dados para abrir como readOnly
		leitorTexto.closeFile();
		leitorBancoDados.closeFile(file + "bin");
		leitorBancoDados.openFileReadOnly(file + "bin");

		//cria arquivos de hash e �ndice invertido
		int numReg = leitorBancoDados.getNumReg();
		do {
			StringBuilder allWords = new StringBuilder();
//			System.out.println("antes do teste");
//			leitorHash.teste();
//			System.out.println("depois do teste");
			
			//gera uma string com todas as pal�vras eleg�veis ao �ndice, com o n�mero do respectivo registro
			for(int i=1; i<=numReg; i++) {
//				System.out.println("o i vale (aplicacao) " + i);
				Game game = leitorBancoDados.getDataIndice(i);
				String title = game.getName();
				allWords.append(i + "," + manipulador.splitTitle(title));
//				System.out.println("o retorno da string � \n" + manipulador.splitTitle(title));
//				System.out.println(title);
			}
			
			int registro = -1;
//			System.out.println("A stringbuilder ficou " + allWords.toString());
			for(String word : allWords.toString().split(",")) {
				//se for n�mero, armazena o n�mero do registro do game. Se for palavra, trabalha os arquivos bin�rios
				if(manipulador.isNumeric(word))
					registro = Integer.parseInt(word);
				else {
					int hash = leitorHash.calculaHash(word);
					System.out.println("a palavra " + word + " existe? " + leitorHash.exist(word, hash));
					if(leitorHash.exist(word, hash)) {
						int indiceInvertido = leitorHash.getIndice(word);
						leitorIndiceInvertido.addOccur(word, indiceInvertido, registro);
					}
					else {
//						System.out.println("o n�mero de registros � (aplica��o) " + leitorIndiceInvertido.getNumReg());
						leitorHash.newWord(word, hash, leitorIndiceInvertido.getNumReg()+1);
						leitorIndiceInvertido.newWord(word, registro);
					}
				}
			}
		} while (false);
		
		//fecha arquivos para escrita e abre para somente leitura
		leitorHash.closeFile("hashTable.bin");
		leitorIndiceInvertido.closeFile("indiceInvertido.bin");
		leitorHash.openFileReadOnly("hashTable.bin");
		leitorIndiceInvertido.openFileReadOnly("indiceInvertido.bin");
		
		System.out.println("Teste de impress�o: \n" + leitorBancoDados.printFile());
		System.out.println("Teste de impress�o: \n" + leitorHash.printFile());
		System.out.println("Teste de impress�o: \n" + leitorIndiceInvertido.printFile());
		System.out.println("O leitor �: " + leitorBancoDados.getNumReg());
		System.out.println(leitorBancoDados.getDataIndice(6).toString());

		//testar buscas e impress�es
		String palavraTeste = "Super Mario Land";
		String[] registros = new String[palavraTeste.split(" ").length];
		int i=0;
		for(String word : palavraTeste.split(" ")) {
			int indiceInvertido = leitorHash.getIndice(word);
			registros[i] = leitorIndiceInvertido.getRegistros(indiceInvertido, word);
			System.out.println("O registro de " + word + " �: " + registros[i]);
			i++;
		}
		String registrosE = manipulador.verificaRegistrosE(registros);
		System.out.println("O registroE �: " + registrosE);
		String ocorrencias = leitorBancoDados.listaOcorrencias(palavraTeste, registrosE);
		System.out.println("Testando...\n" + ocorrencias + "\n e finalizando");
/*		for (String registro : registros.split(" |,")) {
			if (manipulador.isNumeric(registro))
				System.out.println(leitorBancoDados.getDataIndice(Integer.parseInt(registro)));
		}*/
//		System.out.println(registros);
		leitorBancoDados.closeFile(file + "bin");
	}
}