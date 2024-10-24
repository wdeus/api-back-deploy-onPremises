package br.gov.sp.cps.api.pixel.outbound.jpa;

import br.gov.sp.cps.api.pixel.core.domain.entity.User;
import br.gov.sp.cps.api.pixel.core.domain.repository.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserJpaRepository extends JpaRepository<User, Long>, UserRepository {

    default UserDetails buscarPorLogin(String login) {
        return null;
    }

    UserDetails findByLogin(String login);

    default User salvar(User user) {
        return save(user);
    }
}