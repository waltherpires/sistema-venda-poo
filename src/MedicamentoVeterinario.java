public class MedicamentoVeterinario extends Produto {
    public MedicamentoVeterinario(String nome, double preco) {
        super(nome, preco);
    }

    public String toString(){
        return "Medicamento: " + getNome() + '\'' + "Preco: " + getPreco();
    }

}
