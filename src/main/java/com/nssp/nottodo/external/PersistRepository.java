package com.nssp.nottodo.external;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface PersistRepository<T> extends CrudRepository<T, Long> {
}
