package com.microservices.product.config;

import com.microservices.product.services.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableMethodSecurity
public class AuthSecurityConfig
//        extends  WebSecurityConfigurerAdapter
{
    @Autowired
    JwtRequestFilter jwtRequestFilter;
    List<String> publicApis = List.of("/api/auth/login", "/api/category/**");
    private CorsConfigurationSource corsConfigurationSource() {
        // Very permissive CORS config...
        final var configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setExposedHeaders(Arrays.asList("*"));

        // Limited to API routes (neither actuator nor Swagger-UI)
        final var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", configuration);

        return source;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().configurationSource(corsConfigurationSource());
//        http
//                .authorizeHttpRequests((authz) -> authz
////                        .requestMatchers(HttpMethod.POST, "/login").permitAll()
//                        .antMatchers("/login").permitAll()
////                                .and()
//                        .requestMatchers("/api/category/**").permitAll()
////                                .requestMatchers(publicApis.stream()
////                                        .map(AntPathRequestMatcher::new)
////                                        .toArray(RequestMatcher[]::new)).permitAll()
//                        .requestMatchers("/product/**").authenticated()
//
//                        .anyRequest().authenticated()
//
////                                .anyRequest().permitAll()
//                )
//                .httpBasic(Customizer.withDefaults());

        http.authorizeHttpRequests()
//                .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
//                .requestMatchers(HttpMethod.POST, "/api/auth/logout").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/auth/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/dummy/foo").permitAll()
                .requestMatchers("/api/category/**").permitAll()
//                .requestMatchers("/api/product/**").authenticated()
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
//                .and()
//                .formLogin()
//                .loginPage("/api/auth/login")
//                .and().httpBasic();
        return http.build();
    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(8);
//    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
//    @Bean
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return this.authenticationManagerBean();
//    }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth)
//            throws Exception
//    {
//        auth.inMemoryAuthentication()
//                .withUser("admin")
//                .password(passwordEncoder().encode("admin@123"))
//                .roles("ROLE_USER");
//    }

//    @Bean
//    public InMemoryUserDetailsManager userDetailsService() {
//        UserDetails user = User
//                .withUsername("admin")
//                .password(passwordEncoder().encode("admin@123"))
//                .roles("USER_ROLE")
//                .build();
//        return new InMemoryUserDetailsManager(user);
//    }
}
