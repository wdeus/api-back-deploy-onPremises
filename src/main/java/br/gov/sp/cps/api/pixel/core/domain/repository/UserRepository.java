package br.gov.sp.cps.api.pixel.core.domain.repository;

import br.gov.sp.cps.api.pixel.core.domain.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository {

    UserDetails buscarPorLogin(String login);

    User salvar(User user);
}
