package com.brs.security.api;

import com.brs.model.user.UserAccount;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.brs.security.SecurityConstants.*;

public class ApiJWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public ApiJWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/api/auth", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) {
        try {
            UserAccount userAccount = new ObjectMapper().readValue(req.getInputStream(), UserAccount.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    userAccount.getEmail(), userAccount.getPassword(), new ArrayList<>()));
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res,
                                            FilterChain chain, Authentication auth) {

        if(auth.getPrincipal() != null) {
            User user = (User) auth.getPrincipal();
            String login = user.getUsername();
            if(login != null && login.length() > 0) {
                Claims claims = Jwts.claims().setSubject(login);
                List<String> roles = new ArrayList<>();
                user.getAuthorities().stream().forEach(authority -> roles.add(authority.getAuthority()));
                claims.put("roles", roles);
                String token = Jwts
                        .builder()
                        .setClaims(claims)
                        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                        .signWith(SignatureAlgorithm.HS512, SECRET)
                        .compact();
                res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
            }
        }

    }

}
