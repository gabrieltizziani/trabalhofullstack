package com.example.cadastroPessoa.pessoa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    PessoaRepository pessoaRepository;

    public List<PessoaModel> listarPessoa(){
        return pessoaRepository.findAll();
    }
    public Optional<PessoaModel> buscarPessoaPorNome(String nomePessoa) {
        return pessoaRepository.findByNomePessoa(nomePessoa);
    }
    public  PessoaModel criarPessoa(PessoaModel pessoaModel){
        return pessoaRepository.save(pessoaModel);
    }

    public ResponseEntity<PessoaModel> editarPessoa(Long idPessoa, PessoaModel pessoaModel) {
        if (pessoaRepository.existsById(idPessoa)) {
            pessoaModel.setIdPessoa(idPessoa);
            PessoaModel pessoaAtualizada = pessoaRepository.save(pessoaModel);
            return ResponseEntity.status(HttpStatus.OK).body(pessoaAtualizada);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public boolean excluirPessoa(Long idPessoa){
        if(pessoaRepository.existsById(idPessoa)) {
            pessoaRepository.deleteById(idPessoa);
            return true;
        } return false;
    }
}
