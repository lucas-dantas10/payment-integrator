package br.com.payment_integrator.action.account;

import br.com.payment_integrator.domain.dto.account.create_account.CreateAccountDTO;
import br.com.payment_integrator.domain.service.user.ICreateAccountService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class CreateAccountPostAction {

    private final ICreateAccountService createAccount;

    @Tag(name = "Account")
    @PostMapping
    public ResponseEntity<Void> createAccount(@RequestBody CreateAccountDTO createAccountDTO) throws Exception {
        createAccount.execute(createAccountDTO);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
