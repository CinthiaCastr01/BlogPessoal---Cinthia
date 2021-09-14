package com.blogpessoalcinthia.blog.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogpessoalcinthia.blog.Model.PostagemModel;


@Repository
public interface PostagemRepository extends JpaRepository<PostagemModel, Long> {

List<PostagemModel> findAllByTituloContainingIgnoreCase(String titulo);
	
	Optional<PostagemModel> findByDescricao (String descricao);
}
