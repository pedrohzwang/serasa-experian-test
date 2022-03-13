package com.experian.cadastro.services;

import com.experian.cadastro.dto.PessoaDTOIndividual;
import com.experian.cadastro.dto.PessoaDTOLista;
import com.experian.cadastro.dto.PessoaDTOSalvamento;
import com.experian.cadastro.entities.Pessoa;
import com.experian.cadastro.enums.Score;
import com.experian.cadastro.repositories.PessoaRepository;
import com.experian.cadastro.services.exceptions.ResourceNotFoundException;
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
    public List<PessoaDTOLista> listarTodos() {
        List<Pessoa> pessoas = repository.findAll();

        return pessoas.stream().map(pessoa ->
                new PessoaDTOLista(pessoa, gerarScoreDescricao(pessoa.getScore()))).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PessoaDTOIndividual getPessoaPorId(Long id) {
        Optional<Pessoa> registro = repository.findById(id);
        Pessoa entidade = registro.orElseThrow(() -> new ResourceNotFoundException("Registro não encontrado!"));
        return new PessoaDTOIndividual(entidade, gerarScoreDescricao(entidade.getScore()));
    }

    public Score gerarScoreDescricao(Integer score) {
        Score scoreDescription;
        if (score <= 200) {
            scoreDescription = Score.INSUFICIENTE;
        } else if (score >= 201 && score <= 500) {
            scoreDescription = Score.INACEITAVEL;
        } else if (score >= 501 && score <= 700) {
            scoreDescription = Score.ACEITAVEL;
        } else {
            scoreDescription = Score.RECOMENDAVEL;
        }

        return scoreDescription;
    }

    @Transactional
    public PessoaDTOSalvamento cadastrarPessoa(PessoaDTOSalvamento dto) {
        Pessoa entidade = new Pessoa();
        copiarDTOParaEntidade(dto, entidade);
        return new PessoaDTOSalvamento(repository.save(entidade));
    }

    private void copiarDTOParaEntidade(PessoaDTOSalvamento dto, Pessoa entidade) {
        entidade.setNome(dto.getNome());
        entidade.setIdade(dto.getIdade());
        entidade.setTelefone(dto.getTelefone());
        entidade.setCidade(dto.getCidade());
        entidade.setEstado(dto.getEstado());
        entidade.setScore(dto.getScore());
    }

}
