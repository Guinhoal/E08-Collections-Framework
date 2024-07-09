import java.util.Date;

public abstract class Operacao implements ITaxas, Comparable<Operacao> {

    private Date data;
    private char tipo;
    private double valor;
    private static int totalOperacoes = 0;

    public Operacao(char tipo, double valor) {
        this.tipo = tipo;
        this.valor = valor;
        this.data = new Date();
        Operacao.totalOperacoes++;
    }

    @Override
    public int compareTo(Operacao outraOperacao) {
        if (this.tipo == outraOperacao.tipo) {
            return this.data.compareTo(outraOperacao.data);
        }
        return this.tipo == 'd' ? -1 : 1;
    }

    @Override
    public String toString() {
        return String.format("%s\t%s\t%.2f", this.data, this.tipo, this.valor);
    }

    public Date getData() {
        return data;
    }

    public char getTipo() {
        return tipo;
    }

    public double getValor() {
        return valor;
    }

    public static int getTotalOperacoes() {
        return Operacao.totalOperacoes;
    }

    public void setTipo(char tipo) {
        if (tipo == 'd' || tipo == 's') {
            this.tipo = tipo;
        }
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}