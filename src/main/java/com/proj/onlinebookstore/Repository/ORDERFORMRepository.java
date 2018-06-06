package com.proj.onlinebookstore.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import com.proj.onlinebookstore.Entity.ORDERFORM;
import com.proj.onlinebookstore.Entity.OrderModel;
import com.proj.onlinebookstore.Entity.StatisticsModel;

public interface ORDERFORMRepository extends JpaRepository<ORDERFORM, Long> {
    List<ORDERFORM> findByUid(String uid);

    @Query("select new com.proj.onlinebookstore.Entity.OrderModel(b.name, b.author, b.price, i.num, b.price*i.num) from ORDERFORM o, ITEM i, BOOK b where o.id=i.oidorcid and i.bid=b.id and o.id=:id")
    List<OrderModel> withOidOrderItemsQuery(@Param("id") String id);

    @Query("select o from ORDERFORM o where o.id=:id")
    ORDERFORM withOidOrderQuery(@Param("id") String id);

    @Query("select new com.proj.onlinebookstore.Entity.StatisticsModel(o.id, b.name, b.author, u.username, o.date, i.num, b.price*i.num) from USER u, ORDERFORM o, ITEM i, BOOK b where u.id=o.uid and o.id=i.oidorcid and i.bid=b.id")
    List<StatisticsModel> OrderStatistics();
}