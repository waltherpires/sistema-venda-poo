public class PessoaFisica extends Cliente {
    public PessoaFisica(String nome, String cpf) {
        super(nome, cpf);
    }

    public String toString() {
        return "Cliente PF{ " + "Nome: " + getNome()  + " |  CPF: " + getCpf() + '}';
    }
}
