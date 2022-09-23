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

@Repository
public class TelefoneDAO implements CrudInterface<Telefone, Segurado> {

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
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, segurado.getId());
            statement.setString(2, telefone.getDescricaoTipoTelefone());
            statement.setString(3, telefone.getDdd());
            statement.setString(4, telefone.getNumero());
            statement.setString(5, telefone.getRamal());

            statement.executeUpdate();

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public ArrayList<Telefone> buscar() {
        ArrayList<Telefone> retorno = new ArrayList<Telefone>();

        try {
            String sql = "SELECT * FROM endereco";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultado = statement.executeQuery();
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

        } catch (Exception e) {
            e.printStackTrace();
        }

        return retorno;
    }

    @Override
    public Telefone buscarPorId(Integer id) {
        try {
            String sql = "SELECT * FROM telefone WHERE segurado_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultado = statement.executeQuery();

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

        } catch (Exception e) {
            e.printStackTrace();
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

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deletar(Integer id) {
        String sql = "DELETE FROM telefone WHERE segurado_id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}