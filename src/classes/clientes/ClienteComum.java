package classes.clientes;

public class ClienteComum extends Cliente {
    public ClienteComum(String nome, String cpf) {
        super(nome, cpf);
    }

    public String toString() {
        return "Cliente Comum { " + "Nome: " + getNome()  + " |  CPF: " + getCpf() + " }";
    }

    public String enviarDados() {
        return "VIP," + getNome() + "," + getCpf();
    }
}
