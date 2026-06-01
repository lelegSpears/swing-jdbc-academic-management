package com.lelegspears.atividadepoo.controller;

import com.lelegspears.atividadepoo.dao.AlunoDAO;
import com.lelegspears.atividadepoo.model.Aluno;
import java.util.List;

public class AlunoController {

    private final AlunoDAO dao;

    public AlunoController() {
        dao = new AlunoDAO();
    }

    public void salvar(Aluno aluno) {

        validar(aluno);

        dao.insert(aluno);
    }

    public void atualizar(Aluno aluno) {

        validar(aluno);

        dao.update(aluno, aluno.getId());
    }

    public void deletar(Long id) {

        dao.deleteById(id);
    }

    public List<Aluno> listar() {

        return dao.findAll();
    }

    private void validar(Aluno aluno) {

        if (aluno.getNome() == null ||
            aluno.getNome().isBlank()) {

            throw new RuntimeException(
                "Nome obrigatório"
            );
        }

        if (aluno.getIdade() <= 0) {

            throw new RuntimeException(
                "Idade inválida"
            );
        }

        if (aluno.getCurso() == null) {

            throw new RuntimeException(
                "Curso obrigatório"
            );
        }
    }
}
