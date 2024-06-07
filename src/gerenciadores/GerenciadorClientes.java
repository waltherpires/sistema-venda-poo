package gerenciadores;

import classes.clientes.Cliente;
import classes.clientes.ClienteComum;
import classes.clientes.ClienteVIP;
import dados.dadosClientes.DadosClientes;
import exceptions.CPFJaCadastradoException;
import exceptions.ClienteJaCadastradoException;
import exceptions.ClienteNaoEncontradoException;
import exceptions.EscolhaInvalidaException;
import interfaces.Menu;
import interfaces.PesquisaCliente;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class GerenciadorClientes implements PesquisaCliente, Menu {
    List <Cliente> clientes;

    public GerenciadorClientes(){
        try {
            this.clientes = DadosClientes.carregarClientes();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            this.clientes = new ArrayList<>();
        }
    }

    public void menu(){
        Scanner prompt = new Scanner(System.in);
        boolean sair = false;

        while(!sair) {
            System.out.println("----------------------------------------CLIENTES----------------------------------------");
            System.out.println("1. Listar Clientes | 2. Cadastrar Cliente | 3. Remover Cliente | 4. Sair ");

            try {
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
                        throw new EscolhaInvalidaException("Opção Inválida! Escolha uma opção válida!");
                }
            }catch (InputMismatchException e) {
                System.out.println("Opção Inválida! Escolha uma opção válida!");
                prompt.nextLine();
            } catch (EscolhaInvalidaException e) {
                System.out.println(e.getMessage());
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
        boolean entradaValida = false;

        while(!entradaValida) {
            System.out.println("-------------------------------------CADASTRAR CLIENTES-------------------------------------");
            System.out.println("Escolha o tipo de Cliente: 1. Cliente Comum | 2. Cliente VIP | 3. Voltar");

            try {
                int tipoCliente = prompt.nextInt();
                prompt.nextLine();
                Cliente cliente;

                switch(tipoCliente){
                    case 1:
                        cliente = criarClienteComum(prompt);
                        break;

                    case 2:
                        cliente = criarClienteVIP(prompt);
                        break;

                    case 3:
                        return;

                    default:
                        throw new EscolhaInvalidaException("Opção Inválida! Escolha uma opção válida!");
                }

                if(cliente != null) {
                    clientes.add(cliente);
                    DadosClientes.salvarClientes(clientes);
                    System.out.println("Cliente cadastrado com sucesso: " + cliente.getNome());
                    entradaValida = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Opção Inválida! Escolha uma opção válida!");
                prompt.nextLine();
            } catch(ClienteJaCadastradoException | CPFJaCadastradoException | EscolhaInvalidaException | IOException e) {
                System.out.println(e.getMessage());
            }
        }


    }

    public Cliente criarClienteComum(Scanner prompt) throws ClienteJaCadastradoException, CPFJaCadastradoException {
        System.out.print("Nome Completo: ");
        String nomeCompletoComum = prompt.nextLine();
        verificarClienteCadastrado(nomeCompletoComum);

        System.out.print("CPF: ");
        String cpfComum = prompt.nextLine();
        verificarCPFCadastrado(cpfComum);

        return new ClienteComum(nomeCompletoComum, cpfComum);
    }

    public Cliente criarClienteVIP(Scanner prompt) throws ClienteJaCadastradoException, CPFJaCadastradoException {
        System.out.print("Nome: ");
        String nomeCompletoVIP = prompt.nextLine();
        verificarClienteCadastrado(nomeCompletoVIP);

        System.out.print("CPF: ");
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

        try {
            String nomeRemover = prompt.nextLine();
            Cliente cliente;
            cliente = pesquisarCliente(nomeRemover);

            if (cliente != null) {
                clientes.remove(cliente);
                DadosClientes.salvarClientes(clientes);
                System.out.println("Cliente: " + cliente.getNome() + " removido!");
            }
        } catch (ClienteNaoEncontradoException | IOException e) {
            System.out.println(e.getMessage());
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
