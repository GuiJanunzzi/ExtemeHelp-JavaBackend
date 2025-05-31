package br.com.fiap.extremehelp.config;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.extremehelp.model.TipoUsuario;
import br.com.fiap.extremehelp.model.Usuario;
import br.com.fiap.extremehelp.repository.UsuarioRepository;
import jakarta.annotation.PostConstruct;

@Configuration
public class DatabaseSeeder {
    
    @Autowired
    UsuarioRepository usuarioRepository;

    @PostConstruct
    public void init(){
        var usuarios = List.of(
            Usuario.builder()
                .nome("Cauã Aragão")
                .email("aragao@mottomap.com")
                .senha("caua12345")
                .telefone("(11) 96217-2718")
                .tipoUsuario(TipoUsuario.SOLICITANTE)
                .dataRegistro(LocalDate.parse("2025-05-21" , DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .status(false)
                .build(),

            Usuario.builder()
                .nome("Gustavo Oliveira")
                .email("gustavo@mottomap.com")
                .senha("gustavo12345")
                .telefone("(11) 96217-6547")
                .tipoUsuario(TipoUsuario.VOLUNTARIO)
                .dataRegistro(LocalDate.parse("2025-05-27" , DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .status(true)
                .build(),

            Usuario.builder()
                .nome("Pedro Barbosa")
                .email("p.barbosa@mottomap.com")
                .senha("barbosa12345")
                .telefone("(11) 97843-1293")
                .tipoUsuario(TipoUsuario.ADMIN)
                .dataRegistro(LocalDate.parse("2025-05-30" , DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .status(true)
                .build()
        );

        usuarioRepository.saveAll(usuarios);
    }
}
