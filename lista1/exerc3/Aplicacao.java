package exerc3;

import java.text.DecimalFormat;

public class Aplicacao {

	public static void main(String[] args) {
		Cliente cliente1 = new Cliente("Filipe Iannarelli", 1);
		Cliente cliente2 = new Cliente("Natália Iannarelli", 2);
		
		Conta conta1c1 = new Conta(cliente1, 1);
		Conta conta2c1 = new Conta(cliente1, 2);
		Conta conta1c2 = new Conta(cliente2, 3);
		Conta conta2c2 = new Conta(cliente2, 4);

		conta1c1.deposita(250);
		System.out.println(cliente1.getNome() + " depositou R$ 250,00");
		conta2c1.deposita(1250);
		System.out.println(cliente1.getNome() + " depositou R$ 1250,00");
		conta1c2.deposita(2050);
		System.out.println(cliente2.getNome() + " depositou R$ 2050,00");
		conta2c2.deposita(25);
		System.out.println(cliente2.getNome() + " depositou R$ 25,00");

		if(conta1c1.saque(5000) < 0)
			System.out.println("Saldo insuficiente para saque na conta de " + cliente1.getNome());
		else
			System.out.println(cliente1.getNome() + " sacou R$ 5000,00");
		if(conta2c1.saque(50) < 0)
			System.out.println("Saldo insuficiente para saque na conta de " + cliente2.getNome());
		else
			System.out.println(cliente1.getNome() + " sacou R$ 50,00");
		if(conta1c2.saque(500) < 0)
			System.out.println("Saldo insuficiente para saque na conta de " + cliente1.getNome());
		else
			System.out.println(cliente2.getNome() + " sacou R$ 500,00");
		if(conta2c2.saque(10350) < 0)
			System.out.println("Saldo insuficiente para saque na conta de " + cliente2.getNome());
		else
			System.out.println(cliente2.getNome() + " sacou R$ 10350,00");

		if(conta1c1.transfere(50, conta1c2) < 0)
			System.out.println("Saldo insuficiente para transferência na conta de " + cliente1.getNome());
		else
			System.out.println(cliente1.getNome() + " transferiu R$ 50,00 para " + conta1c2.getTitular().getNome());
		if(conta2c1.transfere(10000, conta2c2) < 0)
			System.out.println("Saldo insuficiente para transferência na conta de " + cliente1.getNome());
		else
			System.out.println(cliente1.getNome() + " transferiu R$ 10000,00 para " + conta2c2.getTitular().getNome());
		if(conta1c2.transfere(650, conta1c1) < 0)
			System.out.println("Saldo insuficiente para transferência na conta de " + cliente2.getNome());
		else
			System.out.println(cliente2.getNome() + " transferiu R$ 500,00 para " + conta1c1.getTitular().getNome());
		if(conta2c2.transfere(13500, conta2c1) < 0)
			System.out.println("Saldo insuficiente para transferência na conta de " + cliente2.getNome());
		else
			System.out.println(cliente2.getNome() + " transferiu R$ 10350,00 para " + conta2c1.getTitular().getNome());

		DecimalFormat df = new DecimalFormat("#.00");

		System.out.println("Contas de " + cliente1.getNome());
		System.out.println(conta1c1.getNumero() + " - R$ " + df.format(conta1c1.getSaldo()));
		System.out.println(conta2c1.getNumero() + " - R$ " + df.format(conta2c1.getSaldo()));
		System.out.println("Contas de " + cliente2.getNome());
		System.out.println(conta1c2.getNumero() + " - R$ " + df.format(conta1c2.getSaldo()));
		System.out.println(conta2c2.getNumero() + " - R$ " + df.format(conta2c2.getSaldo()));
	}

}
