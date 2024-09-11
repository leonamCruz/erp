package tech.leonam.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.leonam.erp.model.entity.Vendas;

public interface VendasRepository extends JpaRepository<Vendas, Long> {
}
