package br.com.bradescoseguros.backend.entity;

public class Endereco {

    private Integer id;
    private Integer seguradoId;
    private String cep;
    private String complemento;
    private String descricaoTipoLogradouro;
    private String logradouro;
    private String numero;
    private String tipoLogradouro;
    private String cidade;
    private String uf;
    private String bairro;

    public Endereco() {
    }

    public Endereco(Integer seguradoId, String cep, String complemento, String descricaoTipoLogradouro,
            String logradouro, String numero, String tipoLogradouro, String cidade, String uf, String bairro) {
        this.seguradoId = seguradoId;
        this.cep = cep;
        this.complemento = complemento;
        this.descricaoTipoLogradouro = descricaoTipoLogradouro;
        this.logradouro = logradouro;
        this.numero = numero;
        this.tipoLogradouro = tipoLogradouro;
        this.cidade = cidade;
        this.uf = uf;
        this.bairro = bairro;
    }

    public Endereco(Integer id, Integer seguradoId, String cep, String complemento, String descricaoTipoLogradouro,
            String logradouro, String numero, String tipoLogradouro, String cidade, String uf, String bairro) {
        this.id = id;
        this.seguradoId = seguradoId;
        this.cep = cep;
        this.complemento = complemento;
        this.descricaoTipoLogradouro = descricaoTipoLogradouro;
        this.logradouro = logradouro;
        this.numero = numero;
        this.tipoLogradouro = tipoLogradouro;
        this.cidade = cidade;
        this.uf = uf;
        this.bairro = bairro;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIntegerId() {
        return seguradoId;
    }

    public void setIntegerId(Integer seguradoId) {
        this.seguradoId = seguradoId;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getDescricaoTipoLogradouro() {
        return descricaoTipoLogradouro;
    }

    public void setDescricaoTipoLogradouro(String descricaoTipoLogradouro) {
        this.descricaoTipoLogradouro = descricaoTipoLogradouro;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTipoLogradouro() {
        return tipoLogradouro;
    }

    public void setTipoLogradouro(String tipoLogradouro) {
        this.tipoLogradouro = tipoLogradouro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

}
