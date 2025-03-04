package br.com.payment_integrator.domain.service.user;

import br.com.payment_integrator.domain.entity.authentication.User;
import br.com.payment_integrator.domain.exception.user.UserNotFoundException;

public interface IFindUserByIdService {

    User findUserById(String userId) throws UserNotFoundException;
}
