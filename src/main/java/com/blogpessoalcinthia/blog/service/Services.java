package com.blogpessoalcinthia.blog.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.blogpessoalcinthia.blog.Model.UsuarioModel;
import com.blogpessoalcinthia.blog.Model.utilite.UsuarioLogin;
import com.blogpessoalcinthia.blog.repositorio.UsuarioRepository;

@Service
public class Services {

private @Autowired UsuarioRepository repository;
	
    public Optional<Object> cadastrarUsuario(UsuarioModel novoUsuario){
    return repository.findByEmail(novoUsuario.getEmail()).map(usuarioExistente ->{
        return Optional.empty();
    }).orElseGet(() ->{
        BCryptPasswordEncoder enconder = new BCryptPasswordEncoder();
        String result = enconder.encode(novoUsuario.getSenha());
        novoUsuario.setSenha(result);
        return Optional.ofNullable(repository.save(novoUsuario));
    });
}
	public Optional<?> autenticador (UsuarioLogin usuarioAutenticar){
		
		return repository.findByEmail(usuarioAutenticar.getEmail()).map(usuarioExistente -> {
			BCryptPasswordEncoder enconder = new BCryptPasswordEncoder();
			
			if (enconder.matches(usuarioAutenticar.getSenha(), usuarioExistente.getSenha())) {
				
				String estruturaBasic = usuarioAutenticar.getEmail() + ":" + usuarioAutenticar.getSenha();
				byte[] autorizacaoBase64 = Base64.encodeBase64(estruturaBasic.getBytes(Charset.forName("Us-AsCII")));
				String autorizacaoHeader = "Basic " + new String(autorizacaoBase64);
				
				usuarioAutenticar.setToken(autorizacaoHeader);
				usuarioAutenticar.setId(usuarioExistente.getIdUsuario());
				usuarioAutenticar.setNome(usuarioExistente.getNome());
				usuarioAutenticar.setSenha(usuarioExistente.getSenha());
				return Optional.ofNullable(usuarioAutenticar);
			}
			else {
				return Optional.empty(); // Senha errada
			}
		}).orElseGet(() -> {
			return Optional.empty(); //Email não existe
			});
		}
	}