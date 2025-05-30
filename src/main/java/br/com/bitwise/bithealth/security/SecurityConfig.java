package br.com.bitwise.bithealth.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final SecurityFilter securityFilter;

    private static final String[] SWAGGER_PERMITIONS_LIST = {
            "swagger-ui/**",
            "/v3/api-docs/**",
            "/swagger-resources/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(SWAGGER_PERMITIONS_LIST).permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/auth/registro").permitAll()
                        .requestMatchers("/api/admin/**").hasRole("ADMINISTRADOR")
                        .requestMatchers("/api/cidadao/**").hasRole("CIDADAO")
                        .requestMatchers(HttpMethod.GET, "/api/unidades-saude/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/unidades-saude/").hasRole("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.DELETE, "/api/unidades-saude/**").hasRole("ADMINISTRADOR")

                        .requestMatchers(HttpMethod.GET, "api/servicos-saude/").permitAll()
                        .requestMatchers(HttpMethod.POST, "api/servicos-saude/").hasRole("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.DELETE, "api/servicos-saude/**").hasRole("ADMINISTRADOR")

                        .requestMatchers(HttpMethod.GET, "/api/medicamentos/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/medicamentos").hasRole("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.DELETE, "/api/medicamentos/**").hasRole("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.PUT, "/api/medicamentos/**").hasRole("ADMINISTRADOR")

                        .requestMatchers(HttpMethod.GET, "/api/news/").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/news").hasRole("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.DELETE, "/api/news/**").hasRole("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.PUT, "/api/news/**").hasRole("ADMINISTRADOR")

                        .requestMatchers(HttpMethod.GET, "/api/calendario-vacinacao/").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/calendario-vacinacao").hasRole("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.DELETE, "/api/calendario-vacinacao/**").hasRole("ADMINISTRADOR")

                        .requestMatchers(HttpMethod.GET, "/api/doctors").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/doctors/**").permitAll()

                        .requestMatchers(HttpMethod.POST, "/api/reset/forgot-password").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/reset/reset-password").permitAll()

                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(List.of("*"));  // Permite todas as origens, ajuste conforme necessário
        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
        corsConfiguration.setAllowedHeaders(List.of("Content-Type", "Authorization"));
        corsConfiguration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);  // Aplica a configuração CORS a todas as rotas

        return source;
    }
}