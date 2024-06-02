package classes.vendas;

import classes.clientes.Cliente;
import classes.clientes.ClienteVIP;
import classes.produtos.Produto;

import java.util.Date;

public class VendaCredito extends Venda {
    public VendaCredito(Cliente cliente, Produto produto, Date dataVenda) {
        super(cliente, produto, dataVenda);
    }

    public double calcularTotal(){
        if(getCliente() instanceof ClienteVIP) {
            return getProduto().getPreco();
        }

        System.out.println("Venda com 10% de taxa");
        return getProduto().getPreco()*1.10;
    }

    public String toString(){
        if(getCliente() instanceof ClienteVIP) {
            System.out.println("Cliente VIP: Taxas anuladas!");
        }
        return "Venda Crédito: " + "\n" +
                "Cliente : " + getCliente().getNome() + "\n" +
                "Produto: " + getProduto().getNome() +  "\n" +
                "Valor: " + calcularTotal() + "\n" +
                "Data: " + getDataVenda() + "\n" +
                "---------------------------------------------";
    }
}
