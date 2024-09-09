package com.keehwan.persistence.repository.user;

import com.keehwan.core.account.domain.UserAccount;
import com.keehwan.core.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByAccount(UserAccount account);
}
