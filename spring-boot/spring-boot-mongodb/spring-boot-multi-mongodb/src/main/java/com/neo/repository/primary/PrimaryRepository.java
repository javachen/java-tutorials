package com.javachen.repository.primary;

import com.javachen.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author javachen
 */
public interface PrimaryRepository extends MongoRepository<User, String> {
}
