package tech.leonam.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.leonam.erp.model.entity.Servico;

public interface ServicoRepository extends JpaRepository<Servico, Long> {
}
