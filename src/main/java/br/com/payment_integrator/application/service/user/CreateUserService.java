package br.com.payment_integrator.application.service.user;

import br.com.payment_integrator.domain.dto.user.create_user.CreateUserDTO;
import br.com.payment_integrator.domain.entity.authentication.User;
import br.com.payment_integrator.domain.service.user.ICreateUserService;
import br.com.payment_integrator.infra.repository.authentication.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CreateUserService implements ICreateUserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public User createUser(CreateUserDTO createUserDTO) throws Exception {
        Optional<User> userExist = userRepository.findByEmail(createUserDTO.email());

        if (userExist.isPresent()) {
            throw new Exception("Usuário já existe");
        }

        User user = User.builder()
                .name(createUserDTO.name())
                .email(createUserDTO.email())
                .password(createUserDTO.password())
                .build();

        return userRepository.save(user);
    }
}
