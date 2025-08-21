package br.gov.caixa.hackaton.config.interceptor;

import br.gov.caixa.hackaton.interceptor.TelemetriaInterceptor;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@AllArgsConstructor
@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {

    private TelemetriaInterceptor telemetriaInterceptor;

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(telemetriaInterceptor);
    }
}
