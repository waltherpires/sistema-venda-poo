package classes.vendas;

import classes.clientes.Cliente;
import classes.produtos.Produto;

import java.util.Date;

public abstract class Venda {
    private Cliente cliente;
    private Produto produto;
    private Date dataVenda;
    private int desconto;

    public Venda(Cliente cliente, Produto produto, Date dataVenda) {
        this.cliente = cliente;
        this.produto = produto;
        this.dataVenda = dataVenda;
    }

    public Venda(Cliente cliente, Produto produto, Date dataVenda, int desconto){
        this.cliente = cliente;
        this.produto = produto;
        this.dataVenda = dataVenda;
        this.desconto = desconto;
    }

    public int getDesconto(){ return desconto; }

    public Cliente getCliente() {
        return cliente;
    }

    public Produto getProduto() {
        return produto;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public abstract String toString();

    public abstract double calcularTotal();
}
