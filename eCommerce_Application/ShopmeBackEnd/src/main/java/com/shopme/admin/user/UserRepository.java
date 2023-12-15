package com.shopme.admin.user;

import org.apache.catalina.realm.JNDIRealm.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{

}
