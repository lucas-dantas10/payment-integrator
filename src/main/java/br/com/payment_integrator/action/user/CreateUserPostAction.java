package br.com.payment_integrator.action.user;

import br.com.payment_integrator.domain.dto.user.create_user.CreateUserDTO;
import br.com.payment_integrator.domain.service.user.ICreateUserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class CreateUserPostAction {

    private final ICreateUserService createUserService;

    @PostMapping
    @Tag(name = "User")
    public ResponseEntity<Void> createUser(@RequestBody CreateUserDTO createUserDTO) throws Exception {
        createUserService.createUser(createUserDTO);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
