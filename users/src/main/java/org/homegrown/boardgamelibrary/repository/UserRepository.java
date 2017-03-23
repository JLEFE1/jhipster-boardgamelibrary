package org.homegrown.boardgamelibrary.repository;

import org.homegrown.boardgamelibrary.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.UUID;

public interface UserRepository extends IAccountRepository<User> {

    @RestResource(exported = false)
    User findByUuid(@Param("uuid") UUID uuid);

    @RestResource(exported = false)
    @Override
    User findOne(UUID id);

    @Override
    @RestResource(exported = false)
    Iterable<User> findAll();

    @Override
    @RestResource(exported = false)
    Iterable<User> findAll(Sort sort);

    @Override
    @RestResource(exported = false)
    Page<User> findAll(Pageable pageable);

    @Override
    @RestResource(exported = false)
    User save(User user);

}
