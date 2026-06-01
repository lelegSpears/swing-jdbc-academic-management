/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lelegspears.atividadepoo.dao;

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

/**
 *
 * @author leleg
 */
public class CursoDAO {
    public CursoDAO(){
    }
    

    public void insert(Curso obj) {
		try (Connection conn = DBConnector.getConexao();
                        PreparedStatement ps = conn.prepareStatement(
				"insert into curso(nome, cargaHoraria) values (?, ?)",
				Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, obj.getNome());
			ps.setDouble(2, obj.getCargaHoraria());
			int la = ps.executeUpdate();
			if (la > 0) {
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
    
    public void update(Curso obj, Long id){
        try (Connection conn = DBConnector.getConexao();
                PreparedStatement ps = conn.prepareStatement(
				"UPDATE curso SET nome = ?, cargaHoraria = ? WHERE id = ?")) {
			ps.setString(1, obj.getNome());
			ps.setDouble(2, obj.getCargaHoraria());
                        ps.setLong(3, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DBException(e.getMessage());
		}
    }
    
    public void deleteById(Long id) {
		try (Connection conn = DBConnector.getConexao();
                        PreparedStatement ps = conn.prepareStatement("Delete from curso where id = ?")) {
			ps.setLong(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DBException(e.getMessage());
		}

	}
    public List<Curso> findAll() {

    List<Curso> cursos = new ArrayList<>();

    try (
        Connection conn = DBConnector.getConexao();

        PreparedStatement ps = conn.prepareStatement(
            "SELECT * FROM curso"
        );

        ResultSet rs = ps.executeQuery()
    ) {

        while (rs.next()) {

            Curso curso = new Curso(
                rs.getLong("id"),
                rs.getString("nome"),
                rs.getDouble("cargaHoraria")
            );

            cursos.add(curso);
        }

    } catch (SQLException e) {

        throw new DBException(e.getMessage());
    }

    return cursos;
}
    
}
