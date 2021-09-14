package com.blogpessoalcinthia.blog.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogpessoalcinthia.blog.Model.TemaModel;


@Repository
public interface TemaRepository extends JpaRepository<TemaModel, Long> {

}
