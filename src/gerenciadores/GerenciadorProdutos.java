package gerenciadores;

import classes.produtos.Produto;
import classes.produtos.ProdutoEletronico;
import classes.produtos.ProdutoRoupa;
import exceptions.EscolhaInvalidaException;
import exceptions.ProdutoJaCadastradoException;
import exceptions.ProdutoNaoEncontradoException;
import interfaces.Menu;
import interfaces.PesquisaProduto;

import java.util.*;

public class GerenciadorProdutos implements PesquisaProduto, Menu {
    private Set<Produto> produtos = new HashSet<>();

    public void menu() {
        Scanner prompt = new Scanner(System.in);
        boolean sair = false;

        while(!sair) {
            System.out.println("----------------------------------------PRODUTOS----------------------------------------");
            System.out.println("1. Listar Produtos | 2. Cadastrar Produto | 3. Remover produto | 4. Voltar (Menu Principal)");

            try {
                int escolha = prompt.nextInt();
                prompt.nextLine();

                switch(escolha) {
                    case 1:
                        listarProdutos();
                        break;
                    case 2:
                        cadastrarProduto();
                        break;
                    case 3:
                        removerProduto();
                        break;
                    case 4:
                        sair = true;
                        break;
                    default:
                        throw new EscolhaInvalidaException("Opção Inválida! Escolha uma opção válida!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Opção Inválida! Escolha uma opção válida!");
                prompt.nextLine();
            } catch (EscolhaInvalidaException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void cadastrarProduto() {
        System.out.println("Escolha o tipo de produto: 1. Eletrônico | 2. Roupas | 3. Voltar (Menu Produtos)");
        Scanner prompt = new Scanner(System.in);
        int escolhaProduto = prompt.nextInt();
        prompt.nextLine();

        Produto produto;

        try {
            switch(escolhaProduto) {
                case 1:
                    produto = criarProdutoEletronico(prompt);
                    break;

                case 2:
                    produto = criarProdutoRoupa(prompt);
                    break;

                case 3:
                    return;
                default:
                    throw new EscolhaInvalidaException("Opção Inválida! Escolha uma opção válida!");

            }

            produtos.add(produto);
            System.out.println("Produto: " + produto.getNome() + " foi cadastrado com sucesso!");

        }  catch (InputMismatchException e) {
            System.out.println("Opção Inválida! Escolha uma opção válida!");
            prompt.nextLine();
        } catch(ProdutoJaCadastradoException | EscolhaInvalidaException e) {
            System.out.println(e.getMessage());
        }
    }

    public void verificarProdutoCadastrado(String nome) throws ProdutoJaCadastradoException {
        for (Produto produto : produtos) {
            if(produto.getNome().equalsIgnoreCase(nome)) {
                throw new ProdutoJaCadastradoException("Produto já cadastrado: " + nome + "!");
            }
        }
    }

    public Produto criarProdutoEletronico(Scanner prompt) throws ProdutoJaCadastradoException {
        System.out.println("Nome do Produto: ");
        String nomeEletronico = prompt.nextLine();
        verificarProdutoCadastrado(nomeEletronico);

        System.out.println("Preço do Produto: ");
        double valorEletronico = prompt.nextDouble();
        prompt.nextLine();

        return new ProdutoEletronico(nomeEletronico, valorEletronico);
    }

    public Produto criarProdutoRoupa(Scanner prompt) throws ProdutoJaCadastradoException {
        System.out.println("Nome do Produto: ");
        String nomeRoupa = prompt.nextLine();
        verificarProdutoCadastrado(nomeRoupa);

        System.out.println("Preço do Produto: ");
        double valorRoupa = prompt.nextDouble();
        prompt.nextLine();

        return new ProdutoRoupa(nomeRoupa, valorRoupa);
    }

    public void listarProdutos(){
        if(produtos.isEmpty()){
            System.out.println("Nenhum produto cadastrado!");
        }
        else {
            for(Produto produtoLista : produtos) {
                System.out.println(produtoLista);
            }
        }
    }

    public void removerProduto(){
        Scanner prompt = new Scanner(System.in);
        System.out.println("Digite o nome do produto a ser removido:");
        String nome = prompt.nextLine();;
        Produto produtoRemover = null;
        for(Produto produto : produtos){
            if(produto.getNome().equalsIgnoreCase(nome)){
                produtoRemover = produto;
            }
        }

        if(produtoRemover != null){
            produtos.remove(produtoRemover);
            System.out.println("Produto: " + produtoRemover.getNome() + " foi removido!");
        } else {
            System.out.println("Não foi possível remover o produto escolhido!");
        }
    }

    public Produto pesquisarProduto(String nome) throws ProdutoNaoEncontradoException {
        for(Produto produto : produtos) {
            if(produto.getNome().equalsIgnoreCase(nome)) {
                return produto;
            }
        }
        throw new ProdutoNaoEncontradoException("Produto não encontrado: " + nome);
    }
}
