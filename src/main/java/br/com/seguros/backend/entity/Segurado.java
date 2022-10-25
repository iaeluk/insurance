package br.com.seguros.backend.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Segurado {

    private Integer id;
    private String nome;
    private String numeroDocumento;
    private TipoPessoa tipoPessoa;
    private Date dataNascimento;
    private String email;

    public Segurado(String nome, String numeroDocumento, TipoPessoa tipoPessoa, Date dataNascimento,
            String email) {
        this.nome = nome;
        this.numeroDocumento = numeroDocumento;
        this.tipoPessoa = tipoPessoa;
        this.dataNascimento = dataNascimento;
        this.email = email;
    }
}
