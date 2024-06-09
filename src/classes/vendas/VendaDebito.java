package classes.vendas;

import classes.clientes.Cliente;
import classes.clientes.ClienteVIP;
import classes.produtos.Produto;

import java.util.Date;

public class VendaDebito extends Venda {
    public VendaDebito(Cliente cliente, Produto produto, Date dataVenda) {
        super(cliente, produto, dataVenda);
    }

    public VendaDebito(Cliente cliente, Produto produto, Date dataVenda, double desconto) {
        super(cliente, produto, dataVenda, desconto);
    }

    public double calcularTotal(double desconto) {
        if(getCliente() instanceof ClienteVIP) {
            return getProduto().getPreco() * ((100 - desconto)/100);
        }

        System.out.println("Venda com 5% de taxa");
        System.out.println("Desconto de: " + desconto + "% aplicado.");
        return (getProduto().getPreco()*1.05) * ((100 - desconto)/100);
    }

    public double calcularTotal() {
        if(getCliente() instanceof ClienteVIP) {
            return getProduto().getPreco();
        }
        return getProduto().getPreco()*1.05;
    }

    public String valorTotal(){
        if(getDesconto() != 0){
            return "Valor: " + calcularTotal(getDesconto()) + " (Desconto: " + getDesconto() + "%";
        }
        return "Valor: " + calcularTotal();
    }

    public String toString(){
        if(getCliente() instanceof ClienteVIP) {
            System.out.println("Cliente VIP: Taxas anuladas!");
        }
        return
                "Venda Debito: " + "\n" +
                "Cliente : " + getCliente().getNome() + "\n" +
                "Produto: " + getProduto().getNome() +  "\n" +
                valorTotal() + "\n" +
                "Data: " + getDataVenda() + "\n" +
                "---------------------------------------------";
    }
}
