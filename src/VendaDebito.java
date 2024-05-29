import java.util.Date;

public class VendaDebito extends Venda{
    public VendaDebito(Cliente cliente, Produto produto, Date dataVenda) {
        super(cliente, produto, dataVenda);
    }

    public double calcularTotal() {
        return getProduto().getPreco()*1.05;
    }

    public String toString(){
        return "Venda Debito { Cliente: " + getCliente() + " | Produto: " + getProduto() + " | Data: " + getDataVenda() + " }";
    }
}
