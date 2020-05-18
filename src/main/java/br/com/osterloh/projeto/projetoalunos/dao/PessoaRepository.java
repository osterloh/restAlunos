package br.com.osterloh.projeto.projetoalunos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.osterloh.projeto.projetoalunos.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer>{

	List<Pessoa> findByEmail(String email);
	
}
