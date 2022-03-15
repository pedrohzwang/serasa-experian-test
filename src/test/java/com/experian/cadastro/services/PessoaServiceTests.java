package com.experian.cadastro.services;

import com.experian.cadastro.dto.PessoaDTO;
import com.experian.cadastro.dto.PessoaDTOListarTodos;
import com.experian.cadastro.dto.PessoaDTOSalvar;
import com.experian.cadastro.entities.Pessoa;
import com.experian.cadastro.enums.Score;
import com.experian.cadastro.repositories.PessoaRepository;
import com.experian.cadastro.services.exceptions.RecursoNaoEncontradoException;
import com.experian.cadastro.tests.PessoaFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class PessoaServiceTests {

    @InjectMocks
    private PessoaService service;

    @Mock
    private PessoaRepository repository;

    private List<Pessoa> pessoaLista;
    private long idExistente;
    private long idNaoExistente;
    private Pessoa pessoa;
    private PessoaDTOSalvar salvarDto;

    @BeforeEach
    void beforeEach() throws Exception {
        pessoaLista = PessoaFactory.criarListaPessoas();
        idExistente = 1L;
        idNaoExistente = 5000L;
        pessoa = PessoaFactory.criarPessoa();
        salvarDto = PessoaFactory.criarPessoaDTOSalvar();

        Mockito.when(repository.findAll()).thenReturn(pessoaLista);

        Mockito.when(repository.findById(idExistente)).thenReturn(Optional.of(pessoa));
        Mockito.when(repository.findById(idNaoExistente)).thenReturn(Optional.empty());

        Mockito.when(repository.save(ArgumentMatchers.any())).thenReturn(pessoa);
    }

    @Test
    public void salvarPessoaDeveRetornarPessoaDTOSalvar() {
        PessoaDTOSalvar dto = service.salvarPessoa(salvarDto);

        Assertions.assertNotNull(dto);
        Mockito.verify(repository).save(ArgumentMatchers.any());
    }

    @Test
    public void listarTodosDeveRetornarPessoaDTOListarTodos() {
        List<PessoaDTOListarTodos> dtoList = service.listarTodos();

        Assertions.assertNotNull(dtoList);
        Mockito.verify(repository).findAll();
    }

    @Test
    public void buscarPorIdDeveRetornarPessoaDTOQuandoIdExistir() {
        PessoaDTO dto = service.buscarPorId(idExistente);

        Assertions.assertNotNull(dto);
        Mockito.verify(repository).findById(idExistente);
    }

    @Test
    public void buscarPorIdDeveLancarRecursoNaoEncontradoExceptionQuandoIdNaoExistir() {
        Assertions.assertThrows(RecursoNaoEncontradoException.class, () -> service.buscarPorId(idNaoExistente));

        Mockito.verify(repository).findById(idNaoExistente);
    }

    @Test
    public void gerarDescricaoScoreDeveRetornarScoreInsuficiente() {
        Score valorEsperado = Score.INSUFICIENTE;
        Score score = service.gerarDescricaoScore(200);

        Assertions.assertEquals(score, valorEsperado);
    }

    @Test
    public void gerarDescricaoScoreDeveRetornarScoreInaceitavel() {
        Score valorEsperado = Score.INACEITAVEL;
        Score score = service.gerarDescricaoScore(500);

        Assertions.assertEquals(score, valorEsperado);
    }

    @Test
    public void gerarDescricaoScoreDeveRetornarScoreAceitavel() {
        Score valorEsperado = Score.ACEITAVEL;
        Score score = service.gerarDescricaoScore(700);

        Assertions.assertEquals(score, valorEsperado);
    }

    @Test
    public void gerarDescricaoScoreDeveRetornarScoreRecomendavel() {
        Score valorEsperado = Score.RECOMENDAVEL;
        Score score = service.gerarDescricaoScore(1000);

        Assertions.assertEquals(score, valorEsperado);
    }

}
