package com.keehwan.persistence.repository.account;

import com.keehwan.core.account.domain.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserTokenJpaRepository extends JpaRepository<UserToken, Long> {

    Optional<UserToken> findUserTokenByToken(String token);

    void deleteUserTokenByToken(String token);
}
