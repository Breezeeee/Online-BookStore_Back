package com.proj.onlinebookstore.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.proj.onlinebookstore.Entity.BOOK;

public interface BOOKRepository extends JpaRepository<BOOK, Long> {
    @Query("select p from BOOK p where p.id=:id")
    BOOK withIdBookQuery(@Param("id") String id);

    @Query("select b from BOOK b where b.name=:name and b.author=:author and b.language=:language and b.published=:published")
    BOOK withNALPBookQuery(@Param("name") String name, @Param("author") String author, @Param("language") String language, @Param("published") String published);
}
