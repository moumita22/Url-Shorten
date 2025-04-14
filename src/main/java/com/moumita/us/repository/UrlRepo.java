package com.moumita.us.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.moumita.us.model.ShortUrl;

@Repository
public interface UrlRepo extends JpaRepository<ShortUrl, Integer> {

	@Query("SELECT s.originalUrl FROM ShortUrl s WHERE s.shortCode = :shortCode")//table name should be the class name
	String findByShortCode(String shortCode);
	
	@Query("select s.UrlId from ShortUrl s order by UrlId desc limit 1")//table name should be the class name
	String findUrlId();
	
	@Query("select s.clickCount from ShortUrl s Where s.shortCode = :shortCode")//table name should be the class name
	long findClickCount(String shortCode);
	
	@Modifying//need for update and delete query
	@Transactional//need for update and delete query
	@Query("UPDATE ShortUrl s SET s.clickCount = :clickCount WHERE s.shortCode = :shortCode")
    int updateCount(Long clickCount,  String shortCode);
	
	@Query("select s.expiryAt from ShortUrl s Where s.shortCode = :shortCode")
	LocalDateTime findexpiryAt(String shortCode);


}
