package br.gov.sp.cps.api.pixel.outbound.jpa;

import br.gov.sp.cps.api.pixel.core.domain.entity.User;
import br.gov.sp.cps.api.pixel.core.domain.repository.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserJpaRepository extends JpaRepository<User, Long>, UserRepository {

    default UserDetails buscarPorLogin(String login) {
        return findByLogin(login);
    }

    UserDetails findByLogin(String login);

    default User salvar(User user) {
        return save(user);
    }

    @Query(value = "SELECT u.nome AS nome_usuario, g.nome AS nome_grupo, p.nome_descricao AS descricao" +
            "FROM usuario u" +
            "JOIN grupo g ON g.id = u.grupo_id" +
            "JOIN grupo_permissao gp ON gp.grupo_id = g.id" +
            "JOIN permissao p ON gp.permissao_id = p.id" +
            "WHERE g.id = ?" ,
            nativeQuery = true)
    List<Object[]> buscarPermissoesPorGrupoId(Long grupoId);
}
