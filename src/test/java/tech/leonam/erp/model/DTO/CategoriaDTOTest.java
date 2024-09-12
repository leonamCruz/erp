package tech.leonam.erp.model.DTO;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.SecureRandom;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CategoriaDTOTest {
    CategoriaDTO categoria;
    Validator validator;
    SecureRandom random;

    String retornaQuantidadeDeLetrasAleatorias(int quantidadeDeLetras) {
        if (quantidadeDeLetras <= 0) {
            throw new IllegalArgumentException("Quantidade de letras deve ser maior que zero.");
        }

        StringBuilder letrasAleatorias = new StringBuilder();
        for (int i = 0; i < quantidadeDeLetras; i++) {
            int codigoAleatorio = random.nextInt(62);
            char caractereAleatorio = switch (codigoAleatorio / 26) {
                case 0 -> (char) ('A' + codigoAleatorio);
                case 1 -> (char) ('a' + (codigoAleatorio - 26));
                case 2 -> (char) ('0' + (codigoAleatorio - 52));
                default -> throw new IllegalStateException("Código aleatório fora do esperado.");
            };

            letrasAleatorias.append(caractereAleatorio);
        }

        return letrasAleatorias.toString();
    }

    @BeforeEach
    void setup() {
        categoria = new CategoriaDTO();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        random = new SecureRandom();
    }

    @Test
    void DTOCorreto() {
        categoria.setAtivo(true);

        categoria.setNome(retornaQuantidadeDeLetrasAleatorias(3));
        categoria.setDescricao(retornaQuantidadeDeLetrasAleatorias(510));

        Set<ConstraintViolation<CategoriaDTO>> violations = validator.validate(categoria);

        assertThat(violations).isEmpty();
    }

    @Test
    void DTOIncorreto() {
        categoria.setAtivo(null);
        categoria.setNome(retornaQuantidadeDeLetrasAleatorias(2));
        categoria.setDescricao(retornaQuantidadeDeLetrasAleatorias(511));

        Set<ConstraintViolation<CategoriaDTO>> violations = validator.validate(categoria);

        assertThat(violations).hasSize(3);
    }

    @Test
    void quantidadeDeLetrasCerta() {
        assertEquals(4, retornaQuantidadeDeLetrasAleatorias(4).length(), "Quantidade de letras deve ser 4");
    }


}