package gerenciadores;

import classes.clientes.Cliente;
import classes.produtos.Produto;
import classes.vendas.Venda;
import classes.vendas.VendaCredito;
import classes.vendas.VendaDebito;
import exceptions.ClienteNaoEncontradoException;
import exceptions.ProdutoNaoEncontradoException;
import exceptions.VendaInvalidaException;

import java.util.*;

public class GerenciadorVendas {
    List<Venda> vendas = new LinkedList<>();
    private GerenciadorProdutos gerenciadorProdutos;
    private GerenciadorClientes gerenciadorClientes;

    public void menuVenda() {
        Scanner prompt = new Scanner(System.in);
        boolean sair = false;

        while (!sair) {
            System.out.println("1. Listar Vendas | 2. Adicionar Venda | 3. Sair");
            int escolha = prompt.nextInt();
            prompt.nextLine();

            switch (escolha) {
                case 1:
                    listarVendas();
                    break;
                case 2:
                    criarVenda();
                    break;
                case 3:
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida. Escolha uma opção válida!");
            }
        }
    }

    public void registrarVenda(Venda venda) throws VendaInvalidaException {
        if (venda.getProduto() == null || venda.getCliente() == null) {
            throw new VendaInvalidaException("Venda invalida: " + venda);
        }
        vendas.add(venda);
    }

    public void criarVenda() {
        Scanner prompt = new Scanner(System.in);

        System.out.println("Pagamento: 1. Débito | 2. Crédito");
        int tipoVenda = prompt.nextInt();
        prompt.nextLine();

        System.out.println("Nome do Cliente: ");
        String nomeCliente = prompt.nextLine();

        System.out.println("Nome do Produto: ");
        String nomeProduto = prompt.nextLine();

        long currentTimeInMillis = System.currentTimeMillis();
        Date dataVenda = new Date(currentTimeInMillis);

        try {
            Produto produto = gerenciadorProdutos.pesquisarProduto(nomeProduto);
            Cliente cliente = gerenciadorClientes.pesquisarCliente(nomeCliente);
            Venda venda;
            if (tipoVenda == 1) {
                venda = new VendaDebito(cliente, produto, dataVenda);
            } else if (tipoVenda == 2) {
                venda = new VendaCredito(cliente, produto, dataVenda);
            } else {
                throw new IllegalArgumentException("Tipo de venda inválido");
            }
            registrarVenda(venda);
            System.out.println("Venda Realizada com sucesso!");
        } catch (ProdutoNaoEncontradoException e) {
            System.out.println("Erro ao realizar venda (Produto): " + e.getMessage());
        } catch (VendaInvalidaException i) {
            System.out.println("Erro ao realizar venda (Venda): " + i.getMessage());
        } catch (ClienteNaoEncontradoException c) {
            System.out.println("Erro ao realizar venda (Cliente): " + c.getMessage());
        }
    }

    public void listarVendas() {
        for (Venda venda : vendas) {
            System.out.println(venda);
        }
    }
}
