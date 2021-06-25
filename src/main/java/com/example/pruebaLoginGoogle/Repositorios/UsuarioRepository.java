package com.example.pruebaLoginGoogle.Repositorios;

import com.example.pruebaLoginGoogle.entidades.AuthenticationProvider;
import com.example.pruebaLoginGoogle.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Usuario findByEmail(String email);


}
