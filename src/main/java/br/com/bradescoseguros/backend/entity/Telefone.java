package br.com.bradescoseguros.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Telefone {

    private Integer id;
    private Integer seguradoId;
    private String descricaoTipoTelefone;
    private String ddd;
    private String numero;
    private String ramal;

    public Telefone(Integer seguradoId, String descricaoTipoTelefone, String ddd, String numero, String ramal) {
        this.seguradoId = seguradoId;
        this.descricaoTipoTelefone = descricaoTipoTelefone;
        this.ddd = ddd;
        this.numero = numero;
        this.ramal = ramal;
    }
}
