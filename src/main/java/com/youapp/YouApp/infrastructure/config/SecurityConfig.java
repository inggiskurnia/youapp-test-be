package com.youapp.YouApp.infrastructure.config;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import com.youapp.YouApp.infrastructure.security.filters.TokenBlacklist;
import com.youapp.YouApp.service.GetUserAuthDetailsUsecase;
import jakarta.servlet.http.Cookie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final GetUserAuthDetailsUsecase getUserAuthDetailsUsecase;
    private final PasswordEncoder passwordEncoder;
    private final RsaKeyConfigProperties rsaKeyConfigProperties;
    private final TokenBlacklist tokenBlacklistFilter;

    public SecurityConfig(GetUserAuthDetailsUsecase getUserAuthDetailsUsecase, PasswordEncoder passwordEncoder, RsaKeyConfigProperties rsaKeyConfigProperties, TokenBlacklist tokenBlacklistFilter) {
        this.getUserAuthDetailsUsecase = getUserAuthDetailsUsecase;
        this.passwordEncoder = passwordEncoder;
        this.rsaKeyConfigProperties = rsaKeyConfigProperties;
        this.tokenBlacklistFilter = tokenBlacklistFilter;
    }

    @Bean
    public AuthenticationManager authManager(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(getUserAuthDetailsUsecase);
        authProvider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(authProvider);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(new CorsConfigurationSourceImpl()))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/register").permitAll()
                        .requestMatchers("/api/login").permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(tokenBlacklistFilter, UsernamePasswordAuthenticationFilter.class)
                .oauth2ResourceServer(oauth2 -> {
                    oauth2.jwt(jwt -> jwt.decoder(jwtDecoder()));

                    oauth2.bearerTokenResolver(request -> {
                        Cookie[] cookies = request.getCookies();
                        if (cookies != null){
                            for (Cookie cookie : cookies){
                                if (cookie.getName().equals("SID")){
                                    return cookie.getValue();
                                }
                            }
                        }

                        var header = request.getHeader("Authorization");
                        if (header != null){
                            return header.replace("Bearer ", "");
                        }
                        return null;
                    });
                })
                .userDetailsService(getUserAuthDetailsUsecase)
                .build();
    }

    @Bean
    public JwtDecoder jwtDecoder(){
        return NimbusJwtDecoder.withPublicKey(rsaKeyConfigProperties.publicKey()).build();
    }

    @Bean
    public JwtEncoder jwtEncoder(){
        JWK jwk = new RSAKey.Builder(rsaKeyConfigProperties.publicKey()).privateKey(rsaKeyConfigProperties.privateKey()).build();
        JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwks);
    }
}
