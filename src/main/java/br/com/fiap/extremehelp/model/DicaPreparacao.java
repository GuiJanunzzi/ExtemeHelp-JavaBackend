package br.com.fiap.extremehelp.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entidade que representa uma dica de preparação no sistema da ExtemeHelp")
public class DicaPreparacao {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único da dica", example = "1", readOnly = true)
    private Long id;

    @NotBlank(message = "O título é obrigatório")
    @Size(max = 150, message = "O título deve ter no máximo 150 caracteres")
    @Schema(description = "Título da notícia", example = "Campanha de doações começa nesta segunda-feira", required = true, maxLength = 150)
    private String titulo;

    @NotBlank(message = "O conteúdo é obrigatório")
    @Size(max = 3000, message = "O conteúdo deve ter no máximo 3000 caracteres")
    @Schema(description = "Conteúdo completo da notícia", example = "A campanha arrecada roupas e alimentos para famílias afetadas pelas enchentes...", required = true)
    private String conteudo;

    @NotBlank(message = "A categoria é obrigatória")
    @Size(max = 50, message = "A categoria deve ter no máximo 50 caracteres")
    @Schema(description = "Categoria da notícia", example = "Campanha", required = true)
    private String categoria;

    @NotNull(message = "A data de atualização é obrigatória")
    @PastOrPresent(message = "A data de atualização não pode estar no futuro")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    @Schema(description = "Data da última atualização da notícia", example = "01/06/2025 09:00", required = true)
    private LocalDateTime dataAtualizacao;
}
