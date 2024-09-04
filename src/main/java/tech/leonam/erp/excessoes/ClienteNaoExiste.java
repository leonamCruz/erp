package tech.leonam.erp.excessoes;

public class ClienteNaoExiste extends Exception {
    public ClienteNaoExiste(String message) {
        super(message);
    }
}
