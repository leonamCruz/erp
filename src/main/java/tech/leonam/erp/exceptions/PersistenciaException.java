package tech.leonam.erp.exceptions;

/*
 * Essa exception serve para referência para qualquer exception que será lançada ao persistir no Spring JPA
 */
public class PersistenciaException extends RuntimeException{
    PersistenciaException(String msg){
        super(msg);      
    }
}
