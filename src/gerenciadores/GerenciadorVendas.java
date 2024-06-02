package gerenciadores;

import classes.clientes.Cliente;
import classes.produtos.Produto;
import classes.vendas.Venda;
import classes.vendas.VendaCredito;
import classes.vendas.VendaDebito;
import exceptions.*;
import interfaces.Menu;

import java.util.*;

public class GerenciadorVendas implements Menu {
    List<Venda> vendas = new LinkedList<>();
    private GerenciadorProdutos gerenciadorProdutos;
    private GerenciadorClientes gerenciadorClientes;

    public GerenciadorVendas(GerenciadorProdutos gerenciadorProdutos, GerenciadorClientes gerenciadorClientes) {
        this.gerenciadorProdutos = gerenciadorProdutos;
        this.gerenciadorClientes = gerenciadorClientes;
    }

    public void menu() {
        Scanner prompt = new Scanner(System.in);
        boolean sair = false;

        while (!sair) {
            System.out.println("----------------------------------------VENDAS----------------------------------------");
            System.out.println("1. Listar Vendas | 2. Adicionar Venda | 3. Voltar (Menu Principal)");

            try {
                int escolha = prompt.nextInt();
                prompt.nextLine();

                switch (escolha) {
                    case 1:
                        listarVendas();
                        break;
                    case 2:
                        if(gerenciadorProdutos.produtos.isEmpty() || gerenciadorClientes.clientes.isEmpty()){
                            throw new ListaVaziaException("Erro ao Adicionar Venda: Sem clientes ou produtos cadastrados");
                        }
                        criarVenda();
                        break;
                    case 3:
                        sair = true;
                        break;
                    default:
                        throw new EscolhaInvalidaException("Opção Inválida! Escolha uma opção válida!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Opção Inválida! Escolha uma opção válida!");
                prompt.nextLine();
            } catch(EscolhaInvalidaException | ListaVaziaException e) {
                System.out.println(e.getMessage());
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

        try {
            System.out.println("Pagamento: 1. Débito | 2. Crédito");
            int tipoVenda = prompt.nextInt();
            prompt.nextLine();

            if(tipoVenda > 2) {
                throw new VendaInvalidaException("Tipo de venda inválido");
            }

            System.out.println("Nome do Cliente: ");
            String nomeCliente = prompt.nextLine();

            System.out.println("Nome do Produto: ");
            String nomeProduto = prompt.nextLine();

            long currentTimeInMillis = System.currentTimeMillis();
            Date dataVenda = new Date(currentTimeInMillis);


            Produto produto = gerenciadorProdutos.pesquisarProduto(nomeProduto);
            Cliente cliente = gerenciadorClientes.pesquisarCliente(nomeCliente);
            Venda venda;

            switch(tipoVenda) {
                case 1:
                    venda = new VendaDebito(cliente, produto, dataVenda);
                    break;
                case 2:
                    venda = new VendaCredito(cliente, produto, dataVenda);
                    break;
                default:
                    throw new VendaInvalidaException("Tipo de venda inválido");
            }

            registrarVenda(venda);
            System.out.println("Venda Realizada com sucesso!");

        } catch (InputMismatchException e) {
            System.out.println("Opção Inválida! Escolha uma opção válida!");
            prompt.nextLine();
        } catch (ProdutoNaoEncontradoException | ClienteNaoEncontradoException | VendaInvalidaException e) {
            System.out.println("Erro ao realizar a venda { " + e.getMessage() + " }");
        }
    }

    public void listarVendas() {
        for (Venda venda : vendas) {
            System.out.println(venda);
        }
    }
}
