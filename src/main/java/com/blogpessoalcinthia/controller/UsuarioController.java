package com.blogpessoalcinthia.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogpessoalcinthia.blog.Model.UsuarioModel;
import com.blogpessoalcinthia.blog.repositorio.UsuarioRepository;




@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	private @Autowired UsuarioRepository repository;
	
	//endpoints ou crud
	
	/*
	@GetMapping("/todos")
	public ResponseEntity<List<UsuarioModel>> pegarTodes(){
	return ResponseEntity.ok(repository.findAll());
		)*/
	@GetMapping("/todos")
	public ResponseEntity<List<UsuarioModel>> pegarTodos(){
		List<UsuarioModel> objetoLista = repository.findAll();
		if(objetoLista.isEmpty()) {
			return ResponseEntity.status(204).build();
		}else {
			return ResponseEntity.status(200).body(objetoLista);
		}
	}
	@PostMapping("/salvar1")
	public ResponseEntity<UsuarioModel> cadastrarUsuario1(@Valid @RequestBody UsuarioModel novoUsuario){
		return ResponseEntity.status(201).body(repository.save(novoUsuario));
	}
	
	/*@PostMapping("/salvar2")
	public ResponseEntity<UsuarioModel> cadastrarUsuario2(@Valid @RequestBody UsuarioModel novoUsuario){
		return ResponseEntity<UsuarioModel>if()
	}*/
	@PutMapping("/atualizar")
	public ResponseEntity<UsuarioModel> atualizarUsuario (UsuarioModel usuarioParaAtualizar){
		return ResponseEntity.status(201).body(repository.save(usuarioParaAtualizar));
	}
	@GetMapping("/{idUsuario}")
	public ResponseEntity<UsuarioModel> buscarPorId(@PathVariable (value = "idUsuario") Long idUsuario){
		Optional<UsuarioModel> objetoUsuario = repository.findById(idUsuario);
		if(objetoUsuario.isPresent()) {
			return ResponseEntity.status(200).body(objetoUsuario.get());
		}else {
			return ResponseEntity.status(204).build();
		}
	}
		@GetMapping("/nome/{nome_usuario}")
		public ResponseEntity<List<UsuarioModel>> buscarPorNome (@PathVariable(value = "nome_usuario") String nome){
			List<UsuarioModel> objetoNomes = repository.findAllByNomeContainingIgnoreCase(nome);
			if(objetoNomes.isEmpty()) {
				return ResponseEntity.status(204).build();
			}else {
				return ResponseEntity.status(200).body(objetoNomes);
			}
		}
			@DeleteMapping("/deletar/{id_usuario}")
			public void deletarUsuario (@PathVariable(value = "id_usuario") Long idUsuario) {
				repository.deleteById(idUsuario);
	}
}
