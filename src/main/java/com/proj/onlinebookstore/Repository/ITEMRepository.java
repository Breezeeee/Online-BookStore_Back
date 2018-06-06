package com.proj.onlinebookstore.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import com.proj.onlinebookstore.Entity.ITEM;

public interface ITEMRepository extends JpaRepository<ITEM, Long> {
    List<ITEM> findByOidorcid(String oidorcid);

    @Query("select i from ITEM i where i.oidorcid=:cid and i.bid=:bid")
    ITEM withCidAndBidItemQuery(@Param("cid") String cid, @Param("bid") String bid);

    @Query("select i from ITEM i where i.oidorcid=:cid")
    List<ITEM> withCidItemQuery(@Param("cid") String cid);

    @Query("select i from ITEM i where i.bid=:bid")
    List<ITEM> withBidITEMQuery(@Param("bid") String bid);
}
