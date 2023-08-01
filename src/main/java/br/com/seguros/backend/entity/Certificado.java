package br.com.seguros.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Certificado implements Serializable {

    private Integer id;
    private Integer apoliceAutoId;
    private String cdProdutoRet;
    private String chaveNegocio;
    private TipoDescricaoSituacao descricaoSituacao;
    private String nomeProduto;
    private String ramo;

    public Certificado(Integer apoliceAutoId, String cdProdutoRet, String chaveNegocio,
            TipoDescricaoSituacao descricaoSituacao, String nomeProduto, String ramo) {
        this.apoliceAutoId = apoliceAutoId;
        this.cdProdutoRet = cdProdutoRet;
        this.chaveNegocio = chaveNegocio;
        this.descricaoSituacao = descricaoSituacao;
        this.nomeProduto = nomeProduto;
        this.ramo = ramo;
    }
}
