package Security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blogpessoalcinthia.blog.Model.UsuarioModel;
import com.blogpessoalcinthia.blog.repositorio.UsuarioRepository;

@Service
public class UserDetailsServiceImplements implements UserDetailsService {

	@Autowired
    private UsuarioRepository repository;
	
	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UsuarioModel> objOptional = repository.findByEmail(username);

        if(objOptional.isPresent()){
            return new UserDetailsImplements(objOptional.get());
        }else {
            throw new UsernameNotFoundException(username + "Não existe");
        }
    }

}
