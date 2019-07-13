package com.javachen.repository.secondary;

import com.javachen.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author javachen
 */
public interface SecondaryRepository extends MongoRepository<User, String> {
}
