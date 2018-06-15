package com.proj.onlinebookstore.Repository;

import com.proj.onlinebookstore.Entity.BookInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookInfoRepository extends MongoRepository<BookInfo, String> {
    BookInfo findByBid(String Bid);
}
