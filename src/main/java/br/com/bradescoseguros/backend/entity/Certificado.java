package br.com.bradescoseguros.backend.entity;

public class Certificado {

    private Integer id;
    private Integer apoliceAutoId;
    private String cdProdutoRet;
    private String chaveNegocio;
    private TipoDescricaoSituacao descricaoSituacao;
    private String nomeProduto;
    private String ramo;

    public Certificado() {
    }

    public Certificado(Integer apoliceAutoId, String cdProdutoRet, String chaveNegocio,
            TipoDescricaoSituacao descricaoSituacao, String nomeProduto, String ramo) {
        this.apoliceAutoId = apoliceAutoId;
        this.cdProdutoRet = cdProdutoRet;
        this.chaveNegocio = chaveNegocio;
        this.descricaoSituacao = descricaoSituacao;
        this.nomeProduto = nomeProduto;
        this.ramo = ramo;
    }

    public Certificado(Integer id, Integer apoliceAutoId, String cdProdutoRet, String chaveNegocio,
            TipoDescricaoSituacao descricaoSituacao, String nomeProduto, String ramo) {
        this.id = id;
        this.apoliceAutoId = apoliceAutoId;
        this.cdProdutoRet = cdProdutoRet;
        this.chaveNegocio = chaveNegocio;
        this.descricaoSituacao = descricaoSituacao;
        this.nomeProduto = nomeProduto;
        this.ramo = ramo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getApoliceAutoId() {
        return apoliceAutoId;
    }

    public void setApoliceAutoId(Integer apoliceAutoId) {
        this.apoliceAutoId = apoliceAutoId;
    }

    public String getCdProdutoRet() {
        return cdProdutoRet;
    }

    public void setCdProdutoRet(String cdProdutoRet) {
        this.cdProdutoRet = cdProdutoRet;
    }

    public String getChaveNegocio() {
        return chaveNegocio;
    }

    public void setChaveNegocio(String chaveNegocio) {
        this.chaveNegocio = chaveNegocio;
    }

    public TipoDescricaoSituacao getDescricaoSituacao() {
        return descricaoSituacao;
    }

    public void setDescricaoSituacao(TipoDescricaoSituacao descricaoSituacao) {
        this.descricaoSituacao = descricaoSituacao;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getRamo() {
        return ramo;
    }

    public void setRamo(String ramo) {
        this.ramo = ramo;
    }

}
