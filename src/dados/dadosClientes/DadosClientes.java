package dados.dadosClientes;

import classes.clientes.Cliente;
import exceptions.EscolhaInvalidaException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DadosClientes {

    public static void salvarClientes(List<Cliente> clientes) throws IOException {
        BufferedWriter arquivo = null;
        try {
            FileWriter caminho = new FileWriter("src/dados/dadosClientes/clientes.txt");
            arquivo = new BufferedWriter(caminho);
            for (Cliente cliente : clientes) {
                arquivo.write(cliente.enviarDados());
                arquivo.newLine();
            }
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if(arquivo != null) {
                arquivo.close();
            }
        }
    }

    public static List<Cliente> carregarClientes() throws IOException{
        List<Cliente> clientes = new ArrayList<>();
        BufferedReader leitorArquivo = null;
        try {
            FileReader leitorCaminho = new FileReader("src/dados/dadosClientes/clientes.txt");
            leitorArquivo = new BufferedReader(leitorCaminho);
            String linha = leitorArquivo.readLine();
            while (linha != null) {
                clientes.add(Cliente.receberDados(linha));
                linha = leitorArquivo.readLine();
            }
        } catch (IOException | EscolhaInvalidaException e) {
            System.out.println(e.getMessage());
        } finally {
            if (leitorArquivo != null) {
                leitorArquivo.close();
            }
        }
        return clientes;
    }
}
