package br.com.seguros.backend.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.seguros.backend.connection.SingleConnection;
import br.com.seguros.backend.entity.Endereco;
import br.com.seguros.backend.entity.Segurado;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Repository
public class EnderecoDAO implements CrudInterface<Endereco, Segurado> {

    private static final Logger logger = LogManager.getLogger(EnderecoDAO.class);

    private Connection connection;

    @Autowired
    private DataSource dataSource;

    public EnderecoDAO() {
        try {
            connection = dataSource.getConnection();
        } catch (Exception e) {
            // TODO: handle exception
            logger.error(e.getStackTrace());
        }
    }

    @Override
    public void salvar(Endereco a) {
        // TODO Auto-generated method stub

    }

    @Override
    public void salvar(Endereco endereco, Segurado segurado) {
        String sql = "INSERT INTO endereco (segurado_id, cep, complemento, descricao_tipo_logradouro, logradouro, numero, tipo_logradouro, cidade, uf, bairro) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, segurado.getId());
            ps.setString(2, endereco.getCep());
            ps.setString(3, endereco.getComplemento());
            ps.setString(4, endereco.getDescricaoTipoLogradouro());
            ps.setString(5, endereco.getLogradouro());
            ps.setString(6, endereco.getNumero());
            ps.setString(7, endereco.getTipoLogradouro());
            ps.setString(8, endereco.getCidade());
            ps.setString(9, endereco.getUf());
            ps.setString(10, endereco.getBairro());

            ps.executeUpdate();
            logger.info("Endereço salvo com sucesso.");

        } catch (SQLException e) {
            logger.error("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            logger.error(e.getStackTrace());
        }
    }

    @Override
    public ArrayList<Endereco> buscar() {
        ArrayList<Endereco> retorno = new ArrayList<Endereco>();

        try {
            String sql = "SELECT * FROM endereco";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet resultado = ps.executeQuery();
            while (resultado.next()) {
                Endereco endereco = new Endereco(
                        resultado.getInt("id"),
                        resultado.getInt("segurado_id"),
                        resultado.getString("cep"),
                        resultado.getString("complemento"),
                        resultado.getString("descricao_tipo_logradouro"),
                        resultado.getString("logradouro"),
                        resultado.getString("numero"),
                        resultado.getString("tipo_logradouro"),
                        resultado.getString("cidade"),
                        resultado.getString("uf"),
                        resultado.getString("bairro"));

                retorno.add(endereco);
            }

        } catch (SQLException e) {
            logger.error("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            logger.error(e.getStackTrace());
        }

        return retorno;
    }

    @Override
    public Endereco buscarPorId(Integer id) {
        try {
            String sql = "SELECT * FROM endereco WHERE segurado_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet resultado = ps.executeQuery();

            while (resultado.next()) {
                Endereco endereco = new Endereco(
                        resultado.getInt("id"),
                        resultado.getInt("segurado_id"),
                        resultado.getString("cep"),
                        resultado.getString("complemento"),
                        resultado.getString("descricao_tipo_logradouro"),
                        resultado.getString("logradouro"),
                        resultado.getString("numero"),
                        resultado.getString("tipo_logradouro"),
                        resultado.getString("cidade"),
                        resultado.getString("uf"),
                        resultado.getString("bairro"));

                return endereco;
            }
        } catch (SQLException e) {
            logger.error("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            logger.error(e.getStackTrace());
        }

        return null;
    }

    @Override
    public Endereco editar(Endereco endereco, Integer id) {
        try {
            String sql = "UPDATE endereco SET cep=?, complemento=?, descricao_tipo_logradouro=?, logradouro=?, numero=?, tipo_logradouro=?, cidade=?, uf=?, bairro=? WHERE segurado_id=?";
            

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, endereco.getCep());
            ps.setString(2, endereco.getComplemento());
            ps.setString(3, endereco.getDescricaoTipoLogradouro());
            ps.setString(4, endereco.getLogradouro());
            ps.setString(5, endereco.getNumero());
            ps.setString(6, endereco.getTipoLogradouro());
            ps.setString(7, endereco.getCidade());
            ps.setString(8, endereco.getUf());
            ps.setString(9, endereco.getBairro());
            ps.setInt(10, id);

            ResultSet resultado = ps.executeQuery();

            while (resultado.next()) {
                Endereco enderecoAtualizado = new Endereco(
                        resultado.getInt("id"),
                        resultado.getInt("segurado_id"),
                        resultado.getString("cep"),
                        resultado.getString("complemento"),
                        resultado.getString("descricao_tipo_logradouro"),
                        resultado.getString("logradouro"),
                        resultado.getString("numero"),
                        resultado.getString("tipo_logradouro"),
                        resultado.getString("cidade"),
                        resultado.getString("uf"),
                        resultado.getString("bairro"));

                return enderecoAtualizado;
            }

        } catch (SQLException e) {
            logger.error("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            logger.error(e.getStackTrace());
        }

        return null;
    }

    @Override
    public void deletar(Integer id) {
        String sql = "DELETE FROM endereco WHERE segurado_id = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            logger.error(e.getStackTrace());
        }
    }

}
