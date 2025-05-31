package br.com.fiap.extremehelp.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
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
@Schema(description = "Entidade que representa um usuário no sistema da ExtemeHelp")
public class Usuario {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único do usuário", example = "1", readOnly = true)
    private Long id;

    @NotBlank(message = "O nome é obrigatório.")
    @Size(min = 3, max = 200, message = "O nome deve ter entre 3 e 100 caracteres.")
    @Schema(description = "Nome completo do usuário", example = "João da Silva", required = true)
    private String nome;

    @NotBlank(message = "O e-mail é obrigatório.")
    @Email(message = "O e-mail deve ser válido.")
    @Schema(description = "E-mail do usuário", example = "joao.silva@email.com", required = true)
    private String email;

    @NotBlank(message = "A senha é obrigatória.")
    @Size(min = 6, message = "A senha deve ter pelo menos 6 caracteres.")
    @Schema(description = "Senha do usuário (mínimo 6 caracteres)", example = "minhaSenha123", required = true)
    private String senha;

    @Pattern(regexp = "\\(\\d{2}\\) \\d{4,5}-\\d{4}", message = "O telefone deve estar no formato (XX) XXXX-XXXX ou (XX) XXXXX-XXXX.")
    @Schema(description = "Telefone para contato", example = "(11) 91234-5678")
    private String telefone;

    @NotNull(message = "O tipo de usuário é obrigatório.")
    @Enumerated(EnumType.STRING)
    @Schema(description = "Tipo de usuário (ex: ADMIN, SOLICITANTE, VOLUNTARIO)", example = "VOLUNTARIO", required = true)
    private TipoUsuario tipoUsuario;

    @PastOrPresent(message = "A data de registro não pode estar no futuro.")
    @PastOrPresent(message = "A data não pode ser no futuro")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Schema(description = "Data de registro do usuário", example = "2024-05-30", required = true)
    private LocalDate dataRegistro;

    @NotNull(message = "O status é obrigatório.")
    @Schema(description = "Status do usuário (ativo/inativo)", example = "true")
    private Boolean status;
}
