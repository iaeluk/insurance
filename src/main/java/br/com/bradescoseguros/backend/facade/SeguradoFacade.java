package br.com.bradescoseguros.backend.facade;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.bradescoseguros.backend.dao.EnderecoDAO;
import br.com.bradescoseguros.backend.dao.SeguradoDAO;
import br.com.bradescoseguros.backend.dao.TelefoneDAO;
import br.com.bradescoseguros.backend.entity.Endereco;
import br.com.bradescoseguros.backend.entity.Segurado;
import br.com.bradescoseguros.backend.entity.Telefone;

@Component
public class SeguradoFacade {

    @Autowired
    private SeguradoDAO seguradoDAO;
    @Autowired
    private EnderecoDAO enderecoDAO;
    @Autowired
    private TelefoneDAO telefoneDAO;

    public SeguradoFacade() {
    }


    public void salvarSegurado(Segurado segurado, Endereco endereco, Telefone telefone) {
            seguradoDAO.salvar(segurado);
            ArrayList<Segurado> listaDeSegurados = seguradoDAO.buscar();
            Segurado ultimoSegurado = listaDeSegurados.get(listaDeSegurados.size() - 1);

            enderecoDAO.salvar(endereco, ultimoSegurado);
            telefoneDAO.salvar(telefone, ultimoSegurado);
            buscarSegurado();
    }

    public ArrayList<Segurado> buscarSegurado() {
        return seguradoDAO.buscar();
    }

    public Segurado buscarSeguradoPorId(Integer id) {
        telefoneDAO.buscarPorId(id);
        enderecoDAO.buscarPorId(id);
        return seguradoDAO.buscarPorId(id);
    }

    public void editarSegurado(Segurado segurado, Telefone telefone, Endereco endereco, Integer id) {
        seguradoDAO.editar(segurado, id);
        telefoneDAO.editar(telefone, id);
        enderecoDAO.editar(endereco, id);
    }

    public Telefone buscarTelefonePorId(Integer id) {
        return telefoneDAO.buscarPorId(id);
    }

    public Endereco buscarEnderecoPorId(Integer id) {
        return enderecoDAO.buscarPorId(id);
    }

    public void deletarSegurado(Integer id) {
        enderecoDAO.deletar(id);
        telefoneDAO.deletar(id);
        seguradoDAO.deletar(id);
    }

}
