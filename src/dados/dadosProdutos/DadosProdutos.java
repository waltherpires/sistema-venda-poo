package dados.dadosProdutos;

import classes.clientes.Cliente;
import classes.produtos.Produto;
import exceptions.EscolhaInvalidaException;

import java.io.*;
import java.nio.Buffer;
import java.util.HashSet;
import java.util.Set;

public class DadosProdutos {

    public static void salvarProdutos(Set<Produto> produtos) throws IOException {
        BufferedWriter arquivo = null;
        try {
            FileWriter caminho = new FileWriter("produtos.txt");
            arquivo = new BufferedWriter(caminho);
            for(Produto produto : produtos) {
                arquivo.write(produto.enviarDados());
                arquivo.newLine();
            }
        } catch(IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if(arquivo != null) {
                arquivo.close();
            }
        }
    }

    public static Set<Produto> carregarProdutos() throws IOException{
        Set<Produto> produtos = new HashSet<>();
        BufferedReader leitorArquivo = null;

        try {
            FileReader leitorCaminho = new FileReader("produtos.txt");
            leitorArquivo = new BufferedReader(leitorCaminho);
            String linha = leitorArquivo.readLine();
            while(linha != null) {
                produtos.add(Produto.receberDados(linha));
                linha = leitorArquivo.readLine();
            }
        } catch (IOException | EscolhaInvalidaException e) {
            System.out.println(e.getMessage());
        } finally {
            if(leitorArquivo != null) {
                leitorArquivo.close();
            }
        }

        return produtos;
    }
}
