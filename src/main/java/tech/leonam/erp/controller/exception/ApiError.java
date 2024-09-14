package tech.leonam.erp.controller.exception;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ApiError {
    private String descricao;
    private List<String> error;
    
    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();

}
