public abstract class Produto {
    private String nome;
    protected double preco;

    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome(){
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public abstract String toString();
}