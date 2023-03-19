package com.sub.sublayer.repository;

import com.sub.sublayer.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaSpecificationExecutor<User>, JpaRepository<User, Long> {
}
