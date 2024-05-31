package gerenciadores;

import classes.clientes.Cliente;
import classes.clientes.ClienteComum;
import classes.clientes.ClienteVIP;
import exceptions.CPFJaCadastradoException;
import exceptions.ClienteJaCadastradoException;
import exceptions.ClienteNaoEncontradoException;
import interfaces.PesquisaCliente;

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
        System.out.println("Escolha o tipo de Cliente: 1. Cliente Comum | 2. Cliente VIP");
        int tipoCliente = prompt.nextInt();
        prompt.nextLine();

        try {
            Cliente cliente = null;
            switch(tipoCliente){
                case 1:
                    cliente = criarClienteComum(prompt);
                    break;

                case 2:
                    cliente = criarClienteVIP(prompt);
                    break;
                default:
                    System.out.println("Opção inválida! Escolha uma opção válida!");
                    return;
            }

            if(cliente != null) {
                clientes.add(cliente);
                System.out.println("Cliente cadastrado com sucesso: " + cliente.getNome());
            }
        } catch(ClienteJaCadastradoException | CPFJaCadastradoException e) {
            System.out.println(e.getMessage());
        }

    }

    public Cliente criarClienteComum(Scanner prompt) throws ClienteJaCadastradoException, CPFJaCadastradoException {
        System.out.println("Nome: ");
        String nomeComum = prompt.nextLine();
        System.out.println("Sobrenome: ");
        String sobrenomeComum = prompt.nextLine();
        String nomeCompletoComum = nomeComum + " " + sobrenomeComum;

        verificarClienteCadastrado(nomeCompletoComum);

        System.out.println("CPF: ");
        String cpfComum = prompt.nextLine();

        verificarCPFCadastrado(cpfComum);

        return new ClienteComum(nomeCompletoComum, cpfComum);
    }

    public Cliente criarClienteVIP(Scanner prompt) throws ClienteJaCadastradoException, CPFJaCadastradoException {
        System.out.println("Nome: ");
        String nomeVIP = prompt.nextLine();
        System.out.println("Sobrenome: ");
        String sobrenomeVIP = prompt.nextLine();
        String nomeCompletoVIP = nomeVIP + " " + sobrenomeVIP;

        verificarClienteCadastrado(nomeCompletoVIP);

        System.out.println("CPF: ");
        String cpfVIP = prompt.nextLine();

        verificarCPFCadastrado(cpfVIP);

        return new ClienteVIP(nomeCompletoVIP, cpfVIP);
    }

    public void verificarClienteCadastrado(String nome) throws ClienteJaCadastradoException {
        for(Cliente cliente : clientes) {
            if(cliente.getNome().equalsIgnoreCase(nome)){
                throw new ClienteJaCadastradoException("Cliente já cadastrado!");
            }
        }
    }

    private void verificarCPFCadastrado(String cpf) throws CPFJaCadastradoException {
        for(Cliente cliente : clientes){
            if(cliente.getCpf().equalsIgnoreCase(cpf)){
                throw new CPFJaCadastradoException("CPF já cadastrado!");
            }
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

    public Cliente pesquisarCliente(String nome) throws ClienteNaoEncontradoException {
        for(Cliente cliente : clientes) {
            if(cliente.getNome().equalsIgnoreCase(nome)) {
                return cliente;
            }
        }
        throw new ClienteNaoEncontradoException("Cliente não encontrado: " + nome);
    }
}