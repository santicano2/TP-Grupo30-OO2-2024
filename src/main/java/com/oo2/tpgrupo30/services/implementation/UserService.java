package com.oo2.tpgrupo30.services.implementation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.oo2.tpgrupo30.entities.UsuarioRoles;
import com.oo2.tpgrupo30.repositories.IUserRepository;

@Service("userService")
public class UserService implements UserDetailsService {

	private IUserRepository userRepository;

	public UserService(IUserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String nombre) throws UsernameNotFoundException {
		com.oo2.tpgrupo30.entities.Usuario usuario = userRepository.findByUsernameAndFetchUserRolesEagerly(nombre);
		return buildUser(usuario, buildGrantedAuthorities(usuario.getUsuarioRoles()));
	}

	private User buildUser(com.oo2.tpgrupo30.entities.Usuario usuario, List<GrantedAuthority> grantedAuthorities) {
		return new User(usuario.getNombre(), usuario.getClave(), true, true, true, true, grantedAuthorities);
	}

	private List<GrantedAuthority> buildGrantedAuthorities(Set<UsuarioRoles> usuarioRoles) {
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		for (UsuarioRoles usuarioRol : usuarioRoles) {
			grantedAuthorities.add(new SimpleGrantedAuthority(usuarioRol.getRol()));
		}
		return new ArrayList<>(grantedAuthorities);
	}
}