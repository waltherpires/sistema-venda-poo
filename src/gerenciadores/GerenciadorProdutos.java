package gerenciadores;

import classes.produtos.Produto;
import classes.produtos.ProdutoEletronico;
import classes.produtos.ProdutoRoupa;
import exceptions.ProdutoNaoEncontradoException;
import interfaces.PesquisaProduto;

import java.util.*;

public class GerenciadorProdutos implements PesquisaProduto {
    private Set<Produto> produtos = new HashSet<>();

    public void menuProdutos() {
        Scanner prompt = new Scanner(System.in);
        boolean sair = false;

        while(!sair) {
            System.out.println("1. Listar Produtos | 2. Cadastrar Produto | 3. Remover produto | 4. Voltar ");
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
                    System.out.println("Escolha inválida! Faça uma escolha válida");
            }
        }
    }

    public void cadastrarProduto() {
        System.out.println("Escolha o tipo de produto: 1. Eletrônico | 2. Roupas | 3. Sair");
        Scanner prompt = new Scanner(System.in);
        int escolhaProduto = prompt.nextInt();
        prompt.nextLine();

        Produto produto = null;
        switch(escolhaProduto) {
            case 1:
                System.out.println("Nome do Produto: ");
                String nomeEletronico = prompt.nextLine();
                boolean eletronicoJaCadastrado = false;
                for(Produto produtoEletronico : produtos) {
                    if (produtoEletronico.getNome().equalsIgnoreCase(nomeEletronico)) {
                        eletronicoJaCadastrado = true;
                        System.out.println("Produto já cadastrado!");
                        break;
                    }
                }
                    if(!eletronicoJaCadastrado) {
                        System.out.println("Preço do Produto: ");
                        double valorEletronico = prompt.nextDouble();
                        prompt.nextLine();
                        produto = new ProdutoEletronico(nomeEletronico, valorEletronico);
                    }
                    break;

            case 2:
                System.out.println("Nome do Produto: ");
                String nomeRoupa = prompt.nextLine();
                boolean roupaJaCadastrada = false;
                for(Produto roupa : produtos) {
                    if (roupa.getNome().equalsIgnoreCase(nomeRoupa)) {
                        roupaJaCadastrada = true;
                        System.out.println("Produto já cadastrado!");
                        break;
                    }
                }
                if(!roupaJaCadastrada){
                        System.out.println("Preço do Produto: ");
                        double valorRoupa = prompt.nextDouble();
                        prompt.nextLine();
                        produto = new ProdutoRoupa(nomeRoupa, valorRoupa);
                }
                break;
            case 3:
                return;
            default:
                System.out.println("Opção inválida! Escolha uma opção válida!");
                return;
        }

        if(produto != null) {
            produtos.add(produto);
            System.out.println("classes.produtos.Produto: " + produto.getNome() + " foi cadastrado com sucesso!");
        }
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
            System.out.println("classes.produtos.Produto: " + produtoRemover.getNome() + " foi removido!");
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
        throw new ProdutoNaoEncontradoException("classes.produtos.Produto não encontrado: " + nome);
    }
}
