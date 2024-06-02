import exceptions.EscolhaInvalidaException;
import gerenciadores.GerenciadorClientes;
import gerenciadores.GerenciadorProdutos;
import gerenciadores.GerenciadorVendas;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner prompt = new Scanner(System.in);

        GerenciadorProdutos gerenciadorProdutos = new GerenciadorProdutos();
        GerenciadorClientes gerenciadorClientes = new GerenciadorClientes();
        GerenciadorVendas gerenciadorVendas = new GerenciadorVendas(gerenciadorProdutos, gerenciadorClientes);
        boolean gerenciadorLigado = true;

        while (gerenciadorLigado) {
            System.out.println("----------------------------------------SISTEMA----------------------------------------");
            System.out.println("Escolha uma categoria: 1. Produtos | 2. Clientes | 3. Vendas | 4. Fechar Sistema ");

            try {
                int opcaoInicio = prompt.nextInt();
                prompt.nextLine();

                switch (opcaoInicio) {
                    case 1:
                        gerenciadorProdutos.menu();
                        break;
                    case 2:
                        gerenciadorClientes.menu();
                        break;
                    case 3:
                        gerenciadorVendas.menu();
                        break;
                    case 4:
                        gerenciadorLigado = false;
                        break;
                    default:
                        throw new EscolhaInvalidaException("Opção Inválida! Escolha uma opção válida!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Opção Inválida! Escolha uma opção válida!");
                prompt.nextLine();
            }
            catch(EscolhaInvalidaException e) {
                System.out.println(e.getMessage());
            }

        }
        System.out.println("Sistema desligado.");
    }
}
