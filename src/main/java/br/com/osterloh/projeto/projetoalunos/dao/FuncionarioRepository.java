package br.com.osterloh.projeto.projetoalunos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.osterloh.projeto.projetoalunos.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {
	
	List<Funcionario> findByFuncao(String funcao);

}
