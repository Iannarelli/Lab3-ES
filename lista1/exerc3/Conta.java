package exerc3;

public class Conta {

	private int Numero;
	private double Saldo;
	public Cliente Titular;

	public Conta(Cliente cliente, int numero) {
		this.Titular = cliente;
		this.Numero = numero;
		this.Saldo = 0;
	}

	public void deposita(double valor) {
		Saldo += valor;
	}

	public double saque(double valor) {
		if (valor*1.1 <= Saldo) {
			Saldo -= valor*1.1;
			return Saldo;
		}
		else
			return Saldo - valor*1.1;
	}

	public double transfere(double valor, Conta destino) {
		if (valor*1.1 <= Saldo) {
			Saldo -= valor*1.1;
			destino.deposita(valor);
			return Saldo;
		}
		else
			return Saldo - valor*1.1;
	}
	
	public double getSaldo() {
		return Saldo;
	}

	public Cliente getTitular() {
		return Titular;
	}
	
	public int getNumero() {
		return Numero;
	}
}
