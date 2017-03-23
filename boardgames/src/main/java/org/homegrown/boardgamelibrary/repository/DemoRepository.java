package org.homegrown.boardgamelibrary.repository;

import org.homegrown.boardgamelibrary.domain.Demo;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the Demo entity.
 */
@SuppressWarnings("unused")
public interface DemoRepository extends MongoRepository<Demo,String> {

}
