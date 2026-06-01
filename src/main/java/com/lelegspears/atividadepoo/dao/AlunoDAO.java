package com.lelegspears.atividadepoo.dao;

import com.lelegspears.atividadepoo.model.Aluno;
import com.lelegspears.atividadepoo.model.Curso;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.DBConnector;
import util.DBException;

public class AlunoDAO {

    public AlunoDAO() {
    }

    public void insert(Aluno obj) {

        try (
            Connection conn = DBConnector.getConexao();

            PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO aluno(nome, idade, curso_id) VALUES (?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
            )
        ) {

            ps.setString(1, obj.getNome());
            ps.setInt(2, obj.getIdade());
            ps.setLong(3, obj.getCurso().getId());

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {

                try (ResultSet rs = ps.getGeneratedKeys()) {

                    if (rs.next()) {
                        obj.setId(rs.getLong(1));
                    }
                }
            }

        } catch (SQLException e) {

            throw new DBException(e.getMessage());
        }
    }

    public void update(Aluno obj, Long id) {

        try (
            Connection conn = DBConnector.getConexao();

            PreparedStatement ps = conn.prepareStatement(
                "UPDATE aluno SET nome = ?, idade = ?, curso_id = ? WHERE id = ?"
            )
        ) {

            ps.setString(1, obj.getNome());
            ps.setInt(2, obj.getIdade());
            ps.setLong(3, obj.getCurso().getId());
            ps.setLong(4, id);

            ps.executeUpdate();

        } catch (SQLException e) {

            throw new DBException(e.getMessage());
        }
    }

    public void deleteById(Long id) {

        try (
            Connection conn = DBConnector.getConexao();

            PreparedStatement ps = conn.prepareStatement(
                "DELETE FROM aluno WHERE id = ?"
            )
        ) {

            ps.setLong(1, id);

            ps.executeUpdate();

        } catch (SQLException e) {

            throw new DBException(e.getMessage());
        }
    }

    public List<Aluno> findAll() {

        List<Aluno> alunos = new ArrayList<>();

        try (
            Connection conn = DBConnector.getConexao();

            PreparedStatement ps = conn.prepareStatement(
                """
                SELECT
                    a.id,
                    a.nome,
                    a.idade,
                    c.id AS curso_id,
                    c.nome AS curso_nome,
                    c.cargaHoraria
                FROM aluno a
                JOIN curso c
                ON a.curso_id = c.id
                """
            );

            ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                Curso curso = new Curso(
                    rs.getLong("curso_id"),
                    rs.getString("curso_nome"),
                    rs.getDouble("cargaHoraria")
                );

                Aluno aluno = new Aluno(
                    rs.getLong("id"),
                    rs.getString("nome"),
                    rs.getInt("idade"),
                    curso
                );

                alunos.add(aluno);
            }

        } catch (SQLException e) {

            throw new DBException(e.getMessage());
        }

        return alunos;
    }
}