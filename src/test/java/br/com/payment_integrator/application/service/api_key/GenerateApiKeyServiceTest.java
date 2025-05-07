package br.com.payment_integrator.application.service.api_key;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GenerateApiKeyServiceTest {

    private static final int NUMBER_BYTE = 32;

    @InjectMocks
    private GenerateApiKeyService generateApiKeyService;

    @Test
    public void shouldGenerateApiKey() {
        String apiKey = generateApiKeyService.generate();

        int expectedMinLength = Base64.getUrlEncoder().withoutPadding().encodeToString(new byte[NUMBER_BYTE]).length();

        assertNotNull(apiKey);
        assertFalse(apiKey.isEmpty());
        assertTrue(apiKey.matches("^[A-Za-z0-9_-]+$"));
        assertEquals(expectedMinLength, apiKey.length());
    }

}