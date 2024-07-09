import java.util.Date;

public class Main {
    public static void main(String[] args) {

        Cliente joao = new PessoaFisica("João", "Av. Antonio Carlos, 6627",
                                        new Date(), "111.111.111-11", 36, 'm');
        Cliente lojinha = new PessoaJuridica("Loja R$1,99", "Av. Afonso Pena, 3000",
                                        new Date(), "000.00000.0000/0001", 25, "Comércio");


        Conta cc = new ContaCorrente(1234, joao, 0, 1500);
        Conta cp = new ContaPoupanca(12121, lojinha, 10000, 1500);

        cc.depositar(1000);
        cc.depositar(2000);
        cc.sacar(500);
        cc.depositar(3000);
        cc.sacar(10);
        cc.sacar(15);

        cc.imprimirExtratoTaxas();
    }
}