package com.example.jwttutorial.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity // 이 어노테이션은 클래스를 스프링 시큐리티의 설정 클래스로 표시하며, 기본 웹 보안을 활성화합니다.
public class SecurityConfig {

    /**
     * Spring Security 5.7.0-M2에서 WebSecurityConfigurerAdapter가 사용 중단(deprecated) 되었습니다.
     * 이는 컴포넌트 기반의 보안 구성으로의 전환을 장려하기 위함입니다.
     * 이 변경으로 인해 보다 깔끔하고 모듈화된 보안 구성이 가능해졌습니다.
     * 결과적으로, SecurityFilterChain을 사용하여 보안 규칙을 정의합니다.
     *
     * @Bean 어노테이션은 애플리케이션 컨텍스트에서 스프링 빈을 정의하는 데 사용됩니다.
     * filterChain 메소드는 SecurityFilterChain 타입의 빈을 정의합니다.
     * 이 메소드는 HttpSecurity를 구성하여 HTTP 요청에 대한 보안 규칙을 설정합니다.
     *
     * @param /http 웹 기반 보안을 구성하기 위한 HttpSecurity 객체.
     * @return 구성된 HttpSecurity를 사용하여 만들어진 SecurityFilterChain.
     * @throws /Exception 구성 중 오류가 발생한 경우.
     */

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .requestMatchers(new AntPathRequestMatcher("/h2-console/**"))
                .requestMatchers(new AntPathRequestMatcher("/favicon.ico"));
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // authorizeHttpRequests 메소드는 인가 규칙을 적용하는 데 사용됩니다.
                .authorizeHttpRequests(auth ->
                        // auth.anyRequest().permitAll()은 모든 요청이 보안 제한 없이 허용됨을 의미합니다.
                        // 이는 개방된 구성이며, 필요에 따라 보안 제약 조건을 추가로 조정해야 합니다.
                        auth.anyRequest().permitAll()
                );

        // 마지막으로, 구성을 빌드하고 SecurityFilterChain으로 반환합니다.
        return http.build();
    }
}
