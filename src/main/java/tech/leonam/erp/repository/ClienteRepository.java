package tech.leonam.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tech.leonam.erp.model.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query("SELECT COUNT(c) > 0 FROM Cliente c WHERE c.cpf = :cpf AND :cpf IS NOT NULL AND :cpf <> ''")
    public Boolean existsByCpf(@Param("cpf") String cpf);

    @Query("SELECT COUNT(c) > 0 FROM Cliente c WHERE c.cnpj = :cnpj AND :cnpj IS NOT NULL AND :cnpj <> ''")
    public Boolean existsByCnpj(@Param("cnpj") String cpf);
}
