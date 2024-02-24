package conta;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;
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
		ContaPoupanca contaPoupanca = new ContaPoupanca(null, 0, 0, 0, 0, null);
		ContaController contas = new ContaController();
		Cores cores = new Cores();

		//Variável para o Menu
		int opcao = 0;

		//Variáveis de Conta
		String nomeTitular, dataFormada;
		int agenciaConta = 0, numeroConta = 0, tipoConta;
		float saldo = 0;

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
			case 1:
				//Variáveis TryCatch
				boolean loop = true;
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
				System.out.print("Digite o nome do titular: ");
				System.out.println(Cores.TEXT_WHITE_BOLD);
				leia.nextLine();
				nomeTitular = leia.nextLine();
				novaConta.setNomeTitular(nomeTitular);
				System.out.println(Cores.TEXT_RESET + Cores.TEXT_CYAN);

				//Agência do titular
				//Criando um TryCatch para impedir que o usuário digite um valor incorreto
				do {
					try {
						System.out.print("Digite o número da agência: ");
						System.out.println(Cores.TEXT_WHITE_BOLD);
						agenciaConta = leia.nextInt();
						novaConta.setAgenciaConta(agenciaConta);
						System.out.println(Cores.TEXT_RESET + Cores.TEXT_CYAN);

						loop = false;

					//Caso o usuário insira um valor diferente de int, o programa não fecha, mas informa o usuário e dá a oportunidade de inserir um valor int
					} catch (InputMismatchException e) { //Devolução de erro
						System.out.println("                                                 ");
						System.err.println(Cores.TEXT_RED + "Exceção: " + e + Cores.TEXT_RESET); 
						leia.nextLine();
						System.out.println(Cores.TEXT_RED_BOLD + "Digite um número inteiro!" + Cores.TEXT_RESET + Cores.TEXT_CYAN);
					} 
				} while(loop);

				//Tipo de conta
				System.out.print("Digite o tipo da conta, 1 corrente, 2 poupança: ");
				System.out.println(Cores.TEXT_WHITE_BOLD);
				tipoConta = leia.nextInt();
				novaConta.setTipoConta(tipoConta);
				System.out.println(Cores.TEXT_RESET + Cores.TEXT_CYAN);

				
				//Efetuando os métodos de acordo com o tipo de conta selecionado
				//No caso da conta for corrente, efetua o método dentro da classe contaCorrente
				if(tipoConta == 1) {//Conta Corrente
					
					do {
						try {
							//Saldo mensal
							System.out.print("Digite o saldo mensal da conta: ");
							System.out.println(Cores.TEXT_WHITE_BOLD);
							saldo = leia.nextFloat();
							novaConta.setSaldo(saldo);
							System.out.println(Cores.TEXT_RESET + Cores.TEXT_CYAN);

							//Limite - optei por já deixá-lo declarado
							float limiteSaque = saldo  * 0.90f; //Aqui, estamos declarando que o limite será igual a 90% do saldo, 
							//assim o usuário não poderá sacar mais do que tem na conta e também não poderá deixar sua conta vazia
							contaCorrente.setLimite(limiteSaque);
							System.out.print("Limite de crédito da conta: ");
							System.out.println(Cores.TEXT_WHITE_BOLD + limiteSaque + Cores.TEXT_RESET + Cores.TEXT_CYAN);

							System.out.println("\n");
							//Cadastra as informações fornecidas pelo usuário, e o salva como uma conta do Array/Lista de contas
							contas.cadastrar(new ContaCorrente(nomeTitular, agenciaConta, tipoConta, contas.gerarNumero(), saldo, limiteSaque));

							loop2 = false;

						} catch (InputMismatchException e) { //Devolução de erro
							System.out.println("                                                 ");
							System.err.println(Cores.TEXT_RED + "Exceção: " + e + Cores.TEXT_RESET); 
							leia.nextLine();
							System.out.println(Cores.TEXT_RED_BOLD + "Digite um número inteiro!" + Cores.TEXT_RESET + Cores.TEXT_CYAN);
						} 
					} while(loop2);

				} else if(tipoConta == 2) { //Conta Poupança

					do {
						try {
							//Saldo mensal
							System.out.print("Digite o saldo mensal da conta: ");
							System.out.println(Cores.TEXT_WHITE_BOLD);
							saldo = leia.nextFloat();
							novaConta.setSaldo(saldo);
							System.out.println(Cores.TEXT_RESET + Cores.TEXT_CYAN);

							//Data de aniversário - já declarei a variável, pois como o usuário cria a conta na hora, nada mais justo que o aniversário ser o dia da criação
							//Método para data de aniversário da conta bancária
							LocalDate dataAniv = LocalDate.now(); //LocalDate é capaz de pegar a data em tempo real
							DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy"); //Definindo o formato que a data será exibida
							String dataFormatada = dataAniv.format(formatadorData); //Formatando dataAniv
							contaPoupanca.setDataAniversario(dataFormatada);

							System.out.println("Aniversário da conta: ");
							System.out.println(Cores.TEXT_WHITE_BOLD + dataFormatada + Cores.TEXT_RESET + Cores.TEXT_CYAN);

							System.out.println("\n");
							//Cadastra as informações fornecidas pelo usuário, e o salva como uma conta do Array/Lista de contas
							contas.cadastrar(new ContaPoupanca(nomeTitular, agenciaConta, tipoConta, contas.gerarNumero(), saldo, dataFormatada));

							loop3 = false;

						} catch (InputMismatchException e) {//Devolução de erro
							System.out.println("                                                 ");
							System.err.println(Cores.TEXT_RED + "Exceção: " + e + Cores.TEXT_RESET); 
							leia.nextLine();
							System.out.println(Cores.TEXT_RED_BOLD + "Digite um número inteiro!" + Cores.TEXT_RESET + Cores.TEXT_CYAN);
						} 
					} while(loop3);

				} else { //Caso o usuário escolha um tipo de conta diferente de 1 ou 2
					System.out.println("                                                 ");
					System.out.println(Cores.TEXT_RED_BOLD + "Tipo de conta inválida!" + Cores.TEXT_RESET);
				}

				System.out.println(Cores.TEXT_CYAN);
				System.out.println("*************************************************");
				System.out.println(Cores.TEXT_RESET);

				continuarPrograma();
				break;

			case 2:
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

			case 3:
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
						int numero = leia.nextInt();
						System.out.println(Cores.TEXT_RESET + Cores.TEXT_PURPLE_BRIGHT);

						//Devolve a conta encontrada
						contas.procurarPorNumero(numero);
						System.out.println(Cores.TEXT_RESET);

						loop4 = false;

					} catch (InputMismatchException e) { //Devolução de erro
						System.out.println("                                                 ");
						System.err.println(Cores.TEXT_RED + "Exceção: " + e + Cores.TEXT_RESET); 
						leia.nextLine();
						System.out.println(Cores.TEXT_RED_BOLD + "Digite um número inteiro!" + Cores.TEXT_RESET + Cores.TEXT_PURPLE); //Instrução
					}
				} while(loop4);

				continuarPrograma();
				break;

			case 4:
				//Variável TryCatch
				boolean loop5 = true;
				boolean loop6 = true;

				System.out.println(Cores.TEXT_BLUE_BRIGHT);
				System.out.println("*************************************************");
				System.out.println("                                                 ");
				System.out.println("            ATUALIZAR DADOS DA CONTA             ");
				System.out.println("                                                 ");
				System.out.println("*************************************************");

				//NÃO DEU CERTO DE JEITO NENHUM :( 
				//Fiquei presa nisso até tarde, por isso não busquei ajuda dos meus colegas com consciência de que é uma sexta-feira e já são 22h da noite
				
				/*//Implementando o método de busca por conta para atualizar a conta desejada pelo usuário
				//TryCatch para impedir que o usuário busque por um valor diferente de int
				do {
					try {
						//Declarando os valor das variável número
						System.out.print("\nDigite o número da conta que deseja atualizar: ");
						System.out.println(Cores.TEXT_WHITE_BOLD);
						int numero = leia.nextInt();
						System.out.println(Cores.TEXT_RESET + Cores.TEXT_BLUE_BRIGHT);

						var buscarConta = contas.buscarNaCollection(numero);

						if(buscarConta != null) {
							
							tipoConta = buscarConta.getTipoConta();

							System.out.print("Digite o nome do titular: ");
							System.out.println(Cores.TEXT_WHITE_BOLD);
							leia.skip("\\R?");
							nomeTitular = leia.nextLine();
							System.out.println(Cores.TEXT_RESET + Cores.TEXT_BLUE_BRIGHT);

							//Criando um TryCatch para impedir que o usuário digite um valor incorreto
							do {
								try {
									System.out.print("Digite o número da agência: ");
									System.out.println(Cores.TEXT_WHITE_BOLD);
									agenciaConta = leia.nextInt();
									System.out.println(Cores.TEXT_RESET + Cores.TEXT_BLUE_BRIGHT);

									loop6 = false;

								} catch (InputMismatchException e) { //Devolução de erro
									System.out.println("                                                 ");
									System.err.println(Cores.TEXT_RED + "Exceção: " + e + Cores.TEXT_RESET); 
									leia.nextLine();
									System.out.println(Cores.TEXT_RED_BOLD + "Digite um número inteiro!" + Cores.TEXT_RESET + Cores.TEXT_BLUE_BRIGHT);
								} 
							} while(loop6);

							float limiteSaque = saldo  * 0.90f; //Aqui, estamos declarando que o limite será igual a 90% do saldo, 
							//assim o usuário não poderá sacar mais do que tem na conta e também não poderá deixar sua conta vazia
							contaCorrente.setLimite(limiteSaque);
							System.out.println("Limite de crédito da conta: ");
							System.out.println(Cores.TEXT_WHITE_BOLD + limiteSaque + Cores.TEXT_RESET + Cores.TEXT_BLUE_BRIGHT);
							
							//Método para data de aniversário da conta bancária
							LocalDate dataAniv = LocalDate.now(); //LocalDate é capaz de pegar a data em tempo real
							DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy"); //Definindo o formato que a data será exibida
							String dataFormatada = dataAniv.format(formatadorData); //Formatando dataAniv
							contaPoupanca.setDataAniversario(dataFormatada);

							System.out.println("Aniversário da conta: ");
							System.out.println(Cores.TEXT_WHITE_BOLD + dataFormatada + Cores.TEXT_RESET + Cores.TEXT_BLUE_BRIGHT);
							
							switch(tipoConta) {
							case 1 -> {
								boolean loop7 = true;
								
								do {
									try {
										System.out.print("Digite o saldo mensal da conta: ");
										System.out.println(Cores.TEXT_WHITE_BOLD);
										saldo = leia.nextFloat();
										novaConta.setSaldo(saldo);
										System.out.println(Cores.TEXT_RESET + Cores.TEXT_BLUE_BRIGHT);
										
										
										System.out.println("\n");
										contas.atualizar(new ContaCorrente(nomeTitular, agenciaConta, tipoConta, numeroConta, saldo, limiteSaque));

										loop7 = false;

									} catch (InputMismatchException e) { //Devolução de erro
										System.out.println("                                                 ");
										System.err.println(Cores.TEXT_RED + "Exceção: " + e + Cores.TEXT_RESET); 
										leia.nextLine();
										System.out.println(Cores.TEXT_RED_BOLD + "Digite um número inteiro!" + Cores.TEXT_RESET + Cores.TEXT_BLUE_BRIGHT);
									} 
								} while(loop7);
							}

							case 2 -> {
								boolean loop8 = true;
								
								do {
									try {
										System.out.print("Digite o saldo mensal da conta: ");
										System.out.println(Cores.TEXT_WHITE_BOLD);
										saldo = leia.nextFloat();
										novaConta.setSaldo(saldo);
										System.out.println(Cores.TEXT_RESET + Cores.TEXT_BLUE_BRIGHT);

										System.out.println("\n");
										contas.atualizar(new ContaPoupanca(nomeTitular, agenciaConta, tipoConta, numeroConta, saldo, dataFormatada));

										loop8 = false;

									} catch (InputMismatchException e) {//Devolução de erro
										System.out.println("                                                 ");
										System.err.println(Cores.TEXT_RED + "Exceção: " + e + Cores.TEXT_RESET); 
										leia.nextLine();
										System.out.println(Cores.TEXT_RED_BOLD + "Digite um número inteiro!" + Cores.TEXT_RESET + Cores.TEXT_BLUE_BRIGHT);
									} 
								} while(loop8);
							}
							
							default -> {
								System.out.println(Cores.TEXT_BLUE_BOLD + "Tipo de conta inválida!" + Cores.TEXT_RESET + Cores.TEXT_BLUE_BRIGHT);
							}
							} //Fim do SwitchCase

							System.out.println(Cores.TEXT_BLUE_BRIGHT);
							System.out.println("*************************************************");
							System.out.println(Cores.TEXT_RESET);

						} else {
							System.out.println(Cores.TEXT_BLUE_BOLD + numero + "° conta não foi encontrada!" + Cores.TEXT_RESET + Cores.TEXT_BLUE_BRIGHT);
							System.out.println("                                                 ");
							System.out.println("*************************************************");
						}

						System.out.println(Cores.TEXT_RESET);

						loop5 = false;

					} catch (InputMismatchException e) { //Devolução de erro
						System.out.println("                                                 ");
						System.err.println(Cores.TEXT_RED + "Exceção: " + e + Cores.TEXT_RESET); 
						leia.nextLine();
						System.out.println(Cores.TEXT_RED_BOLD + "Digite um número inteiro!" + Cores.TEXT_RESET + Cores.TEXT_BLUE_BRIGHT); //Instrução
					}
				} while(loop5); */

				continuarPrograma();
				break;

			case 5:
				//Variável TryCatch
				boolean loop9 = true;

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
						int numero = leia.nextInt();
						System.out.println(Cores.TEXT_RESET + Cores.TEXT_RED);

						//Deleta a conta encontrada
						contas.deletar(numero);
						System.out.println(Cores.TEXT_RESET);

						loop9 = false;

					} catch (InputMismatchException e) { //Devolução de erro
						System.out.println("                                                 ");
						System.err.println(Cores.TEXT_RED + "Exceção: " + e + Cores.TEXT_RESET); 
						leia.nextLine();
						System.out.println(Cores.TEXT_RED_BOLD + "Digite um número inteiro!" + Cores.TEXT_RESET + Cores.TEXT_PURPLE); //Instrução
					}
				} while(loop9);

				continuarPrograma();
				break;

			case 6:
				System.out.println("                                                 ");
				System.out.println("                      Sacar                      ");
				System.out.println("                                                 ");
				System.out.println("*************************************************");

				break;

			case 7:
				System.out.println("                                                 ");
				System.out.println("                    Depositar                    ");
				System.out.println("                                                 ");
				System.out.println("*************************************************");

				continuarPrograma();
				break;

			case 8:
				System.out.println("                                                 ");
				System.out.println("          Tranferir valores entre contas         ");
				System.out.println("                                                 ");
				System.out.println("*************************************************");

				continuarPrograma();
				break;

			case 9:
				System.out.println(Cores.TEXT_BLUE + Cores.ANSI_BLACK_BACKGROUND);
				System.out.println("                                                 ");
				System.out.println("  BIANCA TRUST BANK agradeçe pela confiança ;)   ");
				novaConta.sobre();
				System.out.println(Cores.TEXT_RESET);
				System.exit(0);

				continuarPrograma();
				break;

			default:
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
