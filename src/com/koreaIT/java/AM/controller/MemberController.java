package com.koreaIT.java.AM.controller;

import com.koreaIT.java.AM.dto.Member;
import com.koreaIT.java.AM.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemberController extends Controller {
  private List<Member> members;
  private String cmd;
  private Scanner sc;
  private String actionMethodName;


  public MemberController(Scanner sc) {
    this.sc = sc;
    members = new ArrayList<>();
  }

  @Override
  public void doAction(String cmd, String actionMethodName) {
    this.cmd = cmd;
    this.actionMethodName = actionMethodName;

    switch (actionMethodName) {
      case "join":
        doJoin();
        break;
      case "login":
        doLogin();
        break;
      case "logout":
        doLogout();
        break;
      default:
        System.out.println("존재하지 않는 명령어 입니다");
        break;
    }
  }

  private void doLogout() {
    if (isLogined() == false) {
      System.out.println("로그아웃 상태입니다");
      return;
    }

    loginedMember = null;

    System.out.println("로그아웃 되었습니다");
  }

  private void doLogin() {
    if (isLogined()) {
      System.out.println("로그아웃 후 이용하세요");
      return;
    }

    System.out.printf("로그인 아이디 : ");
    String loginId = sc.nextLine();
    System.out.printf("로그인 비밀번호 : ");
    String loginPw = sc.nextLine();

    // 사용자가 입력한 아이디에 해당하는 회원 존재 여부 확인
    Member member = getMemberByLoginId(loginId);

    if (member == null) {
      System.out.println("존재하지 않는 회원입니다");
      return;
    }

    if (member.loginPw.equals(loginPw) == false) {
      System.out.println("비밀번호를 확인하세요");
      return;
    }

    loginedMember = member;
    System.out.printf("%s님이 로그인에 성공하였습니다\n", loginedMember.name);
  }

  private void doJoin() {
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

  private Member getMemberByLoginId(String loginId) {
    int idx = getMemberIndexByLoginId(loginId);

    if (idx == -1) {
      return null;
    }
    return members.get(idx);
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

  public void makeTestData() {
    System.out.println("회원 테스트데이터를 생성합니다");
    members.add(new Member(1, Util.getNowDateStr(), "admin", "admin", "관리자"));
    members.add(new Member(2, Util.getNowDateStr(), "test1", "test1", "회원1"));
    members.add(new Member(3, Util.getNowDateStr(), "test2", "test2", "회원2"));
  }
}
