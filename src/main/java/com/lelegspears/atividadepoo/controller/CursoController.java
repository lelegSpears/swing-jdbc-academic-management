package com.lelegspears.atividadepoo.controller;

import com.lelegspears.atividadepoo.dao.CursoDAO;
import com.lelegspears.atividadepoo.model.Curso;
import java.util.List;

public class CursoController {

    private final CursoDAO dao;

    public CursoController() {
        dao = new CursoDAO();
    }

    public void salvar(Curso curso) {

        validar(curso);

        dao.insert(curso);
    }

    public void atualizar(Curso curso) {

        validar(curso);

        dao.update(curso, curso.getId());
    }

    public void deletar(Long id) {

        dao.deleteById(id);
    }

    public List<Curso> listar() {

        return dao.findAll();
    }

    private void validar(Curso curso) {

        if (curso.getNome() == null ||
            curso.getNome().isBlank()) {

            throw new RuntimeException(
                "Nome obrigatório"
            );
        }

        if (curso.getCargaHoraria() <= 0) {

            throw new RuntimeException(
                "Carga horária inválida"
            );
        }
    }
}