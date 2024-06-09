package classes.vendas;

import classes.clientes.Cliente;
import classes.clientes.ClienteVIP;
import classes.produtos.Produto;

import java.util.Date;

public class VendaCredito extends Venda {

    public VendaCredito(Cliente cliente, Produto produto, Date dataVenda) {
        super(cliente, produto, dataVenda);
    }

    public VendaCredito(Cliente cliente, Produto produto, Date dataVenda, double desconto) {
        super(cliente, produto, dataVenda, desconto);
    }

    public double calcularTotal(){
        if(getCliente() instanceof ClienteVIP) {
            return getProduto().getPreco();
        }

        System.out.println("Venda com 10% de taxa");
        return getProduto().getPreco()*1.10;
    }

    public double calcularTotal(double desconto) {
        if(getCliente() instanceof ClienteVIP) {
            return getProduto().getPreco() * ((100 - desconto)/100);
        }

        System.out.println("Venda com 10% de taxa");
        System.out.println("Desconto de: " + desconto + "% aplicado.");
        return (getProduto().getPreco()*1.10) * ((100 - desconto)/100);
    }

    public String valorTotal(){
        if(getDesconto() != 0){
            return "Valor: " + calcularTotal(getDesconto());
        }
        return "Valor: " + calcularTotal();
    }

    public String toString(){
        if(getCliente() instanceof ClienteVIP) {
            System.out.println("Cliente VIP: Taxas anuladas!");
        }

        return "Venda Crédito: " + "\n" +
                "Cliente : " + getCliente().getNome() + "\n" +
                "Produto: " + getProduto().getNome() +  "\n" +
                valorTotal() + "\n" +
                "Data: " + getDataVenda() + "\n" +
                "---------------------------------------------";
    }
}
