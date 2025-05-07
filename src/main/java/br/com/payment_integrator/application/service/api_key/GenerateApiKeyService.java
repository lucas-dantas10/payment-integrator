package br.com.payment_integrator.application.service.api_key;

import br.com.payment_integrator.domain.service.api_key.IGenerateApiKeyService;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class GenerateApiKeyService implements IGenerateApiKeyService {

    private static final int NUMBER_BYTE = 32;

    public String generate() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[NUMBER_BYTE];
        random.nextBytes(bytes);

        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }
}
