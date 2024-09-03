package com.example.cadastroPessoa.pessoa;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {
    @Autowired
    PessoaService pessoaService;

    @GetMapping
    public ResponseEntity< List<PessoaModel>> listarPessoa() {
        List<PessoaModel> pessoas = pessoaService.listarPessoa();
        return ResponseEntity.ok(pessoas);
    }
    @PostMapping
    public ResponseEntity<PessoaModel> criarPessoa (@Valid @RequestBody PessoaModel pessoaModel){
        PessoaModel novaPessoa = pessoaService.criarPessoa(pessoaModel);
        return new ResponseEntity<>(novaPessoa, HttpStatus.CREATED);
    }

    @PutMapping("/{idPessoa}")
    public ResponseEntity<?> editarPessoa(@PathVariable Long idPessoa, @RequestBody PessoaModel pessoaModel) {
        if (pessoaService.editarPessoa(idPessoa, pessoaModel) == null) {
            String mensagem = " o id informado nao existe na base de dados";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensagem);
        }
        return ResponseEntity.ok(pessoaModel);
    }

    @DeleteMapping("/{idPessoa}")
    public ResponseEntity<?> excluirPessoa(@PathVariable Long idPessoa) {
        if (pessoaService.excluirPessoa(idPessoa)) {
            String mensagem = "A deleção do id: " + idPessoa + " foi realizada com sucesso.";
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(mensagem);
        }
        String mensagem = " o id informado nao existe na base de dados";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensagem);
    }

    @GetMapping("/{nomePessoa}")
    public ResponseEntity<PessoaModel> buscarPessoaPorNome(@PathVariable String nomePessoa) {
        Optional<PessoaModel> pessoa = pessoaService.buscarPessoaPorNome(nomePessoa);
        return pessoa.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
