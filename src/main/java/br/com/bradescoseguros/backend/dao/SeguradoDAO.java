package br.com.bradescoseguros.backend.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import br.com.bradescoseguros.backend.connection.SingleConnection;
import br.com.bradescoseguros.backend.entity.Segurado;
import br.com.bradescoseguros.backend.entity.TipoPessoa;

@Repository
public class SeguradoDAO implements CrudInterface<Segurado, Segurado> {

    private Connection connection;

    public SeguradoDAO() {
        connection = SingleConnection.getConnection();
    }

    @Override
    public void salvar(Segurado segurado) {
        String sql = "INSERT INTO segurado (nome, numero_documento, tipo_pessoa, data_nascimento, email) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, segurado.getNome());
            statement.setString(2, segurado.getNumeroDocumento());
            statement.setString(3, String.valueOf(segurado.getTipoPessoa()));
            statement.setDate(4, new Date(segurado.getDataNascimento().getTime()));
            statement.setString(5, segurado.getEmail());

            statement.executeUpdate();

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void salvar(Segurado a, Segurado b) {
    }

    @Override
    public ArrayList<Segurado> buscar() {
        ArrayList<Segurado> listaDeSegurados = new ArrayList<Segurado>();

        try {
            String sql = "SELECT * FROM segurado";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultado = statement.executeQuery();
            while (resultado.next()) {
                Segurado segurado = new Segurado(
                        resultado.getInt("id"),
                        resultado.getString("nome"),
                        resultado.getString("numero_documento"),
                        TipoPessoa.valueOf(resultado.getString("tipo_pessoa")),
                        resultado.getDate("data_nascimento"),
                        resultado.getString("email"));

                listaDeSegurados.add(segurado);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaDeSegurados;
    }

    @Override
    public Segurado buscarPorId(Integer id) {
        try {
            String sql = "SELECT * FROM segurado WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultado = statement.executeQuery();

            while (resultado.next()) {
                Segurado segurado = new Segurado(
                        resultado.getInt("id"),
                        resultado.getString("nome"),
                        resultado.getString("numero_documento"),
                        TipoPessoa.valueOf(resultado.getString("tipo_pessoa")),
                        resultado.getDate("data_nascimento"),
                        resultado.getString("email"));

                return segurado;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Segurado editar(Segurado segurado, Integer id) {
        try {
            String sql = "UPDATE segurado SET nome=?, numero_documento=?, tipo_pessoa=?, data_nascimento=?, email=? WHERE id=?";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, segurado.getNome());
            ps.setString(2, segurado.getNumeroDocumento());
            ps.setString(3, segurado.getTipoPessoa().toString());
            ps.setDate(4, new Date(segurado.getDataNascimento().getTime()));
            ps.setString(5, segurado.getEmail());
            ps.setInt(6, id);

            ResultSet resultado = ps.executeQuery();

            while (resultado.next()) {
                Segurado seguradoAtualizado = new Segurado(
                        resultado.getInt("id"),
                        resultado.getString("nome"),
                        resultado.getString("numero_documento"),
                        TipoPessoa.valueOf(resultado.getString("tipo_pessoa")),
                        resultado.getDate("data_nascimento"),
                        resultado.getString("email"));

                return seguradoAtualizado;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public void deletar(Integer id) {
        String sql = "DELETE FROM segurado WHERE id=?";

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
