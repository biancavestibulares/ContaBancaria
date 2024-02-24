package conta.controller;

import java.util.ArrayList;
import java.util.Iterator;

import conta.model.Conta;
import conta.repository.ContaRepository;
import conta.util.Cores;

public class ContaController implements ContaRepository{
	//Import da classe Cores
	Cores cores = new Cores();

	//Criando um ArrayList para guardar os dados das contas cadastradas
	private ArrayList<Conta> listaContas = new ArrayList<Conta>();

	////Variável trabalhada em contaRepositorio
	int numero = 0; 

	//Implementando todos os Métodos da Interface ContaRepository
	@Override
	public void cadastrar(Conta conta) {
		// TODO Auto-generated method stub
		listaContas.add(conta); //Adiciona a conta criada pelo usuário no Array listaContas
		System.out.println(Cores.TEXT_CYAN_BOLD + "A " + conta.getNumeroConta() + "° foi criada com sucesso!" + Cores.TEXT_RESET);
	}

	@Override
	public void listarTodas() {
		// TODO Auto-generated method stub
		for(var conta : listaContas) { //Cada conta possui suas próprias variáveis dentro de listaContas
			//Todas as contas terão seus dados exibidos a partir do método visualizar da classe Conta
			conta.visualizar(); 
		}

		//Verifica se a lista está vazia para dar um retorno ao usuário
		if(listaContas.isEmpty()) {
			System.out.println(Cores.TEXT_YELLOW_BOLD + "\nA lista está vazia!" + Cores.TEXT_RESET + Cores.TEXT_YELLOW);
			System.out.println("                                                 ");
			System.out.println("*************************************************");
		}
	}

	@Override
	public void procurarPorNumero(int numero) {
		// TODO Auto-generated method stub
		//Proncura o objeto conta dentro da collection de acordo com o número recebido
		var conta = buscarNaCollection(numero);

		if(conta != null)  //Se conta for DIFERENTE de null
			conta.visualizar(); //Exibi dados da conta
		else {
			//Conta não foi encontrada
			System.out.println(Cores.TEXT_PURPLE_BOLD_BRIGHT + numero + "° conta não foi encontrada!" + Cores.TEXT_RESET + Cores.TEXT_PURPLE_BRIGHT);
			System.out.println("                                                 ");
			System.out.println("*************************************************");
		}
	}

	@Override
	public void atualizar(Conta conta) {
		// TODO Auto-generated method stub
		//Buscar conta na collection
		var buscarConta = buscarNaCollection(conta.getNumeroConta());

		if(buscarConta != null) {
			listaContas.set(listaContas.indexOf(buscarConta), conta); //Seleciona a conta informada pelo usuário
			System.out.println(Cores.TEXT_BLUE_BOLD_BRIGHT + "\nA " + numero + "° conta foi atualizada com sucesso!" + Cores.TEXT_RESET + Cores.TEXT_BLUE_BRIGHT);
			System.out.println("                                                 ");
			System.out.println("*************************************************");
		} else {
			//Conta não foi encontrada
			System.out.println(Cores.TEXT_BLUE_BOLD_BRIGHT + "\nA " + numero + "° conta não foi encontrada!" + Cores.TEXT_RESET + Cores.TEXT_BLUE_BRIGHT);
			System.out.println("                                                 ");
			System.out.println("*************************************************");
		}

	}

	@Override
	public void deletar(int numero) {
		// TODO Auto-generated method stub
		//Procurar a conta dentro da collection
		var conta = buscarNaCollection(numero);

		if(conta != null) { //Achou
			if(listaContas.remove(conta) == true) { //Caso ache, apaga a conta
				System.out.println(Cores.TEXT_RED_BOLD + "\nA " + numero + "° conta foi deletada com sucesso!" + Cores.TEXT_RESET + Cores.TEXT_RED);
				System.out.println("                                                 ");
				System.out.println("*************************************************");
			} 
		} else {
			//Conta não foi encontrada
			System.out.println(Cores.TEXT_RED_BOLD + numero + "° conta não foi encontrada!" + Cores.TEXT_RESET + Cores.TEXT_RED);
			System.out.println("                                                 ");
			System.out.println("*************************************************");
		}

	}

	@Override
	public void sacar(int numero, float valor, float limite) {
		// TODO Auto-generated method stub
		var conta = buscarNaCollection(numero);

		//Se a conta não for nula
		if(conta != null) {

			//Verifica se o valor de saque excede o limite
			if(valor > limite) {
				System.out.println(Cores.TEXT_GREEN_BOLD_BRIGHT + "Limite de saque ultrapassado!" + Cores.TEXT_RESET + Cores.TEXT_GREEN_BRIGHT);
				System.out.println("                                                 ");
				System.out.println("*************************************************");
			} else {
				//se não exceder, pode realizar o saque
				if(conta.sacar(valor) == true) {
					System.out.println(Cores.TEXT_GREEN_BOLD_BRIGHT + "O saque na " + numero + "° conta foi efetuado com sucesso!" + Cores.TEXT_RESET + Cores.TEXT_GREEN_BRIGHT);
					System.out.println("                                                 ");
					System.out.println("*************************************************");
				}
			}

		} else {
			//Conta não foi encontrada - nula
			System.out.println(Cores.TEXT_GREEN_BOLD_BRIGHT + numero + "° conta não foi encontrada!" + Cores.TEXT_RESET + Cores.TEXT_GREEN_BRIGHT);
			System.out.println("                                                 ");
			System.out.println("*************************************************");
		}

	}

	@Override
	public void sacarPoupanca(int numero, float valor1) {
		// TODO Auto-generated method stub
		var conta = buscarNaCollection(numero);

		//Se a conta não for nula
		if(conta != null) {

			//Verifica se o valor de saque excede o limite
			if(conta.sacar(valor1) == true) {
				System.out.println(Cores.TEXT_GREEN_BOLD_BRIGHT + "O saque na " + numero + "° conta foi efetuado com sucesso!" + Cores.TEXT_RESET + Cores.TEXT_GREEN_BRIGHT);
				System.out.println("                                                 ");
				System.out.println("*************************************************");
			}
		} else {
			//Conta não foi encontrada - nula
			System.out.println(Cores.TEXT_GREEN_BOLD_BRIGHT + numero + "° conta não foi encontrada!" + Cores.TEXT_RESET + Cores.TEXT_GREEN_BRIGHT);
			System.out.println("                                                 ");
			System.out.println("*************************************************");
		}
	}

	@Override
	public void depositar(int numero, float valor) {
		// TODO Auto-generated method stub
		var conta = buscarNaCollection(numero);

		if(conta != null) {
			conta.depositar(valor); //Depositar é possível em uma conta não nula
			System.out.println(Cores.TEXT_CYAN_BOLD_BRIGHT + "O depósito na " + numero + "° conta foi efetuado com sucesso!" + Cores.TEXT_RESET + Cores.TEXT_CYAN_BRIGHT);
			System.out.println("                                                 ");
			System.out.println("*************************************************");
		} else {
			//Conta não foi encontrada - nula
			System.out.println(Cores.TEXT_CYAN_BOLD_BRIGHT + numero + "° conta não foi encontrada!" + Cores.TEXT_RESET + Cores.TEXT_CYAN_BRIGHT);
			System.out.println("                                                 ");
			System.out.println("*************************************************");
		}

	}

	@Override
	public void transferir(int numeroOrigem, int numeroDestino, float valor) {
		// TODO Auto-generated method stub
		//Localizando as contas de origem e destino dentro da collection
		var contaOrigem = buscarNaCollection(numeroOrigem);
		var contaDestino = buscarNaCollection(numeroDestino);

		//Se ambos os valores não são nulos
		if(contaOrigem != null && contaDestino != null) {

			//É possível realizar o método de saque
			if(contaOrigem.sacar(valor) == true) {
				contaDestino.depositar(valor); //A conta destino quem deposita o valor
				System.out.println(Cores.TEXT_YELLOW_BOLD_BRIGHT + "A transferência foi efetuada com sucesso!" + Cores.TEXT_RESET + Cores.TEXT_YELLOW_BRIGHT);
				System.out.println("                                                 ");
				System.out.println("*************************************************");
			}

		} else { //Conta não encontrada
			System.out.println(Cores.TEXT_YELLOW_BOLD_BRIGHT + "A transferência não pode ser efetuada!" + Cores.TEXT_RESET + Cores.TEXT_YELLOW_BRIGHT);
			System.out.println("                                                 ");
			System.out.println("*************************************************");
		}

	}

	//Cada conta criada é identificada por um número
	public int gerarNumero() {
		return ++ numero; //Aumenta um número sempre que uma conta é cadastrada
	}

	//Encontrar uma conta na coleção Array 
	public Conta buscarNaCollection(int numero) {
		for(var conta : listaContas) {
			if(conta.getNumeroConta() == numero) { //Para cada objeto conta, o método chama o método getNumeroConta()
				return conta; //Retorna a conta encontrada
			}
		}
		return null; //Retorna null se não encontrar a conta procurada
	}



}
