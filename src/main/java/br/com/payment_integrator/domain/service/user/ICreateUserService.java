package br.com.payment_integrator.domain.service.user;

import br.com.payment_integrator.domain.dto.user.create_user.CreateUserDTO;
import br.com.payment_integrator.domain.entity.authentication.User;

public interface ICreateUserService {

    User createUser(CreateUserDTO createUserDTO) throws Exception;
}
