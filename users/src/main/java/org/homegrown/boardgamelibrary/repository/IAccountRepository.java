package org.homegrown.boardgamelibrary.repository;

import org.homegrown.boardgamelibrary.domain.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;
import java.util.UUID;

@NoRepositoryBean
public interface IAccountRepository<T extends Account> extends PagingAndSortingRepository<T, UUID>, JpaSpecificationExecutor<T> {
    @PreAuthorize("(authentication.principal.username == #username) or hasAnyRole('ADMIN', 'DISTRIBUTOR')")
    T findByUsername(@Param("username") String username);

    @PreAuthorize("hasRole('ADMIN')")
    @RestResource(path = "findByEmail", rel = "findByEmail")
    T findByEmailIgnoreCase(@Param("email") String email);

    @PreAuthorize("hasAnyRole('ADMIN')")
    @Override
    Iterable<T> findAll();

    @PreAuthorize("(authentication.principal.uuid == #uuid.toString()) or hasRole('ADMIN')")
    T findByUuid(@Param("uuid") UUID uuid);

    Page<T> findByCreatedBy(Pageable pageable, UUID uuid);

    @PreAuthorize("hasAnyRole('ADMIN')")
    @Override
    @RestResource(exported = false)
    Iterable<T> findAll(Sort sort);

    @PreAuthorize("hasAnyRole('ADMIN')")
    @Override
    @RestResource(exported = false)
    Page<T> findAll(Pageable pageable);

    @PreAuthorize("hasAnyRole('ADMIN')")
    @Override
    @RestResource(exported = false)
    Iterable<T> findAll(Iterable<UUID> uuids);

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostAuthorize("returnObject==null or hasRole('ADMIN') or returnObject.uuid.toString() == authentication.principal.uuid")
    @Override
    T findOne(UUID id);

    @RestResource(exported = false)
    @Override
    void deleteAll();

    @Override
    T findOne(Specification<T> specification);

    @PreAuthorize("hasAnyRole('ADMIN')")
    @Override
    List<T> findAll(Specification<T> specification);

    @PreAuthorize("hasAnyRole('ADMIN')")
    @Override
    Page<T> findAll(Specification<T> specification, Pageable pageable);

    @PreAuthorize("hasAnyRole('ADMIN')")
    @Override
    List<T> findAll(Specification<T> specification, Sort sort);

    @PreAuthorize("hasAnyRole('ADMIN')")
    @Override
    long count(Specification<T> specification);

    @RestResource(exported = false)
    @Override
    <S extends T> Iterable<S> save(Iterable<S> iterable);
}
