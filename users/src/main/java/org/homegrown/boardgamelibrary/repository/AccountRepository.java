package org.homegrown.boardgamelibrary.repository;

import org.homegrown.boardgamelibrary.domain.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.UUID;

public interface AccountRepository extends PagingAndSortingRepository<Account, UUID> {
    @PreAuthorize("(hasRole('USER') and authentication.principal.username == #username) or hasAnyRole('ADMIN', 'DISTRIBUTOR')")
    Account findByUsername(@Param("username") String username);

    @RestResource(exported = false)
    Account findByEmailIgnoreCase(@Param("email") String email);

    @RestResource(exported = false)
    @PreAuthorize("(authentication.principal.uuid == #uuid.toString()) or hasAnyRole('ADMIN', 'DISTRIBUTOR')")
    Account findByUuid(@Param("uuid") UUID uuid);

    @RestResource(exported = false)
    Account findByMobile(@Param("mobile") String mobile);

    @PreAuthorize("hasAnyRole('ADMIN')")
    @Override
    Iterable<Account> findAll(Sort sort);

    @PreAuthorize("hasAnyRole('ADMIN')")
    @Override
    Page<Account> findAll(Pageable pageable);

    @PreAuthorize("hasAnyRole('ADMIN')")
    @Override
    Iterable<Account> findAll();

    @PreAuthorize("hasAnyRole('ADMIN')")
    @Override
    Iterable<Account> findAll(Iterable<UUID> uuids);

    @Override
    @RestResource(exported = false)
    Account save(Account entity);

    @RestResource(exported = false)
    @Override
    void deleteAll();
}
