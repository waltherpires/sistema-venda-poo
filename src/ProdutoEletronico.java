public class ProdutoEletronico extends Produto {
    public ProdutoEletronico(String nome, double preco) {
        super(nome, preco);
    }

    public String toString(){
        return "Eletronico: " + getNome()  + " | Preco: " + getPreco();
    }

}
