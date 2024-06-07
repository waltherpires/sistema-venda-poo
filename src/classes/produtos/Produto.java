package classes.produtos;

import exceptions.EscolhaInvalidaException;

public abstract class Produto {
    private String nome;
    private double preco;

    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public static Produto receberDados(String dados) throws EscolhaInvalidaException{
        String[] partes = dados.split(",");
        String tipo = partes[0];

        if("Eletronico".equals(tipo)) {
            return new ProdutoEletronico(partes[1], Double.parseDouble(partes[2]));
        } else if("Roupa".equals(tipo)) {
            return new ProdutoRoupa(partes[1], Double.parseDouble(partes[2]));
        }
        throw new EscolhaInvalidaException("Dados inválidos");
    }

    public abstract String enviarDados();

    public String getNome(){
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public abstract String toString();
}