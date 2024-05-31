public class ProdutoRoupa extends Produto {
    public ProdutoRoupa(String nome, double preco) {
        super(nome, preco);
    }

    public String toString(){
        return "Roupa: " + getNome() +  " | Preco: " + getPreco();
    }

}
