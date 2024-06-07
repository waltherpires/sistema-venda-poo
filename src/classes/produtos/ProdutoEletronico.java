package classes.produtos;

public class ProdutoEletronico extends Produto {
    public ProdutoEletronico(String nome, double preco) {
        super(nome, preco);
    }

    public String enviarDados() {
        return "Eletronico," + getNome() + "," + getPreco();
    }

    public String toString(){
        return "Eletronico: " + getNome()  + " | Preco: " + getPreco();
    }

}
