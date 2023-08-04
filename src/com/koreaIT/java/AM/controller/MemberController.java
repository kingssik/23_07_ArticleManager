package com.koreaIT.java.AM.controller;

import com.koreaIT.java.AM.dto.Member;
import com.koreaIT.java.AM.util.Util;

import java.util.List;
import java.util.Scanner;

public class MemberController extends Controller {
  private List<Member> members;
  private String cmd;
  private Scanner sc;
  private String actionMethodName;

  @Override
  public void doAction(String cmd, String actionMethodName) {
    this.cmd = cmd;
    this.actionMethodName = actionMethodName;

    switch (actionMethodName) {
      case "join":
        doJoin();
        break;
    }
  }

  public MemberController(Scanner sc, List<Member> members) {
    this.sc = sc;
    this.members = members;
  }

  public void doJoin() {
    int id = members.size() + 1;

    String regDate = Util.getNowDateStr();
    String loginId = null;

    while (true) {
      System.out.printf("로그인 아이디 : ");
      loginId = sc.nextLine();

      if (isJoinableLoginId(loginId) == false) {
        System.out.printf("%s(은)는 사용중인 아이디입니다\n", loginId);
        continue;
      }
      break;
    }

    String loginPw = null;
    String loginPwCheck = null;

    while (true) {
      System.out.printf("로그인 비밀번호 : ");
      loginPw = sc.nextLine();
      System.out.printf("로그인 비밀번호 확인 : ");
      loginPwCheck = sc.nextLine();

      if (loginPw.equals(loginPwCheck) == false) {
        System.out.printf("비밀번호를 다시 입력하세요\n");
        continue;
      }
      break;
    }

    System.out.printf("이름 : ");
    String name = sc.nextLine();

    Member member = new Member(id, regDate, loginId, loginPw, name);
    members.add(member);

    System.out.printf("%d번 회원이 가입했습니다\n", id);
  }

  private boolean isJoinableLoginId(String loginId) {
    int index = getMemberIndexByLoginId(loginId);

    if (index == -1) {
      return true;
    }
    return false;
  }

  private int getMemberIndexByLoginId(String loginId) {
    int i = 0;

    for (Member member : members) {
      if (member.loginId.equals(loginId)) {
        return i;
      }
      i++;
    }
    return -1;
  }
}
