import java.util.*;

public class GerenciadorVendas implements Pesquisa {
    Set<Produto> produtos = new HashSet<>();
    List<Venda> vendas = new LinkedList<>();

    public void adicionarProduto(Produto produto){
        produtos.add(produto);
    }

    public boolean removerProduto(String nome){
        for (Produto produto : produtos) {
            if (produto.getNome().equalsIgnoreCase(nome)){
                System.out.println("Produto " + produto + " removido");
                return produtos.remove(produto);
            }
        }
        return false;
    }

    public void listarProdutos() {
        for (Produto produto : produtos) {
            System.out.println("Produto: " + produto.getNome() + " | Preço: " + produto.getPreco());
        }
    }

    public void metodosProdutos(int escolha) {
        Scanner prompt = new Scanner(System.in);
        if(escolha == 1) {
            System.out.println("Listar produtos selecionado!");
        } else if(escolha == 2){
            System.out.println("Cadastrar produto selecionado!");
        } else if(escolha == 3){
            System.out.println("Remover Produto Selecionado!");
        }
        do {
            switch (escolha) {
                case 1:
                    listarProdutos();
                    break;
                case 2:
                    Produto produto  = null;
                    System.out.println("Escolha o tipo de produto: 1. Eletronico | 2. Medicamento Veterinario | 3. Sair");
                    int escolhaProduto = prompt.nextInt();
                    prompt.nextLine();
                    do{
                        switch(escolhaProduto) {
                            case 1:
                                System.out.println("Nome do produto: ");
                                String nomeEletronico = prompt.nextLine();
                                System.out.println("Preço do produto: ");
                                double precoEletronico = prompt.nextDouble();
                                produto = new ProdutoEletronico(nomeEletronico, precoEletronico);
                                System.out.println("Eletronico: " + produto.getNome() + " criado!");
                                break;

                            case 2:
                                System.out.println("Nome do produto: ");
                                String nomeMedicamento = prompt.nextLine();
                                System.out.println("Preço do produto: ");
                                double precoMedicamento = prompt.nextDouble();
                                produto = new MedicamentoVeterinario(nomeMedicamento, precoMedicamento);
                                System.out.println("Medicamento: " + produto.getNome() + " criado!");
                                break;

                            default:
                                System.out.println("Escolha invalida. Faça uma escolha valida!");
                                escolhaProduto = prompt.nextInt();
                                prompt.nextLine();
                                break;
                        }
                        adicionarProduto(produto);
                        System.out.println("(ADICIONAR PRODUTO) Deseja sair? 3 - Sim | Outro numero - Não");
                        escolhaProduto = prompt.nextInt();
                        prompt.nextLine();
                    } while (escolhaProduto != 3);

                    break;

                case 3:
                    System.out.println("Digite o nome do produto a ser removido: ");
                    String produtoExcluido = prompt.nextLine();
                    try {
                        if(pesquisarProduto(produtoExcluido) != null) {
                          removerProduto(produtoExcluido);
                        }
                    } catch (ProdutoNaoEncontradoException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                default:
                    System.out.println("Opção Invalida! Tente novamente");
                    escolha = prompt.nextInt();
                    prompt.nextLine();
                    break;
            }
            System.out.println("(PRODUTOS) Deseja sair? 4 - Sim | Outro numero - Nao");
            escolha = prompt.nextInt();
            prompt.nextLine();
        } while(escolha != 4);
    }

    public Produto pesquisarProduto(String nome) throws ProdutoNaoEncontradoException {
        for (Produto produto : produtos){
            if(produto.getNome().equalsIgnoreCase(nome)) {
                return produto;
            }
        }
        throw new ProdutoNaoEncontradoException("Produto não encontrado: " + nome);
    }

    public void registrarVenda(Venda venda) throws VendaInvalidaException {
        if(venda.getProduto() == null || venda.getCliente() == null) {
            throw new VendaInvalidaException("Venda invalida: " + venda);
        }
        vendas.add(venda);
    }

    public void criarVenda(int tipoVenda,String nomeProduto, Cliente cliente, Date dataVenda) {
        try {
            Produto produto = pesquisarProduto(nomeProduto);
            Venda venda;
            if(tipoVenda == 1) {
                venda = new VendaCredito(cliente, produto, dataVenda);
            }
            else if(tipoVenda == 2) {
                venda = new VendaDebito(cliente, produto, dataVenda);
            } else {
                venda = new VendaCredito(cliente, produto, dataVenda);
            }
            registrarVenda(venda);
            System.out.println("Venda Realizada com sucesso!");
        } catch(ProdutoNaoEncontradoException e) {
            System.out.println("Erro ao realizar venda (Produto): " + e.getMessage());
        } catch(VendaInvalidaException i) {
            System.out.println("Erro ao realizar venda (Venda): " + i.getMessage());
        }
    }

    public void listarVendas(){
        for (Venda venda : vendas) {
            System.out.println(venda);
        }
    }
}