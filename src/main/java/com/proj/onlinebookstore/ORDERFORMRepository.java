package com.proj.onlinebookstore;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ORDERFORMRepository extends JpaRepository<ORDERFORM, Long> {
    List<ORDERFORM> findByUid(String uid);

    @Query("select p from ORDERFORM p where p.id=:id")
    ORDERFORM withOidOrderQuery(@Param("id") String id);
}