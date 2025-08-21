package br.gov.caixa.hackaton.interceptor;

import br.gov.caixa.hackaton.dto.telemetria.TelemetriaDTO;
import br.gov.caixa.hackaton.service.implementation.TelemetriaServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@AllArgsConstructor
public class TelemetriaInterceptor implements HandlerInterceptor {

    private TelemetriaServiceImpl telemetriaService;

    private static final String START_TIME = "startTime";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute(START_TIME, System.currentTimeMillis());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        Long startTime = (Long) request.getAttribute(START_TIME);
        Long tempoExecucao = System.currentTimeMillis() - startTime;
        int status = response.getStatus();
        String uri = request.getRequestURI();
        String method = request.getMethod();
        TelemetriaDTO reqTel = TelemetriaDTO
                .builder()
                .nomeApi(uri)
                .methodoHttp(method)
                .isSucesso(status == 200)
                .tempoExecucao(tempoExecucao)
                .build();
        telemetriaService.cadastrarTelemetria(reqTel);
    }
}
