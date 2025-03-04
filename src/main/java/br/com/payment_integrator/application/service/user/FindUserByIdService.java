package br.com.payment_integrator.application.service.user;

import br.com.payment_integrator.domain.entity.authentication.User;
import br.com.payment_integrator.domain.exception.user.UserNotFoundException;
import br.com.payment_integrator.domain.service.user.IFindUserByIdService;
import br.com.payment_integrator.infra.repository.authentication.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FindUserByIdService implements IFindUserByIdService {

    private final UserRepository userRepository;

    @Override
    public User findUserById(String userId) throws UserNotFoundException {
        return userRepository
                .findById(UUID.fromString(userId))
                .orElseThrow(UserNotFoundException::new);
    }
}
