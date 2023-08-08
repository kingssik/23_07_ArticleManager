package com.koreaIT.java.AM.container;

import com.koreaIT.java.AM.dao.ArticleDao;
import com.koreaIT.java.AM.dao.MemberDao;

public class Container {
  public static ArticleDao articleDao;
  public static MemberDao memberDao;

  static {
    articleDao = new ArticleDao();
    memberDao = new MemberDao();
  }
}
