package com.koreaIT.java.AM;

import com.koreaIT.java.AM.controller.ArticleController;
import com.koreaIT.java.AM.controller.MemberController;
import com.koreaIT.java.AM.dto.Article;
import com.koreaIT.java.AM.dto.Member;
import com.koreaIT.java.AM.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
  private List<Article> articles;
  private List<Member> members;

  public App() {
    articles = new ArrayList<>();
    members = new ArrayList<>();
  }

  public void start() {
    System.out.println("== 프로그램 시작 ==");
    makeTestData();

    Scanner sc = new Scanner(System.in);
    MemberController memberController = new MemberController(sc, members);
    ArticleController articleController = new ArticleController(sc, articles);

    while (true) {
      System.out.printf("명령어 ) ");
      String cmd = sc.nextLine().trim();

      if (cmd.length() == 0) {
        System.out.println("명령어를 입력하세요");
        continue;
      }

      if (cmd.equals("system exit")) {
        System.out.println("== 프로그램 종료 ==");
        break;
      }

      if (cmd.equals("member join")) {
        memberController.doJoin();

      } else if (cmd.equals("article write")) {
        articleController.doWrite();

      } else if (cmd.startsWith("article list")) {
        articleController.showList(cmd);

      } else if (cmd.startsWith("article detail ")) {
        articleController.showDetail(cmd);

      } else if (cmd.startsWith("article modify ")) {
        articleController.doModify(cmd);

      } else if (cmd.startsWith("article delete ")) {
        articleController.doDelete(cmd);

      } else {
        System.out.println("없는 명령어입니다");
      }
    }

    sc.close();
  }

  private void makeTestData() {
    System.out.println("테스트데이터를 생성합니다");
    articles.add(new Article(1, Util.getNowDateStr(), "title1", "body1", 11));
    articles.add(new Article(2, Util.getNowDateStr(), "title2", "body2", 22));
    articles.add(new Article(3, Util.getNowDateStr(), "title3", "body3", 33));
  }
}