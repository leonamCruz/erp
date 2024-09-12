package tech.leonam.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.leonam.erp.model.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
}
