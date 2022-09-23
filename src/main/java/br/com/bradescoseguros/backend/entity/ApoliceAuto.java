package br.com.bradescoseguros.backend.entity;

import java.util.Date;

public class ApoliceAuto {

    private Integer id;
    private Integer seguradoId;
    private String cia;
    private String numeroDocumentoCorretor;
    private String emailCorretor;
    private Date dataInicioVigencia;
    private Date dataFimVigencia;
    private String descricao;
    private String item;
    private String nomeCorretor;
    private String quantidadeDiasRenovacao;
    private String ramo;
    private String segmento;
    private String sucursal;
    private TipoApolice tipoApolice;
    private String marcaVeiculo;

    public ApoliceAuto() {
    }

    public ApoliceAuto(Integer seguradoId, String cia, String numeroDocumentoCorretor, String emailCorretor,
            Date dataInicioVigencia, Date dataFimVigencia, String descricao, String item, String nomeCorretor,
            String quantidadeDiasRenovacao, String ramo, String segmento, String sucursal, TipoApolice tipoApolice,
            String marcaVeiculo) {
        this.seguradoId = seguradoId;
        this.cia = cia;
        this.numeroDocumentoCorretor = numeroDocumentoCorretor;
        this.emailCorretor = emailCorretor;
        this.dataInicioVigencia = dataInicioVigencia;
        this.dataFimVigencia = dataFimVigencia;
        this.descricao = descricao;
        this.item = item;
        this.nomeCorretor = nomeCorretor;
        this.quantidadeDiasRenovacao = quantidadeDiasRenovacao;
        this.ramo = ramo;
        this.segmento = segmento;
        this.sucursal = sucursal;
        this.tipoApolice = tipoApolice;
        this.marcaVeiculo = marcaVeiculo;
    }

    public ApoliceAuto(Integer id, Integer seguradoId, String cia, String numeroDocumentoCorretor, String emailCorretor,
            Date dataInicioVigencia, Date dataFimVigencia, String descricao, String item, String nomeCorretor,
            String quantidadeDiasRenovacao, String ramo, String segmento, String sucursal, TipoApolice tipoApolice,
            String marcaVeiculo) {
        this.id = id;
        this.seguradoId = seguradoId;
        this.cia = cia;
        this.numeroDocumentoCorretor = numeroDocumentoCorretor;
        this.emailCorretor = emailCorretor;
        this.dataInicioVigencia = dataInicioVigencia;
        this.dataFimVigencia = dataFimVigencia;
        this.descricao = descricao;
        this.item = item;
        this.nomeCorretor = nomeCorretor;
        this.quantidadeDiasRenovacao = quantidadeDiasRenovacao;
        this.ramo = ramo;
        this.segmento = segmento;
        this.sucursal = sucursal;
        this.tipoApolice = tipoApolice;
        this.marcaVeiculo = marcaVeiculo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSeguradoId() {
        return seguradoId;
    }

    public void setSeguradoId(Integer seguradoId) {
        this.seguradoId = seguradoId;
    }

    public String getCia() {
        return cia;
    }

    public void setCia(String cia) {
        this.cia = cia;
    }

    public String getNumeroDocumentoCorretor() {
        return numeroDocumentoCorretor;
    }

    public void setNumeroDocumentoCorretor(String numeroDocumentoCorretor) {
        this.numeroDocumentoCorretor = numeroDocumentoCorretor;
    }

    public String getEmailCorretor() {
        return emailCorretor;
    }

    public void setEmailCorretor(String emailCorretor) {
        this.emailCorretor = emailCorretor;
    }

    public Date getDataInicioVigencia() {
        return dataInicioVigencia;
    }

    public void setDataInicioVigencia(Date dataInicioVigencia) {
        this.dataInicioVigencia = dataInicioVigencia;
    }

    public Date getDataFimVigencia() {
        return dataFimVigencia;
    }

    public void setDataFimVigencia(Date dataFimVigencia) {
        this.dataFimVigencia = dataFimVigencia;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getNomeCorretor() {
        return nomeCorretor;
    }

    public void setNomeCorretor(String nomeCorretor) {
        this.nomeCorretor = nomeCorretor;
    }

    public String getQuantidadeDiasRenovacao() {
        return quantidadeDiasRenovacao;
    }

    public void setQuantidadeDiasRenovacao(String quantidadeDiasRenovacao) {
        this.quantidadeDiasRenovacao = quantidadeDiasRenovacao;
    }

    public String getRamo() {
        return ramo;
    }

    public void setRamo(String ramo) {
        this.ramo = ramo;
    }

    public String getSegmento() {
        return segmento;
    }

    public void setSegmento(String segmento) {
        this.segmento = segmento;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    public TipoApolice getTipoApolice() {
        return tipoApolice;
    }

    public void setTipoApolice(TipoApolice tipoApolice) {
        this.tipoApolice = tipoApolice;
    }

    public String getMarcaVeiculo() {
        return marcaVeiculo;
    }

    public void setMarcaVeiculo(String marcaVeiculo) {
        this.marcaVeiculo = marcaVeiculo;
    }

}
