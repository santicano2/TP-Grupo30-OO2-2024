package com.oo2.tpgrupo30.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.oo2.tpgrupo30.entities.Usuario;

@Repository("userRepository")
public interface IUserRepository extends JpaRepository<Usuario, Serializable> {
	@Query("SELECT u FROM Usuario u JOIN FETCH u.usuarioRoles WHERE u.nombre = (:nombre)")
	public abstract Usuario findByUsernameAndFetchUserRolesEagerly(@Param("nombre") String nombre);
}
