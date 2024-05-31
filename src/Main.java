import gerenciadores.GerenciadorClientes;
import gerenciadores.GerenciadorProdutos;
import gerenciadores.GerenciadorVendas;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner prompt = new Scanner(System.in);

        GerenciadorProdutos gerenciadorProdutos = new GerenciadorProdutos();
        GerenciadorClientes gerenciadorClientes = new GerenciadorClientes();
        GerenciadorVendas gerenciadorVendas = new GerenciadorVendas();
        boolean gerenciadorLigado = true;

        while (gerenciadorLigado) {
            System.out.println("Escolha uma categoria: 1. Produtos | 2. Clientes | 3. Vendas | 4. Sair ");
            int opcaoInicio = prompt.nextInt();
            prompt.nextLine();

            switch (opcaoInicio) {
                case 1:
                    gerenciadorProdutos.menuProdutos();
                    break;
                case 2:
                    gerenciadorClientes.menuClientes();
                    break;
                case 3:
                    gerenciadorVendas.menuVenda();
                    break;
                case 4:
                    gerenciadorLigado = false;
                    break;
                default:
                    System.out.println("Opcao invalida. Escolha uma opcao valida!");
            }
        }
        System.out.println("Sistema desligado.");
    }
}
