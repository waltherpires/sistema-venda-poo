import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class GerenciadorClientes {
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
                for(Cliente aux : clientes){
                    if(aux.getNome().equalsIgnoreCase(nomeCompletoPF)){
                        System.out.println("Cliente já cadastrado!");
                    }
                    else {
                        System.out.println("CPF: ");
                        String cpfPF = prompt.nextLine();
                        for(Cliente auxCpf : clientes){
                            if(auxCpf.getCpf().equalsIgnoreCase(cpfPF)){
                                System.out.println("CPF já cadastrado!");
                            }
                            else {
                                cliente = new PessoaFisica(nomeCompletoPF, cpfPF);
                                clientes.add(cliente);
                            }
                        }
                    }
                }
                break;
            case 2:
                System.out.println("Nome: ");
                String nomeVIP = prompt.nextLine();
                System.out.println("Sobrenome: ");
                String sobrenomeVIP = prompt.nextLine();
                String nomeCompletoVIP = nomeVIP + " " + sobrenomeVIP;
                for(Cliente aux : clientes){
                    if(aux.getNome().equalsIgnoreCase(nomeCompletoVIP)){
                        System.out.println("Cliente já cadastrado!");
                    }
                    else {
                        System.out.println("CPF: ");
                        String cpfVIP = prompt.nextLine();
                        for(Cliente auxCpfVIP : clientes){
                            if(auxCpfVIP.getCpf().equalsIgnoreCase(cpfVIP)){
                                System.out.println("CPF já cadastrado!");
                            }
                            else {
                                cliente = new PessoaFisica(nomeCompletoVIP, cpfVIP);
                                clientes.add(cliente);
                            }
                        }
                    }
                }
                break;
            default:
                System.out.println("Opção inválida! Escolha uma opção válida!");
        }
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
}
