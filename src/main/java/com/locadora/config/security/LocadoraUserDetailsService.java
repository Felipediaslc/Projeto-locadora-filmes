package com.locadora.config.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.locadora.infra.usuario.Usuario;
import com.locadora.infra.usuario.UsuarioRepository;

@Service
public class LocadoraUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Optional<Usuario> usuarioSalvo = this.usuarioRepository.findByLogin(login);
		Usuario usuario = usuarioSalvo.orElseThrow(() -> new UsernameNotFoundException("Login ou senha incorretos"));
		return new User(login,usuario.getSenha(),getPerfil(usuario));
	}
	
	private Collection<? extends GrantedAuthority> getPerfil(Usuario usuario){
		Set<SimpleGrantedAuthority> authorities  = new HashSet<>();
		usuario.getPerfil().getPermissoes().forEach(p -> authorities.add(new SimpleGrantedAuthority(p.getNome().toUpperCase())));
		return authorities;
	}
}
