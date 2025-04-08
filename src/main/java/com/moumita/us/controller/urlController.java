package com.moumita.us.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.view.RedirectView;

import com.moumita.us.model.ShortUrl;
import com.moumita.us.service.GenerateUrl;

@Controller
public class urlController {
	
	@Autowired
	GenerateUrl url;
	
	 @GetMapping("/")
	    public String showForm(Model model) {
	        return "index"; // will render templates/index.html
	    }
	 
	 @GetMapping("/shorten")
	    public String home(Model model) {
	        return "index"; // will render templates/index.html
	    }
	
	 @PostMapping("/shorten")
     public String shortenUrl(@RequestParam String longUrl,
                             @RequestParam(required = false) String customAlias,
                             @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime expiry,
                             Model model) {
        String shortCode = url.generateShortCode(longUrl, customAlias, expiry);
        model.addAttribute("shortUrl", shortCode);
        return "index";
     }
	 
	 @GetMapping("/{shortCode}")
	 public RedirectView redirectToLongUrl(@PathVariable String shortCode) {
		 RedirectView rview =  new RedirectView();
		 String orgUrl = url.getUrl(shortCode);
		 url.getCount(shortCode);
		 rview.setUrl(orgUrl);
		 return rview;
	 }
	 
//	 @GetMapping("/{shortCode}/stats")
//	 public String showStats(@PathVariable String shortCode, Model model) {
//		 ShortUrl Orgurl = url.getUrl(shortCode)
//	             .orElseThrow(() -> new RuntimeException("Not found"));
//	     model.addAttribute("url", Orgurl);
//	     return "stats";
//	 }


}
