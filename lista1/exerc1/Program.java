package exerc1;

public class Program {
	Vetor v = new Vetor();
	public static void main(String[] args) {
		Vetor V = new Vetor();
		V.insereNoFinal(10);
		V.insereNoFinal(8);
		V.insereNoFinal(16);
		V.insereNoFinal(7);
		V.insereNoFinal(5);
		V.insereNoFinal(13);

		V.imprime();
//		System.out.println("O vetor possui " + V.obtemTamanho() + " elemento(s).");
		V.alteraEm(3, 19);
		V.alteraEm(5, 9);
//		System.out.println("O elemento \"10\" ocupa o índice " + V.posicaoDe(10) + " no vetor.");
//		System.out.println("No índice 4 do vetor está o elemento \"" + V.elementoEm(4) + "\".\n");
		for (int i = 0; i < V.obtemTamanho(); i++)
			System.out.println("Elemento na posicao " + i + ": " + V.elementoEm(i));
		V.reverte();
		V.imprime();
//		System.out.println("O vetor possui " + V.obtemTamanho() + " elemento(s).");
//		System.out.println("O elemento \"10\" ocupa o índice " + V.posicaoDe(10) + " no vetor.");
//		System.out.println("No índice 4 do vetor está o elemento \"" + V.elementoEm(4) + "\".\n");
//		Vetor v = new Vetor();
	}
}
