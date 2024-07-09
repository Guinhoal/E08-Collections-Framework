import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Conta implements ITaxas {

    private int numero;
    private Cliente dono;
    private double saldo;
    protected double limite;
    private List<Operacao> operacoes;
    private static int totalContas = 0;

    public Conta(int numero, Cliente dono, double saldo, double limite) {
        this.numero = numero;
        this.dono = dono;
        this.saldo = saldo;
        this.limite = limite;
        this.operacoes = new ArrayList<>();
        Conta.totalContas++;
    }

    public boolean sacar(double valor) {
        if (valor >= 0 && valor <= this.limite) {
            this.saldo -= valor;
            this.operacoes.add(new OperacaoSaque(valor)); // Atualizado para usar add()
            return true;
        }
        return false;
    }

    public void depositar(double valor) {
        this.saldo += valor;
        this.operacoes.add(new OperacaoDeposito(valor)); // Atualizado para usar add()
    }

    public boolean transferir(Conta destino, double valor) {
        boolean valorSacado = this.sacar(valor);
        if (valorSacado) {
            destino.depositar(valor);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return dono.toString() + '\n' +
                "---\n" +
                "numero=" + numero + '\n' +
                "saldo=" + saldo + '\n' +
                "limite=" + limite;
    }

    public void imprimirExtrato(int ordem) {
        if (ordem == 1) {
            Collections.sort(operacoes);
        }
        System.out.println("======= Extrato Conta " + this.numero + " =======");
        for (Operacao operacao : operacoes) {
            System.out.println(operacao);
        }
        System.out.println("=================================");
    }

    public void imprimirExtratoTaxas() {
        System.out.println("=== Extrato de Taxas ===");
        System.out.printf("Manutenção:\t%.2f\n", this.calcularTaxas());

        double totalTaxas = this.calcularTaxas();
        for (Operacao atual : this.operacoes) { // Atualizado para usar for-each
            totalTaxas += atual.calcularTaxas();
            System.out.printf("%c:\t%.2f\n", atual.getTipo(), atual.calcularTaxas());
        }

        System.out.printf("Total:\t%.2f\n", totalTaxas);
    }

    public int getNumero() {
        return numero;
    }

    public Cliente getDono() {
        return dono;
    }

    public double getSaldo() {
        return saldo;
    }

    public double getLimite() {
        return limite;
    }

    public static int getTotalContas() {
        return Conta.totalContas;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setDono(Cliente dono) {
        this.dono = dono;
    }

    public abstract void setLimite(double limite);
}
