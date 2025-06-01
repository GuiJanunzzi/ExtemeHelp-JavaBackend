package br.com.fiap.extremehelp.config;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.extremehelp.model.AtendimentoVoluntario;
import br.com.fiap.extremehelp.model.PedidoAjuda;
import br.com.fiap.extremehelp.model.StatusPedido;
import br.com.fiap.extremehelp.model.TipoUsuario;
import br.com.fiap.extremehelp.model.Usuario;
import br.com.fiap.extremehelp.repository.AtendimentoVoluntarioRepository;
import br.com.fiap.extremehelp.repository.PedidoAjudaRepository;
import br.com.fiap.extremehelp.repository.UsuarioRepository;
import jakarta.annotation.PostConstruct;

@Configuration
public class DatabaseSeeder {
    
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PedidoAjudaRepository pedidoAjudaRepository;

    @Autowired
    AtendimentoVoluntarioRepository atendimentoVoluntarioRepository;

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

        var pedidosAjuda = List.of(
            PedidoAjuda.builder()
            .tipoAjuda("Alimentação")
            .descricão("Solicita cesta básica para família com 2 crianças")
            .latitude(-23.561234)
            .longitude(-46.655678)
            .endereco("Rua das Laranjeiras, 150, São Paulo, SP")
            .dataPedido(LocalDateTime.parse("2025-05-28 16:51", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
            .statusPedido(StatusPedido.PENDENTE)
            .usuario(usuarios.get(0)) // Supondo método existente
            .build(),

        PedidoAjuda.builder()
            .tipoAjuda("Remédios")
            .descricão("Ajuda para obter medicamentos controlados para idosa de 74 anos")
            .latitude(-23.567891)
            .longitude(-46.678901)
            .endereco("Avenida Paulista, 1000, São Paulo, SP")
            .dataPedido(LocalDateTime.parse("2025-05-29 10:21", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
            .statusPedido(StatusPedido.EM_ATENDIMENTO)
            .usuario(usuarios.get(1))
            .build(),

        PedidoAjuda.builder()
            .tipoAjuda("Acompanhamento Médico")
            .descricão("Precisa de acompanhamento médico para paciente com mobilidade reduzida")
            .latitude(-23.587328)
            .longitude(-46.600000)
            .endereco("Rua Bela Vista, 45, São Paulo, SP")
            .dataPedido(LocalDateTime.parse("2025-05-30 17:43", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
            .statusPedido(StatusPedido.CONCLUIDO)
            .usuario(usuarios.get(2))
            .build()
        );

        pedidoAjudaRepository.saveAll(pedidosAjuda);

        var atendimentos = List.of(
            AtendimentoVoluntario.builder()
            .dataAceite(LocalDateTime.parse("2025-05-28T14:30"))
            .dataConclusao(null) // ainda não concluído
            .observacoes(null)   // sem observações ainda
            .pedidoAjuda(pedidosAjuda.get(0))
            .usuario(usuarios.get(1))
            .build(),

        AtendimentoVoluntario.builder()
            .dataAceite(LocalDateTime.parse("2025-05-29T09:00"))
            .dataConclusao(LocalDateTime.parse("2025-05-30T11:45"))
            .observacoes("Entrega realizada com sucesso. Beneficiário agradeceu.")
            .pedidoAjuda(pedidosAjuda.get(1))
            .usuario(usuarios.get(1))
            .build(),

        AtendimentoVoluntario.builder()
            .dataAceite(LocalDateTime.parse("2025-05-31T08:15"))
            .dataConclusao(null) // ainda em andamento
            .observacoes("Voluntário saiu para entrega, previsão de chegada às 10h.")
            .pedidoAjuda(pedidosAjuda.get(2))
            .usuario(usuarios.get(1))
            .build()
        );

        atendimentoVoluntarioRepository.saveAll(atendimentos);
    }
}
