package com.jackdang.config;

// import com.jackdang.common.auth.JwtAuthProvider;
//import com.jackdang.common.interceptor.AuthHandlerInterceptor;
import com.jackdang.common.auth.JwtAuthProvider;
import com.jackdang.common.interceptor.AuthHandlerInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
@RequiredArgsConstructor
@ComponentScan(basePackages = {"com.jackdang.common.interceptor"})
public class WebMvcConfigure implements WebMvcConfigurer {

    private final JwtAuthProvider jwtAuthProvider;

    @Autowired
    private AuthHandlerInterceptor authHandlerInterceptor;

    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*")
                .exposedHeaders("jwt-auth-toekn"); // 클라이언트로 header값 반환시 사용 (*이면 전부)
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(authHandlerInterceptor)
                .addPathPatterns("/api/auth/**") // interceptor 적용될 경로
                .excludePathPatterns(new String[]{"/excludePath/**"}); // interceptor 적용되지 않을 경로
    }
}