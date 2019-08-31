package exerc;

import java.util.Scanner;

public class Aplicacao {

	public static void main(String[] args) {
		Scanner leitor = new Scanner(System.in);
		int opcao, registro;
		MeuItem item;
		System.out.print("Informe um n�vel para �rvore B: ");
		int nivelArv = leitor.nextInt();
		ArvoreB arvore = new ArvoreB(nivelArv);
		do {
			System.out.print("\n\tMENU\n\n" + "\t1- Inserir elemento na �rvore\n" + "\t2- Remover elemento da �rvore\n"
					+ "\t3- Pesquisar elemento na �rove\n" + "\t4- Imprimir os elementos da �rvore\n" + "\t0- Sair\n\n"
					+ "\tInforme a op��o desejada: ");
			opcao = leitor.nextInt();
			switch (opcao) {
			case 1:
				System.out.println("\n\tCADASTRO DE ELEMENTOS NA �RVORE B");
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
				System.out.println("\n\tREMO��O DE ELEMENTOS DA �RVORE B");
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
				System.out.println("\n\tPESQUISA POR ELEMENTOS NA �RVORE B");
				do {
					System.out.print("\n\tInforme o registro desejado (para sair, digite 0): ");
					registro = leitor.nextInt();
					if (registro != 0) {
						item = (MeuItem) arvore.pesquisa(new MeuItem(registro));
						if (item == null)
							System.out.println("\tErro - Registro n�o encontrado");
						else
							System.out.println("\tO registro " + item.recuperaChave() + " est� contido na �rvore");
					}
				} while(registro != 0);
				break;
			case 4:
				System.out.println("\n\tIMPRESS�O DOS ELEMENTOS NA �RVORE B");
				arvore.imprime();
				break;
			case 0:
				System.out.println("\nAt� a pr�xima!");
				break;
			default:
				System.out.println("Op��o inv�lida, tente novamente!");
			}
		} while (opcao != 0);
/*		System.out.println("Elementos na �rvore: \n");
		arvore.imprime();
		arvore.retira(item);
		System.out.println("Elementos na �rvore: \n");
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
