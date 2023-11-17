package utfpr.trabalho.api.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import utfpr.trabalho.api.model.user.UsersModel;
import utfpr.trabalho.api.repository.UsersRepository;

import java.io.IOException;
import java.util.Optional;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    // o filtro acontece 1 vez a cada requisição -> tipo middleware

    @Autowired
    TokenService tokenService;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private UsersRepository usersRepository ;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);
        if(token != null) {
            try {
                String chave = tokenService.validateToken(token);
                String[] login = chave.split(":");
                String sLogin = login[1];
                String userid = login[0];

                UserDetails userDetails = userDetailsService.loadUserByUsername(sLogin);

                Optional<UsersModel> user = usersRepository.findById(Integer.valueOf(userid));
                UsersModel usersModel = user.get();

                //UsernamePasswordAuthenticationToken authentication =
                //       new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(usersModel, null, usersModel.getAuthorities());

                System.out.println("passou aqui + " + authentication);
                SecurityContextHolder.getContext().setAuthentication(authentication);

            } catch (Exception e) {
                // Lidar com exceções durante a validação do token (por exemplo, token expirado)
                SecurityContextHolder.clearContext();
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token inválido");
                return;
            }
           /* var login = tokenService.validateToken(token);
            UserDetails user = usersRepository.findByLogin(login);
            System.out.println("user em do filter internal "+user.getUsername());

            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication); */

        }
        else {
            filterChain.doFilter(request, response); // chama o "next" , no caso  UsernamePasswordAuthenticationFilter.class
            // e se como o filter está sendo chamado antes, e quando chegar no next não vai encontrar token e vai retornar erro 403
        }
    }

    private String recoverToken(HttpServletRequest request){
        var authHeader = request.getHeader("Bearer"); // busca nos headers Authorization
        if(authHeader==null){
            return null;
        }else{
            return authHeader.replace("Bearer ", "");
        }
    }
    /**
     * é padrão das requisições http, quando manda o header de autorização que contem um token, identifica o tipo de token
     * no caso (Bearer ) token, Bearer e espaço vazio na string para pegar somente o valor to token
     *
     */
}
