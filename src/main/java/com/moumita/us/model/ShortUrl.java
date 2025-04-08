package com.moumita.us.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;

//@Data
@Entity
@Table(name = "tbl_short_url")
public class ShortUrl {
	
	@Id
	@Column(name = "Url_id")
	private int UrlId;
	@Column(name = "short_code")
    private String shortCode; // either generated or custom alias
	@Column(name = "original_url")
    private String originalUrl;
    private LocalDateTime createdAt;
    private LocalDateTime expiryAt;

    private long clickCount;
    private String ipAddress;
	
    
	public String getShortCode() {
		return shortCode;
	}
	public void setShortCode(String shortCode) {
		this.shortCode = shortCode;
	}
	public String getOriginalUrl() {
		return originalUrl;
	}
	public void setOriginalUrl(String originalUrl) {
		this.originalUrl = originalUrl;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getExpiryAt() {
		return expiryAt;
	}
	public void setExpiryAt(LocalDateTime expiryAt) {
		this.expiryAt = expiryAt;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public long getClickCount() {
		return clickCount;
	}
	public void setClickCount(long clickCount) {
		this.clickCount = clickCount;
	}
	

	@Override
	public String toString() {
		return "ShortUrl [UrlId=" + UrlId + ", shortCode=" + shortCode + ", originalUrl=" + originalUrl + ", createdAt="
				+ createdAt + ", expiryAt=" + expiryAt + ", clickCount=" + clickCount + ", ipAddress=" + ipAddress
				+ "]";
	}

}
