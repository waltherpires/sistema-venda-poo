import java.util.Date;

public abstract class Venda {
    private Cliente cliente;
    private Produto produto;
    private Date dataVenda;

    public Venda(Cliente cliente, Produto produto, Date dataVenda) {
        this.cliente = cliente;
        this.produto = produto;
        this.dataVenda = dataVenda;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Produto getProduto() {
        return produto;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public abstract double calcularTotal();
}
