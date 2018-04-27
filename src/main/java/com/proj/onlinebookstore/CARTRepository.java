package com.proj.onlinebookstore;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CARTRepository extends JpaRepository<CART, Long> {
    @Query("select new com.proj.onlinebookstore.CARTModel(b.name, b.id, b.price, i.num, b.stock) from CART c, ITEM i, BOOK b where c.cid = i.oidorcid and b.id = i.bid and c.uid = :uid")
    List<CARTModel> withUidCartQuery(@Param("uid") String uid);

    @Query("select c from CART c where c.uid=:uid")
    CART withUidCidQuery(@Param("uid") String uid);
}