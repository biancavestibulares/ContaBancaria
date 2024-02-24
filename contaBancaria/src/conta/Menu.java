package conta;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import conta.controller.ContaController;
import conta.model.Conta;
import conta.model.ContaCorrente;
import conta.model.ContaPoupanca;
import conta.util.Cores;

public class Menu {

	static Scanner leia = new Scanner(System.in);

	public static void main(String[] args) {
		//Importação da classe "Conta" para fazer uso de seus atributos
		Conta novaConta = new Conta(null, 0, 0, 0, 0) { //Aqui, declaramos os atributos com valores vazios para que o usuário os registre
			//Temos que importar a Conta desta forma por ela ser uma classe abstract
		};
		//É imaginado que o usuário seja algum REPRESENTANTE do banco
		//Import de outras classes
		ContaCorrente contaCorrente = new ContaCorrente(null, 0, 0, 0, 0, 0);
		ContaPoupanca contaPoupanca = new ContaPoupanca(null, 0, 0, 0, 0, 0);
		ContaController contas = new ContaController();
		Cores cores = new Cores();

		//Variável para o Menu
		int opcao = 0;

		//Variáveis de Conta
		String nomeTitular;
		int agenciaConta = 0, numero = 0, tipoConta = 0, dataAniversario = 0, numeroDestino = 0;
		float saldo = 0, limite = 0, valor = 0, valor1 = 0;

		while(true) {
			//Imprimindo no console as opções do menu
			System.out.println(Cores.TEXT_CYAN + Cores.ANSI_BLACK_BACKGROUND);
			System.out.println("                                                 ");
			System.out.println("                BIANCA TRUST BANK                ");
			System.out.println("                                                 ");
			System.out.println("*************************************************");
			System.out.println("                                                 ");
			System.out.println("     Opção 1 - Criar conta                       ");
			System.out.println("     Opção 2 - Listar todas as contas            ");
			System.out.println("     Opção 3 - Buscar conta por número           ");
			System.out.println("     Opção 4 - Atualizar dados da conta          ");
			System.out.println("     Opção 5 - Apagar conta                      ");
			System.out.println("     Opção 6 - Sacar                             ");
			System.out.println("     Opção 7 - Depositar                         ");
			System.out.println("     Opção 8 - Tranferir valores entre contas    ");
			System.out.println("     Opção 9 - Sair                              ");
			System.out.println("                                                 ");
			System.out.println("*************************************************");
			System.out.println("                                                 ");
			System.out.println(Cores.TEXT_RESET);
			System.out.print("Insira a opção desejada: ");
			System.out.println("                                                 ");

			//Criando um TryCatch no caso do usuário digitar uma String ao invés de um int
			try {
				opcao = leia.nextInt();
			} catch (InputMismatchException e) { //InputMismatchException é um erro captado quando a devolução não é um int
				System.err.println("\nExceção: " + e); //System.err.println imprime a exceção/erro - informa o usuário do problema encontrado
				leia.nextLine();
				System.out.println(Cores.TEXT_WHITE_BOLD + "Por favor, escolha uma das opções do Menu" + Cores.TEXT_RESET); //Instrução
			}

			//Criando casos para cada uma das opções
			switch(opcao) {
			case 1: //Criar conta
				//Variáveis TryCatch
				boolean loop = true;
				boolean loop0 = true;
				boolean loop1 = true;
				boolean loop2 = true;
				boolean loop3 = true;

				System.out.println(Cores.TEXT_CYAN);
				System.out.println("*************************************************");
				System.out.println("                                                 ");
				System.out.println("                   CRIAR CONTA                   ");
				System.out.println("                                                 ");
				System.out.println("*************************************************");
				System.out.println("                                                 ");

				//Declarando os valores das variáveis
				//Nome do titular
				System.out.println("Digite o nome do titular: ");
				System.out.print(Cores.TEXT_WHITE_BOLD);
				leia.skip("\\R?");
				nomeTitular = leia.nextLine();
				System.out.println(Cores.TEXT_RESET + Cores.TEXT_CYAN);

				//Agência do titular
				//Criando um TryCatch para impedir que o usuário digite um valor incorreto
				do {
					try {
						System.out.println("Digite o número da agência: ");
						System.out.print(Cores.TEXT_WHITE_BOLD);
						agenciaConta = leia.nextInt();
						System.out.println(Cores.TEXT_RESET + Cores.TEXT_CYAN);

						loop = false;

						//Caso o usuário insira um valor diferente de int, o programa não fecha, mas informa o usuário e dá a oportunidade de inserir um valor int
					} catch (InputMismatchException e) { //Devolução de erro
						System.out.println("                                                 ");
						System.err.println(Cores.TEXT_RED + "Exceção: " + e + Cores.TEXT_RESET); 
						leia.nextLine();
						System.out.println(Cores.TEXT_RED_BOLD + "Digite um número inteiro!" + Cores.TEXT_RESET + Cores.TEXT_CYAN);
						System.out.println("                                                 ");
					} 
				} while(loop);

				//Tipo de conta				
				do {
					try {
						System.out.print("Digite o tipo da conta, 1 corrente, 2 poupança: ");
						System.out.println(Cores.TEXT_WHITE_BOLD);
						tipoConta = leia.nextInt();
						System.out.println(Cores.TEXT_RESET + Cores.TEXT_CYAN);

						loop0 = false;
					} catch(InputMismatchException e) {
						System.out.println("                                                 ");
						System.err.println(Cores.TEXT_RED + "Exceção: " + e + Cores.TEXT_RESET); 
						leia.nextLine();
						System.out.println(Cores.TEXT_RED_BOLD + "Digite um número inteiro!" + Cores.TEXT_RESET + Cores.TEXT_CYAN);
						System.out.println("                                                 ");
					}
				}while(loop0 && tipoConta < 1 && tipoConta > 2);

				//Saldo mensal
				do {
					try {
						System.out.print("Digite o saldo mensal da conta (R$): ");
						System.out.println(Cores.TEXT_WHITE_BOLD);
						saldo = leia.nextFloat();
						System.out.println(Cores.TEXT_RESET + Cores.TEXT_CYAN);

						loop1 = false;

					} catch (InputMismatchException e) { //Devolução de erro
						System.out.println("                                                 ");
						System.err.println(Cores.TEXT_RED + "Exceção: " + e + Cores.TEXT_RESET); 
						leia.nextLine();
						System.out.println(Cores.TEXT_RED_BOLD + "Digite um número inteiro!" + Cores.TEXT_RESET + Cores.TEXT_CYAN);
						System.out.println("                                                 ");
					} 
				} while(loop1);

				if(tipoConta == 1 || tipoConta == 2) {
					switch(tipoConta) {
					case 1 -> {
						//Executando um TryCatch em caso do usuário escrever uma variável que se diferencie de int
						do {
							try {
								//Limite de saque
								System.out.print("Digite o limite de crédito da conta (R$): ");
								System.out.println(Cores.TEXT_WHITE_BOLD);
								limite = leia.nextFloat();
								System.out.println(Cores.TEXT_RESET + Cores.TEXT_CYAN);

								System.out.println("\n");
								//Cadastra as informações fornecidas pelo usuário, e o salva como uma conta do Array/Lista de contas
								contas.cadastrar(new ContaCorrente(nomeTitular, agenciaConta, tipoConta, contas.gerarNumero(), saldo, limite));
								loop2 = false;

								//Caso o usuário insira um valor diferente de int, o programa não fecha, mas informa o usuário e dá a oportunidade de inserir um valor int
							} catch (InputMismatchException e) { //Devolução de erro
								System.out.println("                                                 ");
								System.err.println(Cores.TEXT_RED + "Exceção: " + e + Cores.TEXT_RESET); 
								leia.nextLine();
								System.out.println(Cores.TEXT_RED_BOLD + "Digite um número inteiro!" + Cores.TEXT_RESET + Cores.TEXT_CYAN);
								System.out.println("                                                 ");
							} 
						} while(loop2);

					}

					case 2 -> {
						do {
							try {
								//Data de aniversário da conta
								System.out.print("Digite o dia do aniversário da conta: ");
								System.out.println(Cores.TEXT_WHITE_BOLD);
								dataAniversario = leia.nextInt();
								System.out.println(Cores.TEXT_RESET + Cores.TEXT_CYAN);

								System.out.println("\n");
								//Cadastra as informações fornecidas pelo usuário, e o salva como uma conta do Array/Lista de contas
								contas.cadastrar(new ContaPoupanca(nomeTitular, agenciaConta, tipoConta, contas.gerarNumero(), saldo, dataAniversario));

								loop3 = false;

							}catch (InputMismatchException e) { //Devolução de erro
								System.out.println("                                                 ");
								System.err.println(Cores.TEXT_RED + "Exceção: " + e + Cores.TEXT_RESET); 
								leia.nextLine();
								System.out.println(Cores.TEXT_RED_BOLD + "Digite um número inteiro!" + Cores.TEXT_RESET + Cores.TEXT_CYAN);
								System.out.println("                                                 ");
							} 
						} while(loop3);

					}
					} //Fim do SwitchCase

				} else {
					System.out.println("                                                 ");
					System.out.println(Cores.TEXT_RED_BOLD + "Tipo de conta inválida!" + Cores.TEXT_RESET);
				}

				System.out.println(Cores.TEXT_CYAN);
				System.out.println("*************************************************");
				System.out.println(Cores.TEXT_RESET);

				continuarPrograma();
				break;

			case 2: //Listar contas
				System.out.println(Cores.TEXT_YELLOW);
				System.out.println("*************************************************");
				System.out.println("                                                 ");
				System.out.println("             LISTAR TODAS AS CONTAS              ");
				System.out.println("                                                 ");
				System.out.println("*************************************************");

				//Lista as contas adicionadas em contaController
				contas.listarTodas();

				System.out.println(Cores.TEXT_RESET);

				continuarPrograma();
				break;

			case 3: //Buscar conta por número
				//Variável TryCatch
				boolean loop4 = true;

				System.out.println(Cores.TEXT_PURPLE_BRIGHT);
				System.out.println("*************************************************");
				System.out.println("                                                 ");
				System.out.println("            BUSCAR CONTA POR NÚMERO              ");
				System.out.println("                                                 ");
				System.out.println("*************************************************");

				//TryCatch para impedir que o usuário busque por um valor diferente de int
				do {
					try {
						//Declarando os valor das variável número
						System.out.print("\nDigite o número da conta que deseja buscar: ");
						System.out.println(Cores.TEXT_WHITE_BOLD);
						numero = leia.nextInt();
						System.out.println(Cores.TEXT_RESET + Cores.TEXT_PURPLE_BRIGHT);

						//Devolve a conta encontrada
						contas.procurarPorNumero(numero);
						System.out.println(Cores.TEXT_RESET);

						loop4 = false;

					} catch (InputMismatchException e) { //Devolução de erro
						System.out.println("                                                 ");
						System.err.println(Cores.TEXT_RED + "Exceção: " + e + Cores.TEXT_RESET); 
						leia.nextLine();
						System.out.println(Cores.TEXT_RED_BOLD + "Digite um número inteiro!" + Cores.TEXT_RESET + Cores.TEXT_PURPLE_BRIGHT); //Instrução
					}
				} while(loop4);

				continuarPrograma();
				break;

			case 4: //Atualizar conta
				//Variáveis TryCatch
				boolean loop5 = true;
				boolean loop6 = true;
				boolean loop7 = true;
				boolean loop8 = true;
				boolean loop9 = true;

				System.out.println(Cores.TEXT_BLUE_BRIGHT);
				System.out.println("*************************************************");
				System.out.println("                                                 ");
				System.out.println("            ATUALIZAR DADOS DA CONTA             ");
				System.out.println("                                                 ");
				System.out.println("*************************************************");

				//As funções executadas a seguir, serão exatamente iguais as funções de CRIAR CONTA
				//No entanto, o usuário não pode mudar o tipo de conta! Apenas o conteúdo das case selecionada no cadastro
				//TryCatch 
				do {
					try {
						//Declarando os valor das variável número
						System.out.print("\nDigite o número da conta que deseja atualizar: ");
						System.out.println(Cores.TEXT_WHITE_BOLD);
						numero = leia.nextInt();
						System.out.println(Cores.TEXT_RESET + Cores.TEXT_BLUE_BRIGHT);

						loop5 = false;

					} catch (InputMismatchException e) { //Devolução de erro
						System.out.println("                                                 ");
						System.err.println(Cores.TEXT_RED + "Exceção: " + e + Cores.TEXT_RESET); 
						leia.nextLine();
						System.out.println(Cores.TEXT_RED_BOLD + "Digite um número inteiro!" + Cores.TEXT_RESET + Cores.TEXT_BLUE_BRIGHT); //Instrução
					}
				} while(loop5);

				//Buscando por uma conta especifica na collection
				var buscarContas = contas.buscarNaCollection(numero);

				if(buscarContas != null ) {
					tipoConta = buscarContas.getTipoConta(); //Declarando o tipo de conta

					//Declarando os valores das variáveis
					//Nome do titular
					System.out.println("Digite o nome do titular: ");
					System.out.print(Cores.TEXT_WHITE_BOLD);
					leia.skip("\\R?");
					nomeTitular = leia.nextLine();
					System.out.println(Cores.TEXT_RESET + Cores.TEXT_BLUE_BRIGHT);

					//Agência do titular
					//Criando um TryCatch para impedir que o usuário digite um valor incorreto
					do {
						try {
							System.out.println("Digite o número da agência: ");
							System.out.print(Cores.TEXT_WHITE_BOLD);
							agenciaConta = leia.nextInt();
							System.out.println(Cores.TEXT_RESET + Cores.TEXT_BLUE_BRIGHT);

							loop6 = false;

							//Caso o usuário insira um valor diferente de int, o programa não fecha, mas informa o usuário e dá a oportunidade de inserir um valor int
						} catch (InputMismatchException e) { //Devolução de erro
							System.out.println("                                                 ");
							System.err.println(Cores.TEXT_RED + "Exceção: " + e + Cores.TEXT_RESET); 
							leia.nextLine();
							System.out.println(Cores.TEXT_RED_BOLD + "Digite um número inteiro!" + Cores.TEXT_RESET + Cores.TEXT_BLUE_BRIGHT);
							System.out.println("                                                 ");
						} 
					} while(loop6);

					//Saldo mensal
					do {
						try {
							System.out.print("Digite o saldo mensal da conta (R$): ");
							System.out.println(Cores.TEXT_WHITE_BOLD);
							saldo = leia.nextFloat();
							System.out.println(Cores.TEXT_RESET + Cores.TEXT_BLUE_BRIGHT);

							loop7 = false;

						} catch (InputMismatchException e) { //Devolução de erro
							System.out.println("                                                 ");
							System.err.println(Cores.TEXT_RED + "Exceção: " + e + Cores.TEXT_RESET); 
							leia.nextLine();
							System.out.println(Cores.TEXT_RED_BOLD + "Digite um número inteiro!" + Cores.TEXT_RESET + Cores.TEXT_BLUE_BRIGHT);
							System.out.println("                                                 ");
						} 
					} while(loop7);

					//Tipo de Conta
					switch(tipoConta) {
					case 1 -> {
						do {
							try {
								//Limite de saque
								System.out.print("Digite o limite de crédito da conta (R$): ");
								System.out.println(Cores.TEXT_WHITE_BOLD);
								limite = leia.nextFloat();
								System.out.println(Cores.TEXT_RESET + Cores.TEXT_BLUE_BRIGHT);

								System.out.println("\n");
								//Atualiza as informações fornecidas pelo usuário, e substituí os valores que a conta possuía antes
								contas.atualizar(new ContaCorrente(nomeTitular, agenciaConta, tipoConta, tipoConta, saldo, limite));
								loop8 = false;

							} catch (InputMismatchException e) { //Devolução de erro
								System.out.println("                                                 ");
								System.err.println(Cores.TEXT_RED + "Exceção: " + e + Cores.TEXT_RESET); 
								leia.nextLine();
								System.out.println(Cores.TEXT_RED_BOLD + "Digite um número inteiro!" + Cores.TEXT_RESET + Cores.TEXT_BLUE_BRIGHT);
								System.out.println("                                                 ");
							} 
						} while(loop8);

					}

					case 2 -> {
						do {
							try {
								//Data de aniversário da conta
								System.out.print("Digite o dia do aniversário da conta: ");
								System.out.println(Cores.TEXT_WHITE_BOLD);
								dataAniversario = leia.nextInt();
								System.out.println(Cores.TEXT_RESET + Cores.TEXT_BLUE_BRIGHT);

								System.out.println("\n");
								//Atualiza as informações fornecidas pelo usuário, e substituí os valores que a conta possuía antes
								contas.atualizar(new ContaPoupanca(nomeTitular, agenciaConta, tipoConta, tipoConta, saldo, dataAniversario));

								loop9 = false;

							}catch (InputMismatchException e) { //Devolução de erro
								System.out.println("                                                 ");
								System.err.println(Cores.TEXT_RED + "Exceção: " + e + Cores.TEXT_RESET); 
								leia.nextLine();
								System.out.println(Cores.TEXT_RED_BOLD + "Digite um número inteiro!" + Cores.TEXT_RESET + Cores.TEXT_BLUE_BRIGHT);
								System.out.println("                                                 ");
							} 
						} while(loop9);

					}
					} //Fim do SwitchCase

				} else {
					//Conta não encontrada
					System.out.println(Cores.TEXT_BLUE_BOLD_BRIGHT + "\nA " + numero + "° conta não foi encontrada!" + Cores.TEXT_RESET + Cores.TEXT_BLUE_BRIGHT);
					System.out.println("                                                 ");
					System.out.println("*************************************************");
				}

				continuarPrograma();
				break;

			case 5: //Apagar conta
				//Variável TryCatch
				boolean loop10 = true;

				System.out.println(Cores.TEXT_RED);
				System.out.println("*************************************************");
				System.out.println("                                                 ");
				System.out.println("                   APAGAR CONTA                  ");
				System.out.println("                                                 ");
				System.out.println("*************************************************");

				//TryCatch para impedir que o usuário busque por um valor diferente de int
				do {
					try {
						//Declarando os valor das variável número
						System.out.print("\nDigite o número da conta que deseja deletar: ");
						System.out.println(Cores.TEXT_WHITE_BOLD);
						numero = leia.nextInt();
						System.out.println(Cores.TEXT_RESET + Cores.TEXT_RED);

						//Deleta a conta encontrada
						contas.deletar(numero);
						System.out.println(Cores.TEXT_RESET);

						loop10 = false;

					} catch (InputMismatchException e) { //Devolução de erro
						System.out.println("                                                 ");
						System.err.println("Exceção: " + e); 
						leia.nextLine();
						System.out.println(Cores.TEXT_RED_BOLD + "Digite um número inteiro!" + Cores.TEXT_RESET + Cores.TEXT_RED); //Instrução
						System.out.println("                                                 ");
					}
				} while(loop10);

				continuarPrograma();
				break;

			case 6:
				//Variável TryCatch
				boolean loop11 = true;
				boolean loop12 = true;
				boolean loop13 = true;
				boolean loop14 = true;

				System.out.println(Cores.TEXT_GREEN_BRIGHT);
				System.out.println("*************************************************");
				System.out.println("                                                 ");
				System.out.println("                      SACAR                      ");
				System.out.println("                                                 ");
				System.out.println("*************************************************");

				do {
					try {
						//Declarando os valor das variável número
						System.out.println("\nDigite o número da conta que deseja sacar: ");
						System.out.println(Cores.TEXT_WHITE_BOLD);
						numero = leia.nextInt();
						System.out.println(Cores.TEXT_RESET + Cores.TEXT_GREEN_BRIGHT);

						loop11 = false;

					} catch (InputMismatchException e) { //Devolução de erro
						System.out.println("                                                 ");
						System.err.println(Cores.TEXT_RED + "Exceção: " + e + Cores.TEXT_RESET); 
						leia.nextLine();
						System.out.println(Cores.TEXT_RED_BOLD + "Digite um número inteiro!" + Cores.TEXT_RESET + Cores.TEXT_GREEN_BRIGHT); //Instrução
						System.out.println("                                                 ");
					}
				} while(loop11);
				
				//Busca qual a conta inserida
				var buscarContas2 = contas.buscarNaCollection(numero);
				
				if(buscarContas2 != null) {
					tipoConta = buscarContas2.getTipoConta(); //Determina o tipo de conta entre corrente e poupança
				}
				//Criando um método de saque para cada tipo de conta
				switch(tipoConta) {
				//CONTA CORRENTE
				case 1 -> {
					/*//Informando o usuário do limite de saque que cadastrou
					System.out.print(Cores.TEXT_GREEN_BOLD_BRIGHT + "Seu limite de saque é de: " + Cores.TEXT_RESET);
					System.out.println(Cores.TEXT_WHITE_BOLD + limite + Cores.TEXT_RESET);*/

					//Declando o valor de saque
					do {
						try {
							System.out.println("\nDigite o valor que deseja sacar (R$): ");
							System.out.println(Cores.TEXT_WHITE_BOLD);
							valor = leia.nextFloat();
							System.out.println(Cores.TEXT_RESET + Cores.TEXT_GREEN_BRIGHT);

							loop12 = false;
							
						} catch (InputMismatchException e) { //Devolução de erro
							System.out.println("                                                 ");
							System.err.println(Cores.TEXT_RED + "Exceção: " + e + Cores.TEXT_RESET); 
							leia.nextLine();
							System.out.println(Cores.TEXT_RED_BOLD + "Digite um número inteiro!" + Cores.TEXT_RESET + Cores.TEXT_GREEN_BRIGHT); //Instrução
							System.out.println("                                                 ");
						}
					} while(valor <= 0 && loop12);

					//Realiza o método de saque
					contas.sacar(numero, valor, limite); //Conta corrente possui limite
				}
				
				//CONTA POUPANÇA
				case 2 -> {
					/*//Informando o usuário do saldo que cadastrou
					System.out.print(Cores.TEXT_GREEN_BOLD_BRIGHT + "Seu saldo atual é de: " + Cores.TEXT_RESET);
					System.out.println(Cores.TEXT_WHITE_BOLD + saldo + Cores.TEXT_RESET + Cores.TEXT_GREEN_BRIGHT);*/
					
					do {
						try {
							//Declarando os valor das variável número
							System.out.println("\nDigite o número da conta que deseja sacar: ");
							System.out.println(Cores.TEXT_WHITE_BOLD);
							numero = leia.nextInt();
							System.out.println(Cores.TEXT_RESET + Cores.TEXT_GREEN_BRIGHT);

							loop14 = false;

						} catch (InputMismatchException e) { //Devolução de erro
							System.out.println("                                                 ");
							System.err.println(Cores.TEXT_RED + "Exceção: " + e + Cores.TEXT_RESET); 
							leia.nextLine();
							System.out.println(Cores.TEXT_RED_BOLD + "Digite um número inteiro!" + Cores.TEXT_RESET + Cores.TEXT_GREEN_BRIGHT); //Instrução
							System.out.println("                                                 ");
						}
					} while(loop14);
					
					//Informando o usuário do saldo que cadastrou
					System.out.print(Cores.TEXT_GREEN_BOLD_BRIGHT + "Seu saldo atual é de: " + Cores.TEXT_RESET);
					System.out.println(Cores.TEXT_WHITE_BOLD + saldo + Cores.TEXT_RESET + Cores.TEXT_GREEN_BRIGHT);

					//Declando o valor de saque
					do {
						try {
							System.out.println("\nDigite o valor que deseja sacar (R$): ");
							System.out.println(Cores.TEXT_WHITE_BOLD);
							valor1 = leia.nextFloat();
							System.out.println(Cores.TEXT_RESET + Cores.TEXT_GREEN_BRIGHT);

							loop13 = false;
							
						} catch (InputMismatchException e) { //Devolução de erro
							System.out.println("                                                 ");
							System.err.println(Cores.TEXT_RED + "Exceção: " + e + Cores.TEXT_RESET); 
							leia.nextLine();
							System.out.println(Cores.TEXT_RED_BOLD + "Digite um número inteiro!" + Cores.TEXT_RESET + Cores.TEXT_GREEN_BRIGHT); //Instrução
							System.out.println("                                                 ");
						}
					} while(valor1 <= 0 && loop13);

					//Realiza o método de saque
					contas.sacarPoupanca(numero, valor1); //Não possui limite
				}
				} //Fim do SwitchCase

				System.out.println(Cores.TEXT_RESET);

				continuarPrograma();
				break;

			case 7:
				//Variável TryCatch
				boolean loop15 = true;
				boolean loop16 = true;

				System.out.println(Cores.TEXT_CYAN_BRIGHT);
				System.out.println("*************************************************");
				System.out.println("                                                 ");
				System.out.println("                    DEPOSITAR                    ");
				System.out.println("                                                 ");
				System.out.println("*************************************************");

				do {
					try {
						//Declarando os valores das variáveis número
						System.out.println("\nDigite o número da conta que deseja sacar: ");
						System.out.println(Cores.TEXT_WHITE_BOLD);
						numero = leia.nextInt();
						System.out.println(Cores.TEXT_RESET + Cores.TEXT_CYAN_BRIGHT);

						loop15 = false;

					} catch (InputMismatchException e) { //Devolução de erro
						System.out.println("                                                 ");
						System.err.println(Cores.TEXT_RED + "Exceção: " + e + Cores.TEXT_RESET); 
						leia.nextLine();
						System.out.println(Cores.TEXT_RED_BOLD + "Digite um número inteiro!" + Cores.TEXT_RESET + Cores.TEXT_CYAN_BRIGHT); //Instrução
						System.out.println("                                                 ");
					}
				} while(loop15);

				//Declando o valor de depósito
				do {
					try {
						System.out.println("\nDigite o valor que deseja depositar (R$): ");
						System.out.println(Cores.TEXT_WHITE_BOLD);
						valor = leia.nextInt();
						System.out.println(Cores.TEXT_RESET + Cores.TEXT_CYAN_BRIGHT);

						loop16 = false;
					} catch (InputMismatchException e) { //Devolução de erro
						System.out.println("                                                 ");
						System.err.println(Cores.TEXT_RED + "Exceção: " + e + Cores.TEXT_RESET); 
						leia.nextLine();
						System.out.println(Cores.TEXT_RED_BOLD + "Digite um número inteiro!" + Cores.TEXT_RESET + Cores.TEXT_CYAN_BRIGHT); //Instrução
						System.out.println("                                                 ");
					}
				} while(valor <= 0 && loop16);

				//Realiza o método de depósito
				contas.depositar(numero, valor);

				System.out.println(Cores.TEXT_RESET);

				continuarPrograma();
				break;

			case 8:
				//Variável TryCatch
				boolean loop17 = true;
				boolean loop18 = true;
				
				System.out.println(Cores.TEXT_YELLOW_BRIGHT);
				System.out.println("*************************************************");
				System.out.println("                                                 ");
				System.out.println("         TRANSFERIR VALORES ENTRE CONTAS         ");
				System.out.println("                                                 ");
				System.out.println("*************************************************");

				do {
					try {
						//Declarando qual a conta de origem
						System.out.println("\nDigite o número da Conta de Origem: ");
						System.out.println(Cores.TEXT_WHITE_BOLD);
						numero = leia.nextInt();
						System.out.println(Cores.TEXT_RESET + Cores.TEXT_YELLOW_BRIGHT);
						
						//Declarando qual a conta de destino
						System.out.println("Digite o número da Conta de Destino: ");
						System.out.println(Cores.TEXT_WHITE_BOLD);
						numeroDestino = leia.nextInt();
						System.out.println(Cores.TEXT_RESET + Cores.TEXT_YELLOW_BRIGHT);

						loop17 = false;

					} catch (InputMismatchException e) { //Devolução de erro
						System.out.println("                                                 ");
						System.err.println(Cores.TEXT_RED + "Exceção: " + e + Cores.TEXT_RESET); 
						leia.nextLine();
						System.out.println(Cores.TEXT_RED_BOLD + "Digite um número inteiro!" + Cores.TEXT_RESET + Cores.TEXT_YELLOW_BRIGHT); //Instrução
						System.out.println("                                                 ");
					}
				} while(loop17);
				
				//Declarando os valores da transferência
				do {
					try {
						System.out.println("Digite o valor da transferência (R$): ");
						System.out.println(Cores.TEXT_WHITE_BOLD);
						valor = leia.nextFloat();
						System.out.println(Cores.TEXT_RESET + Cores.TEXT_YELLOW_BRIGHT);

						loop17 = false;

					} catch (InputMismatchException e) { //Devolução de erro
						System.out.println("                                                 ");
						System.err.println(Cores.TEXT_RED + "Exceção: " + e + Cores.TEXT_RESET); 
						leia.nextLine();
						System.out.println(Cores.TEXT_RED_BOLD + "Digite um número inteiro!" + Cores.TEXT_RESET + Cores.TEXT_YELLOW_BRIGHT); //Instrução
						System.out.println("                                                 ");
					}
				}while(valor <= 0 && loop18);
				
				contas.transferir(numero, numeroDestino, valor);
				System.out.println(Cores.TEXT_RESET);
				
				continuarPrograma();
				break;

			case 9:
				//Finalização do sistema
				System.out.println(Cores.TEXT_BLUE + Cores.ANSI_BLACK_BACKGROUND);
				System.out.println("                                                 ");
				System.out.println("  BIANCA TRUST BANK agradeçe pela confiança ;)   ");
				novaConta.sobre();
				System.out.println(Cores.TEXT_RESET);
				System.exit(0); //Fecha o programa

				continuarPrograma();
				break;

			default:
				//Retorno caso uma opção improvável tenha sido clicada
				System.out.println(Cores.TEXT_RED_BOLD + "\nOpção Inválida!" + Cores.TEXT_RESET);
				continuarPrograma();
				break;
			}

		} //Fim do While

	}

	//Exigir que a tecla ENTER do teclado seja pressionada para finalizar uma opção do Menu
	public static void continuarPrograma() {
		try {
			System.out.println("                                                 ");
			System.out.println("Pressione Enter para continuar");
			System.in.read();
		} catch(IOException e) {
			System.out.println("Pressione ENTER para continuar");
		}
	}

}
