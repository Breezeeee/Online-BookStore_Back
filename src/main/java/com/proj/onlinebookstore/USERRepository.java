package com.proj.onlinebookstore;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface USERRepository extends JpaRepository<USER, Long> {
    @Query("select p from USER p where p.username=:username")
    USER withUsernameUserQuery(@Param("username") String username);

    @Query("select p from USER p where p.id=:id")
    USER withIdUserQuery(@Param("id") String id);

    @Query("select p from USER p where p.username=:username and p.password=:password")
    USER withUsernameAndPasswordUserQuery(@Param("username") String username, @Param("password") String password);
}
