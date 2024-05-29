import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GerenciadorClientes implements PesquisaCliente {
    private List <Cliente> clientes = new ArrayList<>();

    public void menuClientes(){
        Scanner prompt = new Scanner(System.in);
        boolean sair = false;

        while(!sair) {
            System.out.println("1. Listar Clientes | 2. Cadastrar Cliente | 3. Remover Cliente | 4. Sair ");
            int escolha = prompt.nextInt();
            prompt.nextLine();
            switch(escolha){
                case 1:
                    listarClientes();
                    break;
                case 2:
                    cadastrarCliente();
                    break;
                case 3:
                    removerCliente();
                    break;
                case 4:
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida! Escolha uma opção válida");
            }
        }
    }

    public void listarClientes() {
        if(clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado!");
        } else {
            for(Cliente cliente : clientes) {
                System.out.println(cliente);
            }
        }
    }

    public void cadastrarCliente(){
        Scanner prompt = new Scanner(System.in);
        System.out.println("Escolha o tipo de Cliente: 1. Pessoa Física | 2. Cliente VIP");
        int tipoCliente = prompt.nextInt();
        prompt.nextLine();

        Cliente cliente = null;
        switch(tipoCliente){
            case 1:
                System.out.println("Nome: ");
                String nomePF = prompt.nextLine();
                System.out.println("Sobrenome: ");
                String sobrenomePF = prompt.nextLine();
                String nomeCompletoPF = nomePF + " " + sobrenomePF;

                if(clienteCadastrado(nomeCompletoPF)) {
                    System.out.println("Cliente já cadastrado!");
                    break;
                }

                System.out.println("CPF: ");
                String cpfPF = prompt.nextLine();

                if(cpfCadastrado(cpfPF)){
                    System.out.println("CPF já cadastrado!");
                    break;
                }

                cliente = new PessoaFisica(nomeCompletoPF, cpfPF);
                break;

            case 2:
                System.out.println("Nome: ");
                String nomeVIP = prompt.nextLine();
                System.out.println("Sobrenome: ");
                String sobrenomeVIP = prompt.nextLine();
                String nomeCompletoVIP = nomeVIP + " " + sobrenomeVIP;

                if (clienteCadastrado(nomeCompletoVIP)) {
                    System.out.println("Cliente VIP já cadastrado!");
                    break;
                }

                System.out.println("CPF: ");
                String cpfVIP = prompt.nextLine();

                if(cpfCadastrado(cpfVIP)){
                    System.out.println("CPF já cadastrado!");
                    break;
                }

                cliente = new ClienteVIP(nomeCompletoVIP, cpfVIP);
                break;
            default:
                System.out.println("Opção inválida! Escolha uma opção válida!");
                break;
        }

        if(cliente != null) {
            clientes.add(cliente);
            System.out.println("CLiente cadastrado com sucesso: " + cliente.getNome());
        }
    }

    private boolean clienteCadastrado(String nome){
        for(Cliente cliente : clientes) {
            if(cliente.getNome().equalsIgnoreCase(nome)){
                return true;
            }
        }
        return false;
    }

    private boolean cpfCadastrado(String cpf){
        for(Cliente cliente : clientes){
            if(cliente.getCpf().equalsIgnoreCase(cpf)){
                return true;
            }
        }
        return false;
    }

    public void removerCliente() {
        Scanner prompt = new Scanner(System.in);
        System.out.println("Digite o NOME COMPLETO do cliente que deseja remover: ");
        String nomeRemover = prompt.nextLine();
        Cliente cliente = null;
        for(Cliente clienteRemover : clientes) {
            if(clienteRemover.getNome().equalsIgnoreCase(nomeRemover)){
                cliente = clienteRemover;
            }
        }
        if (cliente != null) {
            clientes.remove(cliente);
            System.out.println("Cliente: " + cliente.getNome() + " removido!");
        } else {
            System.out.println("Não há um cliente com esse nome");
        }
    }

    public Cliente pesquisarCliente(String nome) throws ClienteNaoEncontradoException{
        for(Cliente cliente : clientes) {
            if(cliente.getNome().equalsIgnoreCase(nome)) {
                return cliente;
            }
        }
        throw new ClienteNaoEncontradoException("Cliente não encontrado: " + nome);
    }
}
