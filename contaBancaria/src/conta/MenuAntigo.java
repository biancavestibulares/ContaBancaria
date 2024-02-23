package conta;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

import conta.model.Conta;
import conta.model.ContaCorrente;
import conta.model.ContaPoupanca;
import conta.util.Cores;

public class MenuAntigo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

			//Importação da classe "Conta" para fazer uso de seus atributos
			Conta novaConta = new Conta(null, 0, 0, 0, 0) { //Aqui, declaramos os atributos com valores vazios para que o usuário os registre
				//Temos que importar a Conta desta forma por ela ser uma classe abstract
			};
			//É imaginado que o usuário seja algum REPRESENTANTE do banco
			//Import de outras classes
			ContaCorrente contaCorrente = new ContaCorrente(null, 0, 0, 0, 0, 0);
			ContaPoupanca contaPoupanca = new ContaPoupanca(null, 0, 0, 0, 0, null);
			Cores cores = new Cores();

			//Variáveis
			String nomeTitular = null;
			int tipoConta = 0;
			int numeroConta = 0;
			float saldo = 0f;
			float valorSaque = 0f;
			float valorDeposito = 0f;
			boolean loop = true;
			boolean loop2 = true;

			//Scanner
			Scanner leia = new Scanner(System.in);

			//Declarando os valores das variáveis
			System.out.println("*************************************************");
			System.out.println("** Informações Cliente **");
			System.out.print("Digite o nome do titular: ");
			nomeTitular = leia.next();
			novaConta.setNomeTitular(nomeTitular); //Aqui, declaramos que o nomeTitular de Conta, é igual ao valor lido pelo scanner

			//Agência será uma váriavel já declarada pelo sistema!
			novaConta.setAgenciaConta(12345);

			System.out.println("\n** 1 para corrente, 2 para poupança **");
			System.out.print("Informe o tipo da conta: ");
			tipoConta = leia.nextInt();
			novaConta.setTipoConta(tipoConta);

			//Criando um loop para executar a pergunta até que o usuário digite um valor correto
			do {
				try {
					System.out.print("\nDigite o número da conta: ");
					numeroConta = leia.nextInt();
					novaConta.setNumeroConta(numeroConta);
					
					loop = false;
					
				} catch (InputMismatchException e) { //InputMismatchException é um erro captado quando uma String é digitada ao invés de um int
					System.err.println("\nExceção: " + e); //System.err.println imprime a exceção/erro - informa o usuário do problema encontrado
					leia.nextLine();
					System.out.println("Por favor, digite 5 números inteiros"); //Instrução
				}
			} while(loop);

			do {
				try {
					System.out.print("\nDigite o saldo atual da conta: ");
					saldo = leia.nextFloat();
					novaConta.setSaldo(saldo);
					contaCorrente.setSaldo(saldo);
					
					loop2 = false;
					
				} catch (InputMismatchException e) { 
					System.err.println("\nExceção: " + e);
					leia.nextLine();
					System.out.println("Por favor, digite um valor inteiro");
				}
			} while(loop2);

			float limiteSaque = saldo * 0.90f; //Aqui, estamos declarando que o limite será igual a 90% do saldo, 
			//assim o usuário não poderá sacar mais do que tem na conta e também não poderá deixar sua conta vazia
			contaCorrente.setLimite(limiteSaque);

			//Imprimindo os resultados de acordo com a classe do model
			novaConta.visualizar();

			
			//Efetuando os métodos de acordo com o tipo de conta selecionado
			//No caso da conta for corrente, efetua o método dentro da classe contaCorrente
			if(tipoConta == 1) { 
				//Método de saque de dinheiro
				System.out.println("\n*************************************************");
				System.out.println("** Saque de Dinheiro **");
				System.out.println("\nEsteja ciente que seu limite é de: " + contaCorrente.getLimite());
				System.out.print("Informe a quantidade que deseja sacar: ");
				valorSaque = leia.nextFloat(); //Declarando o valor de saque

				//Efetuando o método de saque
				if(valorSaque < limiteSaque) {
					System.out.println("\nO saldo inicial de " + nomeTitular + " é igual a " + contaCorrente.getSaldo());
					contaCorrente.sacar(valorSaque);
					System.out.println("\nO saldo pós saque de " + nomeTitular + " é igual a " + contaCorrente.getSaldo());
					System.out.println("\n*************************************************");

					System.exit(0); //Finaliza o sistema após realizar o método da conta corrente
				} else {
					System.out.println("\nLimite ultrapassado! O saldo não pode ser efetuado!");
					System.out.println("\n*************************************************");

					System.exit(0); //Finaliza o sistema após realizar o método da conta corrente
				}


			//No caso da conta for poupança, efetua o método dentro da classe contaPoupança	
			} else if(tipoConta == 2) {
				//Método para data de aniversário da conta bancária
				LocalDate dataAniv = LocalDate.now(); //LocalDate é capaz de pegar a data em tempo real
				DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy"); //Definindo o formato que a data será exibida
				String dataFormatada = dataAniv.format(formatadorData); //Formatando dataAniv
				contaPoupanca.setDataAniversario(dataFormatada);

				System.out.println("Aniversário da conta: " + dataFormatada);

				//Método de depósito de dinheiro		
				System.out.println("\n*************************************************");
				System.out.println("** Depósito de Dinheiro **");
				System.out.print("\nInforme a quantidade que deseja depositar: ");
				valorDeposito = leia.nextFloat(); //Declarando o valor de depósito

				novaConta.depositar(valorDeposito);
				System.out.println("\nO novo saldo é de: " + novaConta.getSaldo());
				System.out.println("\n*************************************************");

				System.exit(0); //Finaliza o sistema após realizar o método da conta poupança

			} else {
				System.out.println("\nTipo de conta inválida!");
				System.exit(0); //Fecha o sistema caso o tipo de conta seja inválido
			}

			
		}

	}
