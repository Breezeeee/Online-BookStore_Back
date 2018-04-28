package com.proj.onlinebookstore;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ORDERFORMRepository extends JpaRepository<ORDERFORM, Long> {
    List<ORDERFORM> findByUid(String uid);

    @Query("select new com.proj.onlinebookstore.OrderModel(b.name, b.author, b.price, i.num, b.price*i.num) from ORDERFORM o, ITEM i, BOOK b where o.id=i.oidorcid and i.bid=b.id and o.id=:id")
    List<OrderModel> withOidOrderItemsQuery(@Param("id") String id);

    @Query("select o from ORDERFORM o where o.id=:id")
    ORDERFORM withOidOrderQuery(@Param("id") String id);
}