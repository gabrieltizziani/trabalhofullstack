package com.example.cadastroPessoa.pessoa;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tb_pessoa")
public class PessoaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPessoa;

    @NotNull(message = "O campo nome não pode ser vazio!")
    @Column(nullable = false)
    private String nomePessoa;

    @NotNull(message = "O campo CPF não pode ser vazio!")
    @Column(nullable = false, unique = true, length = 11)
    @Pattern(regexp = "[0-9]{11}", message = "Deve conter exatamente 11 números")
    @Size(min = 11, max = 14)
    private String cpfPessoa;

    @NotNull(message = "O campo número não pode ser vazio!")
    @Column(nullable = false)
    private String numeroPessoa;


}
