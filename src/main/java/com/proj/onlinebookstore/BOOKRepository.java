package com.proj.onlinebookstore;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BOOKRepository extends JpaRepository<BOOK, Long> {
    @Query("select p from BOOK p where p.id=:id")
    BOOK withIdBookQuery(@Param("id") String id);
}
