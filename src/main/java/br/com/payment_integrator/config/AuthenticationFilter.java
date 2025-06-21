package br.com.payment_integrator.config;

import br.com.payment_integrator.domain.entity.authentication.Account;
import br.com.payment_integrator.domain.service.account.IFindAccountByApiKeyService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthenticationFilter extends OncePerRequestFilter {

    private static final String API_KEY = "X-API-KEY";
    private final IFindAccountByApiKeyService findAccountByApiKeyService;

    @Override
    public void doFilterInternal(@NonNull HttpServletRequest request,
                                 @NonNull HttpServletResponse response,
                                 @NonNull FilterChain filterChain)
            throws IOException, ServletException {
        log.info("Start authentication filter");

        Authentication authentication = this.getAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        log.info(path);

        return path.startsWith("/actuator") || path.equals("/api/v1/account");
    }

    private Authentication getAuthentication(HttpServletRequest request) {
        String apiKey = request.getHeader(API_KEY);

        if (apiKey == null) {
            throw new BadCredentialsException("Chave de API ausente");
        }

        Account account = findAccountByApiKeyService.execute(apiKey);

        if (!account.getApiKey().equals(apiKey)) {
            throw new BadCredentialsException("Chave de API inválida");
        }

        Authentication currentAuth = SecurityContextHolder.getContext().getAuthentication();

        if (currentAuth != null && currentAuth.isAuthenticated()) {
            return currentAuth;
        }

        return new UsernamePasswordAuthenticationToken(account, null, AuthorityUtils.NO_AUTHORITIES);
    }
}
