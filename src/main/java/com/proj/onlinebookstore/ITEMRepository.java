package com.proj.onlinebookstore;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ITEMRepository extends JpaRepository<ITEM, Long> {
    List<ITEM> findByOidorcid(String oidorcid);

    @Query("select i from ITEM i where i.oidorcid=:cid and i.bid=:bid")
    ITEM withCidAndBidItemQuery(@Param("cid") String cid, @Param("bid") String bid);

    @Query("select i from ITEM i where i.oidorcid=:cid")
    List<ITEM> withCidItemQuery(@Param("cid") String cid);
}
