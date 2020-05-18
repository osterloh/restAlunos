package br.com.osterloh.projeto.projetoalunos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.osterloh.projeto.projetoalunos.dao.FuncionarioRepository;
import br.com.osterloh.projeto.projetoalunos.dao.PessoaRepository;
import br.com.osterloh.projeto.projetoalunos.model.Funcionario;
import br.com.osterloh.projeto.projetoalunos.model.Pessoa;

@SpringBootApplication
@RestController
@CrossOrigin(origins = "*")
public class ProjetoAlunosApplication {
	
	@Autowired
	private PessoaRepository repository;
	
	@PostMapping("/register")
	public String register(@RequestBody Pessoa pessoa) {
		repository.save(pessoa);
		return " cadastrado com sucesso!!! ";
	}
	
	@GetMapping("/getAllPessoa")
	public List<Pessoa> findAllPessoa(){
		return repository.findAll();
	}
	
	@GetMapping("findPessoa/{email}")
	public List<Pessoa> findPessoa(@PathVariable String email){
		return repository.findByEmail(email);
	}
	
	@GetMapping("/search/{id}")
	public ResponseEntity findById(@PathVariable int id) {
		return repository.findById(id)
				.map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PutMapping("/updatePessoa/{id}")
	public ResponseEntity update(@PathVariable int id, @RequestBody Pessoa pessoa) {
		return repository.findById(id).map(record -> {
			record.setNome(pessoa.getNome());
			record.setEmail(pessoa.getEmail());
			record.setCpf(pessoa.getCpf());
			record.setIdade(pessoa.getIdade());
			Pessoa update = repository.save(record);
			return ResponseEntity.ok().body(update);
		}).orElse(ResponseEntity.notFound().build());
		
	}
	
	@DeleteMapping("/cancel/{id}")
	public List<Pessoa> cancelRegistration(@PathVariable int id){
		repository.deleteById(id);
		return repository.findAll();
	}
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@PostMapping("/registerFunc")
	public String registerFunc(@RequestBody Funcionario funcionario) {
		funcionarioRepository.save(funcionario);
		return " cadastrado com sucesso!!! ";
	}
	
	@GetMapping("/getAllFuncionario")
	public List<Funcionario> findAllFuncionario(){
		return funcionarioRepository.findAll();
	}
	
	@GetMapping("findFuncionario/{funcao}")
	public List<Funcionario> findFuncionario(@PathVariable String funcao){
		return funcionarioRepository.findByFuncao(funcao);
	}
	
	@GetMapping("/searchFunc/{id}")
	public ResponseEntity findByIdFunc(@PathVariable int id) {
		return funcionarioRepository.findById(id)
				.map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PutMapping("/updateFuncionario/{id}")
	public ResponseEntity update(@PathVariable int id, @RequestBody Funcionario funcionario) {
		return funcionarioRepository.findById(id).map(record -> {
			record.setNome(funcionario.getNome());
			record.setEmail(funcionario.getEmail());
			record.setCpf(funcionario.getCpf());
			record.setIdade(funcionario.getIdade());
			record.setFuncao(funcionario.getFuncao());
			record.setSalario(funcionario.getSalario());
			Funcionario update = repository.save(record);
			return ResponseEntity.ok().body(update);
		}).orElse(ResponseEntity.notFound().build());
		
	}
	
	@DeleteMapping("/deleteFunc/{id}")
	public List<Funcionario> deleteFuncionario(@PathVariable int id){
		funcionarioRepository.deleteById(id);
		return funcionarioRepository.findAll();
	}

	public static void main(String[] args) {
		SpringApplication.run(ProjetoAlunosApplication.class, args);
	}

}
