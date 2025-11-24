package com.openclassrooms.mddapi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/article")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ArticleController {
	
	

}
