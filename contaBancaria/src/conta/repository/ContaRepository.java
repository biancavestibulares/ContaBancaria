package conta.repository;

import conta.model.Conta;

public interface ContaRepository {
	
	//Criando os métodos CRUD (Create, Read, Update e Delete)
	//Criamos os métodos na interface, para os tornarmos uma parte obrigatória do código
	//O Menu tem opções que devem ser seguidas à risco no programa, então implementaremos os métodos referentes a cada opção dentro de interface
	public void cadastrar(Conta conta); //Criar conta
	public void listarTodas(); //Listar contas
	public void procurarPorNumero(int numero); //Buscar conta pelo seu número
	public void atualizar(Conta conta); //Atualizar dados da conta
	public void deletar(int numero); //Deletar conta
	
	//Métodos Bancários
	public void sacar(int numero, float valor);
	public void depositar(int numero, float valor);
	public void transferir(int numeroRemetente, int numeroDestinatario, float valor); //Tranferência de valor entre as contas
	
}
