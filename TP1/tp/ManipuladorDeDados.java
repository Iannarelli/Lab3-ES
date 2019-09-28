package tp;

public class ManipuladorDeDados {

	private static final String[] STOP_WORDS = { "about", "are", "com", "for", "from", "how", "new", "that", "the",
			"this", "was", "what", "when", "where", "who", "will", "with", "the", "www", "III", "VII", "VIII" };

	public Game geraGame(String line) {
		String[] informacoes = new String[11];
		String[] aux = new String[3];
		int i;
		for (i = 1; i < 6; i++)
			if (line.charAt(i) == ',') {
				if (line.charAt(i + 1) == '"')
					i = 10;
			}
		if (i > 10) {
			aux = line.split("\"");
			line = aux[0] + "" + aux[2];
		}
		informacoes = line.split(",");
		if (i > 10)
			informacoes[1] = aux[1];
		Game game = new Game(Integer.parseInt(informacoes[0]), informacoes[1], informacoes[2],
				Integer.parseInt(informacoes[3]), informacoes[4], informacoes[5], Double.parseDouble(informacoes[6]),
				Double.parseDouble(informacoes[7]), Double.parseDouble(informacoes[8]),
				Double.parseDouble(informacoes[9]), Double.parseDouble(informacoes[10]));
		return game;
	}

	public String splitTitle(String title) {
		StringBuilder words = new StringBuilder();
		for (String word : title.split(" |\\/|:|,|\\*|\\.|!|-")) {
//			System.out.println("Split = " + word);
			if (word.length() > 2 && !isNumeric(word) && !isStopWord(word))
				words.append(word + ",");
		}
		return words.toString();
	}

	public static boolean isNumeric(String word) {
		try {
			Integer.parseInt(word);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public boolean isStopWord(String word) {
		for (String stopWord : STOP_WORDS) {
			if (word.equalsIgnoreCase(stopWord))
				return true;
		}
		return false;
	}

	public String verificaRegistrosE(String[] registros) {
		if(registros.length == 1)
			return registros[0];
		else {
//		String[] reg = registros[0].split(", ");
			String[] reg = new String[(int) Math.ceil(registros.length/2.0)];
			for(int i=0, j=0; i<registros.length; i=i+2, j++) {
				if(i+1 >= registros.length)
					reg[j] = registros[i];
				else {
					String[] registros1 = registros[i].split(", ");
					String[] registros2 = registros[i+1].split(", ");
					StringBuilder aux = new StringBuilder();
					int x=0;
					for(int y=0; y<registros1.length; y++) {
						for(int z=x; z<registros2.length; z++) {
							if(Integer.parseInt(registros1[y]) == Integer.parseInt(registros2[z])){
								aux.append(registros1[y] + ", ");
								x++;
								z=registros2.length;
							}
						}
					}
//					if(x<registros2.length) {
//						for(int y=x; y<registros2.length; y++) {
//							int cont = 0;
//							for(String regAux : aux.toString().split(", ")) {
//								if(Integer.parseInt(registros2[y]) == Integer.parseInt(regAux))
//									cont++;
//							}
//							if(cont == 0)
//								aux.append(registros2[y] + ", ");
//						}
//					}
					reg[j] = aux.substring(0, aux.length()-2);
				}
			}
			return verificaRegistrosE(reg);
		}
	}
}
