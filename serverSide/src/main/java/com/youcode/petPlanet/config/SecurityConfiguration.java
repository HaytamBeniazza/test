package com.youcode.petPlanet.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import static com.youcode.petPlanet.auth.user.Role.*;
import static com.youcode.petPlanet.auth.user.Permission.*;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

    private static final String[] WHITE_LIST_URL = {
            "/auth/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger-ui.html"};
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req ->
                        req.requestMatchers(WHITE_LIST_URL)
                                .permitAll()
                                .requestMatchers("/category/**").hasAnyRole(ADMIN.name(),CLIENT.name())
                                .requestMatchers("/comment/**").hasAnyRole(BLOGAUTHOR.name(),ADMIN.name())
                                .requestMatchers("/order/**").hasAnyRole(CLIENT.name(),ADMIN.name())
                                .requestMatchers("/pet/**").hasAnyRole(CLIENT.name(),ADMIN.name(),BLOGAUTHOR.name())
                                .requestMatchers("/post/**").hasAnyRole(BLOGAUTHOR.name(),ADMIN.name())
                                .requestMatchers("/product/**").hasAnyRole(ADMIN.name(),CLIENT.name())
                                .requestMatchers("/petproduct/**").hasAnyRole(ADMIN.name(),CLIENT.name())
                                .requestMatchers("/review/**").hasAnyRole(BLOGAUTHOR.name(), CLIENT.name(),ADMIN.name())
                                .requestMatchers("/user/**").hasAnyRole(BLOGAUTHOR.name(), CLIENT.name(),ADMIN.name())
                                .requestMatchers(GET, "/category/**").hasAnyAuthority(ADMIN_CREATE.name(),CLIENT_READ.name())
                                .requestMatchers(GET, "/category/**").hasAnyAuthority(ADMIN_CREATE.name(), CLIENT_READ.name())
                                .requestMatchers(POST, "/order/**").hasAnyAuthority(ADMIN_CREATE.name(), CLIENT_READ.name(),CLIENT_CREATE.name())
                                .requestMatchers(POST, "/comment/**").hasAnyAuthority(ADMIN_CREATE.name(), CLIENT_READ.name(),CLIENT_CREATE.name())
                                .requestMatchers(POST, "/user/**").hasAnyAuthority(ADMIN_CREATE.name(), CLIENT_READ.name())
                                .requestMatchers(POST, "/review/**").hasAnyAuthority(ADMIN_CREATE.name(), CLIENT_READ.name(),CLIENT_CREATE.name())
                                .requestMatchers(POST, "/petproduct/**").hasAnyAuthority(ADMIN_CREATE.name(), CLIENT_READ.name())
                                .requestMatchers(POST, "/product/**").hasAnyAuthority(ADMIN_CREATE.name(),ADMIN_READ.name(),ADMIN_UPDATE.name(),ADMIN_DELETE.name(), CLIENT_READ.name())
                                .requestMatchers(GET, "/product/**").hasAnyAuthority(ADMIN_CREATE.name(),ADMIN_READ.name(),ADMIN_UPDATE.name(),ADMIN_DELETE.name(), CLIENT_READ.name())
                                .requestMatchers(PUT, "/product/**").hasAnyAuthority(ADMIN_CREATE.name(),ADMIN_READ.name(),ADMIN_UPDATE.name(),ADMIN_DELETE.name(), CLIENT_READ.name())
                                .requestMatchers(DELETE, "/product/**").hasAnyAuthority(ADMIN_CREATE.name(),ADMIN_READ.name(),ADMIN_UPDATE.name(),ADMIN_DELETE.name(), CLIENT_READ.name())
                                .requestMatchers(POST, "/post/**").hasAnyAuthority(ADMIN_CREATE.name(), CLIENT_READ.name(),CLIENT_CREATE.name())
                                .requestMatchers(POST, "/pet/**").hasAnyAuthority(ADMIN_CREATE.name(), CLIENT_READ.name())
                                .anyRequest()
                                .authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout ->
                        logout.logoutUrl("/auth/logout")
                                .addLogoutHandler(logoutHandler)
                                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
                );

        return http.build();
    }
}
