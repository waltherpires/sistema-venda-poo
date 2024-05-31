package interfaces;

import classes.produtos.Produto;
import exceptions.ProdutoNaoEncontradoException;

public interface PesquisaProduto {
    Produto pesquisarProduto(String nome) throws ProdutoNaoEncontradoException;
}
