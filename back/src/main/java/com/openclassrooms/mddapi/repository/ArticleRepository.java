package com.openclassrooms.mddapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openclassrooms.mddapi.models.Article;

public interface ArticleRepository extends JpaRepository<Article, Long>{

}
