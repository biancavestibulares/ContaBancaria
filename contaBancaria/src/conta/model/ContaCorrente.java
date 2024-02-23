package conta.model;

public class ContaCorrente extends Conta{ //Herdando os atributos da classe Conta
	
	//Variável
	private float limite;
	
	//Construtor
	public ContaCorrente(String nomeTitular, int agenciaConta, int tipoConta, int numeroConta, float saldo, float limite) {
		super(nomeTitular, agenciaConta, tipoConta, numeroConta, saldo); //Construtor da classe-mãe (Conta)
		this.limite = limite;
	}

	//Getter e Setter da variável limite
	public float getLimite() {
		return limite;
	}

	public void setLimite(float limite) {
		this.limite = limite;
	}
	
	//Contas correntes tem limites para sacar dinheiro! Para isso, implementaremos um limite para bloquear saques de valor excessivo
	@Override //Para poder usar um método já existente na classe-mãe
	public boolean sacar(float valor) {
		if(this.getSaldo() + this.getLimite() < valor) {
			System.out.println("\nSaldo insuficiente!");
			return false;
		} else {
			this.setSaldo(this.getSaldo() - valor);
			System.out.println("\nValor sacado com sucesso!");
			return true;
		}	
	}
	
	@Override
	public void visualizar() {
		super.visualizar();
		System.out.println("Limite de crédito da conta: " + this.limite);
	}
	
}
