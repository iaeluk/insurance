package br.com.bradescoseguros.backend.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import br.com.bradescoseguros.backend.connection.SingleConnection;
import br.com.bradescoseguros.backend.entity.Telefone;
import br.com.bradescoseguros.backend.entity.Segurado;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Repository
public class TelefoneDAO implements CrudInterface<Telefone, Segurado> {

    private static final Logger logger = LogManager.getLogger(TelefoneDAO.class);

    private Connection connection;

    public TelefoneDAO() {
        connection = SingleConnection.getConnection();
    }

    @Override
    public void salvar(Telefone a) {
        // TODO Auto-generated method stub

    }

    @Override
    public void salvar(Telefone telefone, Segurado segurado) {
        String sql = "INSERT INTO telefone (segurado_id, descricao_tipo_telefone, ddd, numero, ramal) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, segurado.getId());
            ps.setString(2, telefone.getDescricaoTipoTelefone());
            ps.setString(3, telefone.getDdd());
            ps.setString(4, telefone.getNumero());
            ps.setString(5, telefone.getRamal());

            ps.executeUpdate();
            logger.info("Telefone salvo com sucesso.");

        } catch (SQLException e) {
            logger.error("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            logger.error(e.getStackTrace());
        }

    }

    @Override
    public ArrayList<Telefone> buscar() {
        ArrayList<Telefone> retorno = new ArrayList<Telefone>();

        try {
            String sql = "SELECT * FROM endereco";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet resultado = ps.executeQuery();
            while (resultado.next()) {
                Telefone telefone = new Telefone(
                        resultado.getInt("id"),
                        resultado.getInt("segurado_id"),
                        resultado.getString("descricao_tipo_telefone"),
                        resultado.getString("ddd"),
                        resultado.getString("numero"),
                        resultado.getString("ramal"));

                retorno.add(telefone);
            }

        } catch (SQLException e) {
            logger.error("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            logger.error(e.getStackTrace());
        }

        return retorno;
    }

    @Override
    public Telefone buscarPorId(Integer id) {
        try {
            String sql = "SELECT * FROM telefone WHERE segurado_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet resultado = ps.executeQuery();

            while (resultado.next()) {
                Telefone telefone = new Telefone(
                        resultado.getInt("id"),
                        resultado.getInt("segurado_id"),
                        resultado.getString("descricao_tipo_telefone"),
                        resultado.getString("ddd"),
                        resultado.getString("numero"),
                        resultado.getString("ramal"));

                return telefone;
            }

        } catch (SQLException e) {
            logger.error("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            logger.error(e.getStackTrace());
        }

        return null;
    }

    @Override
    public Telefone editar(Telefone telefone, Integer id) {
        try {
            String sql = "UPDATE telefone SET descricao_tipo_telefone=?, ddd=?, numero=?, ramal=? WHERE segurado_id=?";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, telefone.getDescricaoTipoTelefone());
            ps.setString(2, telefone.getDdd());
            ps.setString(3, telefone.getNumero());
            ps.setString(4, telefone.getRamal());
            ps.setInt(5, id);

            ResultSet resultado = ps.executeQuery();

            while (resultado.next()) {
                Telefone telefoneAtualizado = new Telefone(
                        resultado.getInt("id"),
                        resultado.getInt("segurado_id"),
                        resultado.getString("descricao_tipo_telefone"),
                        resultado.getString("ddd"),
                        resultado.getString("numero"),
                        resultado.getString("ramal"));

                return telefoneAtualizado;
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
        String sql = "DELETE FROM telefone WHERE segurado_id = ?";

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
