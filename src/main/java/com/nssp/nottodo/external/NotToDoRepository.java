package com.nssp.nottodo.external;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Qualifier("nottodo")
@Repository
public interface NotToDoRepository extends CrudRepository<NotToDoEnt, Long> {
}
