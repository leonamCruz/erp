package tech.leonam.erp.model.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriaDTO {
    @NotBlank
    @Size(min = 3, max = 255, message = "Verifique o tamanho do nome.")
    private String nome;
    @NotBlank
    @Size(min = 3, max = 510, message = "Verifique o tamanho da descrição")
    private String descricao;
    @NotNull(message = "Verifique o campo de \"ativo\".")
    private Boolean ativo;
}
