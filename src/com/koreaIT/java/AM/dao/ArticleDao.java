package com.koreaIT.java.AM.dao;

import com.koreaIT.java.AM.dto.Article;

import java.util.ArrayList;
import java.util.List;

public class ArticleDao extends Dao {
  public List<Article> articles;

  public ArticleDao() {
    articles = new ArrayList<>();
  }

  public void add(Article article) {
    articles.add(article);
    lastId++;
  }
}
