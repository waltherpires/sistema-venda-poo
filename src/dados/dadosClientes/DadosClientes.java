package dados.dadosClientes;

import classes.clientes.Cliente;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DadosClientes {
    final static private String NOMEARQUIVO = "clientes.txt";

    public static void salvarClientes(List<Cliente> clientes) throws IOException {
        BufferedWriter arquivo = null;
        try {
            FileWriter caminho = new FileWriter(NOMEARQUIVO);
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

    public static List<Cliente> carregarClientes() {
        List<Cliente> clientes = new ArrayList<>();
        BufferedReader leitorArquivo = null;
        try {
            FileReader leitorCaminho = new FileReader(NOMEARQUIVO);
            leitorArquivo = new BufferedReader(leitorCaminho);
            String linha;
            while ((linha = leitorArquivo.readLine()) != null) {
                clientes.add(Cliente.receberDados(linha));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if (leitorArquivo != null) {
                try {
                    leitorArquivo.close();
                } catch (IOException e){
                    System.out.println(e.getMessage());
                }
            }
        }
        return clientes;
    }
}
