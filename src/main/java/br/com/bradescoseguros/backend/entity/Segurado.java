package br.com.bradescoseguros.backend.entity;

import java.util.Date;

public class Segurado {

    private Integer id;
    private String nome;
    private String numeroDocumento;
    private TipoPessoa tipoPessoa;
    private Date dataNascimento;
    private String email;

    public Segurado() {
    }

    public Segurado(String nome, String numeroDocumento, TipoPessoa tipoPessoa, Date dataNascimento,
            String email) {
        this.nome = nome;
        this.numeroDocumento = numeroDocumento;
        this.tipoPessoa = tipoPessoa;
        this.dataNascimento = dataNascimento;
        this.email = email;
    }

    public Segurado(Integer id, String nome, String numeroDocumento, TipoPessoa tipoPessoa, Date dataNascimento,
            String email) {
        this.id = id;
        this.nome = nome;
        this.numeroDocumento = numeroDocumento;
        this.tipoPessoa = tipoPessoa;
        this.dataNascimento = dataNascimento;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public TipoPessoa getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(TipoPessoa tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
