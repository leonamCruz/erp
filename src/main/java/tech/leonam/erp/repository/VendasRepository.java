package tech.leonam.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.leonam.erp.model.entity.Vendas;

@Repository
public interface VendasRepository extends JpaRepository<Vendas, Long> {
}
