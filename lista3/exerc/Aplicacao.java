package exerc;

import java.util.Scanner;

public class Aplicacao {

	public static void main(String[] args) {
		Scanner leitor = new Scanner(System.in);
		int opcao, registro;
		MeuItem item;
		System.out.print("Informe um nível para Árvore B: ");
		int nivelArv = leitor.nextInt();
		ArvoreB arvore = new ArvoreB(nivelArv);
		do {
			System.out.print("\n\tMENU\n\n" + "\t1- Inserir elemento na árvore\n" + "\t2- Remover elemento da árvore\n"
					+ "\t3- Pesquisar elemento na árove\n" + "\t4- Imprimir os elementos da árvore\n" + "\t0- Sair\n\n"
					+ "\tInforme a opção desejada: ");
			opcao = leitor.nextInt();
			switch (opcao) {
			case 1:
				System.out.println("\n\tCADASTRO DE ELEMENTOS NA ÁRVORE B");
				do {
					System.out.print("\n\tInforme um registro (para sair, digite 0): ");
					registro = leitor.nextInt();
					if (registro != 0) {
						item = new MeuItem(registro);
						arvore.insere(item);
					}
				} while (registro != 0);
				break;
			case 2:
				System.out.println("\n\tREMOÇÃO DE ELEMENTOS DA ÁRVORE B");
				do {
					System.out.print("\n\tInforme o registro a remover (para sair, digite 0): ");
					registro = leitor.nextInt();
					if (registro != 0) {
						item = new MeuItem(registro);
						arvore.retira(item);
					}
				} while(registro != 0);
				break;
			case 3:
				System.out.println("\n\tPESQUISA POR ELEMENTOS NA ÁRVORE B");
				do {
					System.out.print("\n\tInforme o registro desejado (para sair, digite 0): ");
					registro = leitor.nextInt();
					if (registro != 0) {
						item = (MeuItem) arvore.pesquisa(new MeuItem(registro));
						if (item == null)
							System.out.println("\tErro - Registro não encontrado");
						else
							System.out.println("\tO registro " + item.recuperaChave() + " está contido na árvore");
					}
				} while(registro != 0);
				break;
			case 4:
				System.out.println("\n\tIMPRESSÃO DOS ELEMENTOS NA ÁRVORE B");
				arvore.imprime();
				break;
			case 0:
				System.out.println("\nAté a próxima!");
				break;
			default:
				System.out.println("Opção inválida, tente novamente!");
			}
		} while (opcao != 0);
/*		System.out.println("Elementos na árvore: \n");
		arvore.imprime();
		arvore.retira(item);
		System.out.println("Elementos na árvore: \n");
		arvore.imprime();
		arvore.insere(new MeuItem(4));
		arvore.insere(new MeuItem(1));
		arvore.insere(new MeuItem(9));
		arvore.insere(item);
		arvore.insere(new MeuItem(3));
		item = (MeuItem) arvore.pesquisa(new MeuItem(1));
		System.out.println(item.recuperaChave());*/
		leitor.close();
	}
}
