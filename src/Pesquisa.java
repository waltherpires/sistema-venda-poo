public interface Pesquisa {
    Produto pesquisarProduto(String nome) throws ProdutoNaoEncontradoException;
}
