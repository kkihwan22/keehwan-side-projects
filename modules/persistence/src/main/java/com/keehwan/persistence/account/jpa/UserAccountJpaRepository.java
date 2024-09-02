package com.keehwan.persistence.account.jpa;

import com.keehwan.core.account.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAccountJpaRepository extends JpaRepository<UserAccount, Long> {

    Optional<UserAccount> findUserAccountByUsername(String username);
}
