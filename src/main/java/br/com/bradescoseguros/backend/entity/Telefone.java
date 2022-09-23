package br.com.bradescoseguros.backend.entity;

public class Telefone {

    private Integer id;
    private Integer seguradoId;
    private String descricaoTipoTelefone;
    private String ddd;
    private String numero;
    private String ramal;

    public Telefone() {
    }

    public Telefone(Integer seguradoId, String descricaoTipoTelefone, String ddd, String numero, String ramal) {
        this.seguradoId = seguradoId;
        this.descricaoTipoTelefone = descricaoTipoTelefone;
        this.ddd = ddd;
        this.numero = numero;
        this.ramal = ramal;
    }

    public Telefone(Integer id, Integer seguradoId, String descricaoTipoTelefone, String ddd, String numero,
            String ramal) {
        this.id = id;
        this.seguradoId = seguradoId;
        this.descricaoTipoTelefone = descricaoTipoTelefone;
        this.ddd = ddd;
        this.numero = numero;
        this.ramal = ramal;
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

    public String getDescricaoTipoTelefone() {
        return descricaoTipoTelefone;
    }

    public void setDescricaoTipoTelefone(String descricaoTipoTelefone) {
        this.descricaoTipoTelefone = descricaoTipoTelefone;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getRamal() {
        return ramal;
    }

    public void setRamal(String ramal) {
        this.ramal = ramal;
    }

}
