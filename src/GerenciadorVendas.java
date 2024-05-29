import java.util.*;

public class GerenciadorVendas {
    List<Venda> vendas = new LinkedList<>();
    private GerenciadorProdutos gerenciadorProdutos;

    public void menuVenda(){

    }

    public void registrarVenda(Venda venda) throws VendaInvalidaException {
        if (venda.getProduto() == null || venda.getCliente() == null) {
            throw new VendaInvalidaException("Venda invalida: " + venda);
        }
        vendas.add(venda);
    }

    public void criarVenda(int tipoVenda, String nomeProduto, String nomeCliente, Date dataVenda) {
        try {
            Produto produto = gerenciadorProdutos.pesquisarProduto(nomeProduto);
            Cliente cliente = gerenciadorClientes.pesquisarCliente(nomeCliente);
            Venda venda;
            if (tipoVenda == 1) {
                venda = new VendaCredito(cliente, produto, dataVenda);
            } else if (tipoVenda == 2) {
                venda = new VendaDebito(cliente, produto, dataVenda);
            } else {
                venda = new VendaCredito(cliente, produto, dataVenda);
            }
            registrarVenda(venda);
            System.out.println("Venda Realizada com sucesso!");
        } catch (ProdutoNaoEncontradoException e) {
            System.out.println("Erro ao realizar venda (Produto): " + e.getMessage());
        } catch (VendaInvalidaException i) {
            System.out.println("Erro ao realizar venda (Venda): " + i.getMessage());
        }
    }

    public void listarVendas() {
        for (Venda venda : vendas) {
            System.out.println(venda);
        }
    }
}
