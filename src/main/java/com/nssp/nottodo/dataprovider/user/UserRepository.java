package com.nssp.nottodo.dataprovider.user;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Qualifier("user")
@Repository
public interface UserRepository extends CrudRepository<UserEnt, String> {
}
