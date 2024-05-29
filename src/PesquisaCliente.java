public interface PesquisaCliente {
    Cliente pesquisarCliente(String nome) throws ClienteNaoEncontradoException;
}
