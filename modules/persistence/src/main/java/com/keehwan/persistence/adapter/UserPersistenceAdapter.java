package com.keehwan.persistence.adapter;

import com.keehwan.core.user.domain.User;
import com.keehwan.core.user.service.persistence.UserPersistence;
import com.keehwan.persistence.repository.user.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserPersistenceAdapter implements UserPersistence {
    private final UserJpaRepository userJpaRepository;

    @Override
    public User createUser(User user) {
        return userJpaRepository.save(user);
    }
}
