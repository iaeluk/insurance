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
public class Endereco implements Serializable {

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
}
