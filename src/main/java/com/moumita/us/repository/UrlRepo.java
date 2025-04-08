package com.moumita.us.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.moumita.us.model.ShortUrl;

@Repository
public interface UrlRepo extends JpaRepository<ShortUrl, Integer> {

	@Query("SELECT s.originalUrl FROM ShortUrl s WHERE s.shortCode = :shortCode")//table name should be the class name
	String findByShortCode(String shortCode);
	
	@Query("select s.UrlId from ShortUrl s order by UrlId desc limit 1")//table name should be the class name
	String findUrlId();
	
	@Query("select s.clickCount from ShortUrl s Where s.shortCode = :shortCode")//table name should be the class name
	long findClickCount(String shortCode);
	
	@Query("UPDATE ShortUrl s SET s.clickCount = :clickCount WHERE s.shortCode = :shortCode")
    int updateCount(Long clickCount,  String shortCode);


}
