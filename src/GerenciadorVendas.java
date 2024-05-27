import java.util.*;

public class GerenciadorVendas implements Pesquisa {
    List<Produto> produtos = new ArrayList<>();

    public void adicionarProduto(Produto produto){
        produtos.add(produto);
    }

    public Produto pesquisarProduto(String nome) throws ProdutoNaoEncontradoException {
        for (Produto aux:produtos){
            if(aux.getNome().equalsIgnoreCase(nome)) {
                return aux;
            }
        }
        throw new ProdutoNaoEncontradoException("Produto não encontrado: " + nome);
    }
}