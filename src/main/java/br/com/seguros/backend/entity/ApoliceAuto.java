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
}
