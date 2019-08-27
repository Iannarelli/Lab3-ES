package exerc2;

public class Vetor {
	public static int tamanho = 10;
	private int v[] = new int[tamanho]; // Armazena os elementos
	private int numElementos; // Informação sobre o número de elementos inseridos

	public Vetor() {
		numElementos = 0;
		for (int i = 0; i < tamanho; i++)
			v[i] = 0;
	}

	public int obtemTamanho() {
		return numElementos;
	}

	public void insereNoFinal(int novoElemento) {
		if (numElementos >= tamanho) {
			Vetor aux = new Vetor(); //cria vetor auxiliar de mesmo tamanho inicial
			for (int i = 0; i < tamanho; i++) //popula o vetor inicial com o vetor original
				aux.v[i] = v[i];
			tamanho = tamanho * 2; //dobra a variável tamanho do vetor
			v = new int[tamanho]; //atribui a v[] um novo vetor de tamanho dobrado
			for (int i = 0; i < tamanho/2; i++) //popula o vetor original com os elementos do vetor auxiliar
				v[i] = aux.v[i];
		}
		v[numElementos] = novoElemento;
		numElementos++;
	}

	public int posicaoDe(int elemento) {
		for (int i = 0; i < numElementos; i++) {
			if (v[i] == elemento)
				return i;
		}
		return -1;
	}

	public int alteraEm(int pos, int novoValor) {
		if (pos >= 0 && pos < numElementos) {
			v[pos] = novoValor;
			return 0;
		} else
			return -1;
	}

//	public int elementoDe (int pos) { return 0; }

	public int elementoEm(int pos) {
		if (pos >= 0 && pos < numElementos)
			return v[pos];
		else
			return -1;
	}

	public void reverte() {
		for (int i = 0; i < numElementos / 2; i++) {
			int aux = v[i];
			v[i] = v[(numElementos - 1) - i];
			v[(numElementos - 1) - i] = aux;
		}
	}

	public void imprime() {
		StringBuilder aux = new StringBuilder();
		for (int i = 0; i < numElementos; i++)
			aux.append(v[i] + " ");
		System.out.println("O vetor é { " + aux + "}");
	}
	
	public void retiraElemento(int elemento) {
		for(int i = 0; i<tamanho; i++) {
			if(v[i] == elemento) {
				v[i] = 0;
				numElementos--;
				for(int a = i+1; a<tamanho; a++) {
					if(v[a] != 0 && v[a] != elemento) {
						v[i] = v[a];
						v[a] = 0;
						a = tamanho;
					}
				}
			}
			if(v[i] == 0) {
				for(int a = i+1; a<tamanho; a++) {
					if(v[a] != 0 && v[a] != elemento) {
						v[i] = v[a];
						v[a] = 0;
						a = tamanho;
					}
				}
			}
		}
		int tamanhoAux = tamanho;
		while(numElementos < tamanhoAux/2)
			tamanhoAux /= 2;
		if(tamanhoAux != tamanho) {
			tamanho = tamanhoAux;
			Vetor aux = new Vetor(); //cria vetor auxiliar de mesmo tamanho inicial
			for (int i = 0; i < tamanho; i++) //popula o vetor inicial com o vetor original
				aux.v[i] = v[i];
			v = new int[tamanho]; //atribui a v[] um novo vetor de tamanho dobrado
			for (int i = 0; i < tamanho; i++) //popula o vetor original com os elementos do vetor auxiliar
				v[i] = aux.v[i];
		}
	}
}
