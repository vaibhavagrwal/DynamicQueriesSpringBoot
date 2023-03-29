package com.sub.sublayer.repository;

import com.sub.sublayer.models.Orders;
import com.sub.sublayer.models.Subs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SubsRepository extends JpaRepository<Subs, Long>, JpaSpecificationExecutor<Subs> {
}
