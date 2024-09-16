package tech.leonam.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tech.leonam.erp.model.entity.Estoque;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {
}
