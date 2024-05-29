public interface PesquisaProduto {
    Produto pesquisarProduto(String nome) throws ProdutoNaoEncontradoException;
}
