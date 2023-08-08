package com.koreaIT.java.AM;

import com.koreaIT.java.AM.controller.ArticleController;
import com.koreaIT.java.AM.controller.Controller;
import com.koreaIT.java.AM.controller.MemberController;

import java.util.Scanner;

public class App {

  public App() {
  }

  public void start() {
    System.out.println("== 프로그램 시작 ==");

    Scanner sc = new Scanner(System.in);
    MemberController memberController = new MemberController(sc);
    ArticleController articleController = new ArticleController(sc);

    articleController.makeTestData();
    memberController.makeTestData();

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

      String[] cmdBits = cmd.split(" ");

      if (cmdBits.length == 1) {
        System.out.println("명령어가 존재하지 않습니다");
        continue;
      }

      String controllerName = cmdBits[0];
      String actionMethodName = cmdBits[1];

      Controller controller = null;

      if (controllerName.equals("article")) {
        controller = articleController;
      } else if (controllerName.equals("member")) {
        controller = memberController;
      } else {
        System.out.println("명령어가 존재하지 않습니다");
        continue;
      }

      String actionName = controllerName + "/" + actionMethodName;

      switch (actionName) {
        case "article/write":
        case "article/delete":
        case "article/modify":
        case "member/logout":
          if (Controller.isLogined() == false) {
            System.out.println("로그인 후 이용하세요");
            continue;
          }

          break;
      }

      switch (actionName) {
        case "member/join":
        case "member/login":
          if (Controller.isLogined()) {
            System.out.println("로그아웃 후 이용하세요");
            continue;
          }

          break;
      }
      controller.doAction(cmd, actionMethodName);
    }

    sc.close();
  }

}