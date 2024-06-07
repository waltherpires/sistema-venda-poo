package classes.clientes;

public class ClienteVIP extends Cliente {
    public ClienteVIP(String nome, String cpf) {
        super(nome, cpf);
    }

    public String toString()  {
        return "Cliente VIP { " + "Nome: " + getNome()  + " |  CPF: " + getCpf() + " }";
    }

    public String enviarDados() {
        return "VIP," + getNome() + "," + getCpf() + ",";
    }

}