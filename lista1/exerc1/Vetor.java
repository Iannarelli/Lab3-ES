package exerc1;

public class Vetor {
	public static final int TAMANHO = 10;
	private int v[] = new int[TAMANHO]; // Armazena os elementos
	private int numElementos; // Informação sobre o número de elementos inseridos

	public Vetor() {
		numElementos = 0;
		for (int i = 0; i < TAMANHO; i++)
			v[i] = 0;
	}

	public int obtemTamanho() {
		return numElementos;
	}

	public void insereNoFinal(int novoElemento) {
		if (numElementos < TAMANHO) {
			v[numElementos] = novoElemento;
			numElementos++;
		}
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
}
