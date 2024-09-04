package tech.leonam.erp.excessoes;

public class ClienteNaoDeletado extends Exception {
    public ClienteNaoDeletado(String message) {
        super(message);
    }
}
