import java.util.GregorianCalendar;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner prompt = new Scanner(System.in);

        GerenciadorVendas gerenciador = new GerenciadorVendas();

        System.out.println("Escolha uma categoria: 1. Produtos | 2. Clientes | 3. Vendas ");
        int opcaoInicio = prompt.nextInt();
        prompt.nextLine();
        if(opcaoInicio == 1) {
            System.out.println("Opção PRODUTOS selecionada!");
        } else if(opcaoInicio == 2) {
            System.out.println("Opção CLIENTES selecionada!");
        } else if(opcaoInicio == 3) {
            System.out.println("Opção VENDAS selecionada1");
        }
        do {
            switch(opcaoInicio) {
                case 1:
                    System.out.println("1. Listar Produtos | 2. Cadastrar Produto | 3. Remover produto ");
                    int opcaoProdutos = prompt.nextInt();
                    prompt.nextLine();
                    gerenciador.metodosProdutos(opcaoProdutos);
                    break;

                case 2:
                    break;

                case 3:

                    break;

                default:
                    System.out.println("Opcao invalida. Escolha uma opcao valida!");
                    opcaoInicio = prompt.nextInt();
                    break;
            }
        } while(opcaoInicio != 4);



    }
}