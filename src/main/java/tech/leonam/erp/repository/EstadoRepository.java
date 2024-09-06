package tech.leonam.erp.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class EstadoRepository {

    private final JdbcTemplate jdbcTemplate;

    public  Map<String, String> findAll() {
        List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT nome, sigla FROM estados");

        Map<String, String> estadosMap = new HashMap<>();

        for (Map<String, Object> row : rows) {
            String nome = (String) row.get("nome");
            String sigla = (String) row.get("sigla");
            estadosMap.put(nome, sigla);
        }

        return estadosMap;
    }

}
