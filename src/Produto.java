public abstract class Produto {
    private String nome;
    private double preco;

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

    public boolean equals(Object o) {
        Produto a = (Produto) o;
        return this.nome.equals(a.getNome());
    }

    public int hashCode(){
        return this.nome.hashCode();
    }
}