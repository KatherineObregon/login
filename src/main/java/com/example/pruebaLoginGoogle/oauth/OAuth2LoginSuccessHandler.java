package com.example.pruebaLoginGoogle.oauth;

import com.example.pruebaLoginGoogle.Repositorios.UsuarioRepository;
import com.example.pruebaLoginGoogle.entidades.AuthenticationProvider;
import com.example.pruebaLoginGoogle.entidades.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Autowired
    UsuarioRepository usuarioRepository;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();
        Map<String, Object> atributis = oAuth2User.getAttributes();
        String email = oAuth2User.getEmail();
        System.out.println("Email del usuario : "+ email);
        String nombre = oAuth2User.getName();

        Usuario usuario=usuarioRepository.findByEmail(email);
        if(usuario == null){
            //register
            createNewUserAfterOAuthLoginSuccess(email, nombre, AuthenticationProvider.GOOGLE);
        }else{
            //update

            updateNewUserAfterOAuthLoginSuccess(usuario, nombre,AuthenticationProvider.LOCAL);
        }


        super.onAuthenticationSuccess(request, response, authentication);
    }

    public void createNewUserAfterOAuthLoginSuccess(String email, String name, AuthenticationProvider provider){

        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setName(name);
        usuario.setAuthProvider(provider);
        usuarioRepository.save(usuario);

    }
    public void updateNewUserAfterOAuthLoginSuccess(Usuario usuario, String name, AuthenticationProvider provider){

        usuario.setName(name);
        usuario.setAuthProvider(provider);
        usuarioRepository.save(usuario);

    }
}
