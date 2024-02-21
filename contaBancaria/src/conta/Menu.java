package conta;

import java.util.Scanner;

import conta.model.Conta;
import conta.model.ContaCorrente;
import conta.model.ContaPoupanca;

public class Menu {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Importação da classe "Conta" para fazer uso de seus atributos
		Conta novaConta = new Conta(null, 0, 0, 0, 0); //Aqui, declaramos os atributos com valores vazios para que o usuário os registre
		//É imaginado que o usuário seja algum REPRESENTANTE do banco
		//Import de outras classes
		ContaCorrente contaCorrente = new ContaCorrente(null, 0, 0, 0, 0, 1000.00f);

		//Variáveis
		String nomeTitular = null;
		int tipoConta = 0;
		int numeroConta = 0;
		float saldo = 0f;
		float valorSaque = 0f;
		float valorDeposito = 0f;

		//Scanner
		Scanner leia = new Scanner(System.in);

		//Declarando os valores das variáveis
		System.out.println("\n*************************************************");
		System.out.println("** Informações Cliente **");
		System.out.print("Digite o nome do titular: ");
		nomeTitular = leia.next();
		novaConta.setNomeTitular(nomeTitular); //Aqui, declaramos que o nomeTitular de Conta, é igual ao valor lido pelo scanner

		//Agência será uma váriavel já declarada pelo sistema!
		novaConta.setAgenciaConta(2527);

		System.out.println("\n** 1 para corrente, 2 para poupança **");
		System.out.print("Informe o tipo da conta: ");
		tipoConta = leia.nextInt();
		novaConta.setTipoConta(tipoConta);

		System.out.print("\nDigite o número da conta: ");
		numeroConta = leia.nextInt();
		novaConta.setNumeroConta(numeroConta);

		System.out.print("\nDigite o saldo atual da conta: ");
		saldo = leia.nextFloat();
		novaConta.setSaldo(saldo);
		contaCorrente.setSaldo(saldo);

		//Imprimindo os resultados de acordo com a classe do model
		novaConta.visualizar();

		//Método de saque de dinheiro
		System.out.println("\n*************************************************");
		System.out.println("\n** Saque de Dinheiro **");
		System.out.print("Informe a quantidade que deseja sacar: ");
		valorSaque = leia.nextFloat(); //Declarando o valor de saque

		//Efetuando o método de saque
		System.out.println("\nO saldo inicial de " + nomeTitular + " é igual a " + contaCorrente.getSaldo());
		contaCorrente.sacar(valorSaque);
		System.out.println("\nO saldo pós saque de " + nomeTitular + " é igual a " + contaCorrente.getSaldo());

		//Método de depósito de dinheiro
		System.out.println("\n*************************************************");
		System.out.println("\n** Depósito de Dinheiro **");
		System.out.print("Informe a quantidade que deseja depositar: ");
		valorDeposito = leia.nextFloat(); //Declarando o valor de depósito

		novaConta.depositar(valorDeposito);
		System.out.println("\nO novo saldo é de: " + novaConta.getSaldo());
		System.out.println("\n*************************************************");

	}

}
