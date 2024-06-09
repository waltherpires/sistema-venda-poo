package interfaces;

import classes.clientes.Cliente;
import exceptions.ClienteNaoEncontradoException;

public interface PesquisaCliente {
     Cliente pesquisarCliente(String nome) throws ClienteNaoEncontradoException;
}
