package com.moumita.us.service;

import java.time.LocalDateTime;
import java.security.MessageDigest;
import java.util.Base64;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.moumita.us.model.ShortUrl;
import com.moumita.us.repository.UrlRepo;

@Component
@Service
public class GenerateUrl {
	
	@Autowired
	UrlRepo repo;
	
	ShortUrl surl = new ShortUrl();
	public String generateShortCode(String longUrl,String customAlias,LocalDateTime expiry) {
		if(customAlias == "") {
			//customAlias = RandomStringUtils.randomAlphanumeric(6);// or hash
			try {
				String Iurl = longUrl+repo.findUrlId();
		        MessageDigest digest = MessageDigest.getInstance("SHA-256");
		        byte[] hash = digest.digest(Iurl.getBytes());
		        String base64 = Base64.getUrlEncoder().encodeToString(hash);
		        customAlias = base64.substring(0, 8); // Shorten to 8 chars
		    } catch (Exception e) {
		        throw new RuntimeException("Error generating hash", e);
		    }
		}
		String shortCode=customAlias;
		surl.setOriginalUrl(longUrl);
		surl.setCreatedAt(LocalDateTime.now());
		surl.setExpiryAt(expiry);
		surl.setShortCode(customAlias);
		repo.save(surl);
		return shortCode;
		
	}
	public String getUrl(String shortCode) {
		String orgUrl = repo.findByShortCode(shortCode);
		return orgUrl;
	}
	public void getCount(String shortCode) {
		long count = repo.findClickCount(shortCode);
		surl.setClickCount(count);
		setCount(shortCode);
		
	}
	public void setCount(String shortCode) {
		long count = surl.getClickCount()+1;
		repo.updateCount(count, shortCode);
		System.out.println(count);
	}
	public boolean isExpired(String shortCode) {
		LocalDateTime expiry = repo.findexpiryAt(shortCode);
		surl.setExpiryAt(expiry);
		if (surl.getExpiryAt() != null && surl.getExpiryAt().isAfter(LocalDateTime.now())) {
			return false;
		}
		return true;
	}
	
}
