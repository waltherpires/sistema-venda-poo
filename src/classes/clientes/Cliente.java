package classes.clientes;

import exceptions.EscolhaInvalidaException;

public abstract class Cliente {
    private String nome;
    private String cpf;

    public Cliente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String toString(){
        return "Nome: " + getNome() + " cpf: " + getCpf();
    };

    public abstract String enviarDados();

    public static Cliente receberDados(String dados) throws EscolhaInvalidaException {
        String[] partes = dados.split(",");
        String tipo = partes[0];
        if("Comum".equals(tipo)) {
            return new ClienteComum(partes[1], partes[2]);
        } else if("VIP".equals(tipo)) {
            return new ClienteVIP(partes[1], partes[2]);
        }
        throw new EscolhaInvalidaException("Dados inválidos");
    }
}
