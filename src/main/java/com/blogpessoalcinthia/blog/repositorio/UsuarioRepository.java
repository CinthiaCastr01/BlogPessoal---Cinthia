package com.blogpessoalcinthia.blog.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogpessoalcinthia.blog.Model.UsuarioModel;


@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {

	List<UsuarioModel> findAllByNomeContainingIgnoreCase(String nome);
	
	Optional<UsuarioModel> findByEmail(String email);
}
