package com.experian.cadastro.services;

import com.experian.cadastro.dto.PessoaDTO;
import com.experian.cadastro.dto.PessoaDTOListarTodos;
import com.experian.cadastro.dto.PessoaDTOSalvar;
import com.experian.cadastro.entities.Pessoa;
import com.experian.cadastro.enums.Score;
import com.experian.cadastro.repositories.PessoaRepository;
import com.experian.cadastro.services.exceptions.RecursoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PessoaService {

    @Autowired
    PessoaRepository repository;

    @Transactional(readOnly = true)
    public List<PessoaDTOListarTodos> listarTodos() {
        List<Pessoa> pessoasLista = repository.findAll();

        return pessoasLista.stream().map(pessoa ->
                new PessoaDTOListarTodos(pessoa, gerarDescricaoScore(pessoa.getScore()))).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PessoaDTO buscarPorId(Long id) {
        Optional<Pessoa> optional = repository.findById(id);
        Pessoa entidade = optional.orElseThrow(() -> new RecursoNaoEncontradoException("Recurso não encontrado!"));
        return new PessoaDTO(entidade, gerarDescricaoScore(entidade.getScore()));
    }

    public Score gerarDescricaoScore(Integer score) {
        Score descricaoScore;
        if (score <= 200) {
            descricaoScore = Score.INSUFICIENTE;
        } else if (score >= 201 && score <= 500) {
            descricaoScore = Score.INACEITAVEL;
        } else if (score >= 501 && score <= 700) {
            descricaoScore = Score.ACEITAVEL;
        } else {
            descricaoScore = Score.RECOMENDAVEL;
        }

        return descricaoScore;
    }

    @Transactional
    public PessoaDTOSalvar salvarPessoa(PessoaDTOSalvar dto) {
        Pessoa entidade = new Pessoa();
        copiarDTOParaEntidade(dto, entidade);
        entidade = repository.save(entidade);
        return new PessoaDTOSalvar(entidade);
    }

    private void copiarDTOParaEntidade(PessoaDTOSalvar dto, Pessoa entidade) {
        entidade.setNome(dto.getNome());
        entidade.setIdade(dto.getIdade());
        entidade.setTelefone(dto.getTelefone());
        entidade.setCidade(dto.getCidade());
        entidade.setEstado(dto.getEstado());
        entidade.setScore(dto.getScore());
    }

}
