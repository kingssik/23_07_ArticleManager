package com.koreaIT.java.AM.controller;

import com.koreaIT.java.AM.container.Container;
import com.koreaIT.java.AM.dto.Member;
import com.koreaIT.java.AM.util.Util;

import java.util.List;
import java.util.Scanner;

public class MemberController extends Controller {
  private List<Member> members;
  private String cmd;
  private Scanner sc;
  private String actionMethodName;


  public MemberController(Scanner sc) {
    this.sc = sc;
    members = Container.memberDao.members;
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
    loginedMember = null;

    System.out.println("로그아웃 되었습니다");
  }

  private void doLogin() {

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
    int id = Container.memberDao.getNewId();

    String regDate = Util.getNowDateStr();
    String loginId = null;

    while (true) {
      System.out.printf("로그인 아이디 : ");
      loginId = sc.nextLine().trim();

      if (loginId.length() == 0) {
        System.out.println("아이디를 입력하세요");
        continue;
      }

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

      if (loginPw.length() == 0) {
        System.out.println("비밀번호를 입력하세요");
        continue;
      }

      System.out.printf("로그인 비밀번호 확인 : ");
      loginPwCheck = sc.nextLine();

      if (loginPw.equals(loginPwCheck) == false) {
        System.out.printf("비밀번호를 다시 입력하세요\n");
        continue;
      }
      break;
    }

    String name = null;

    while (true) {
      System.out.printf("이름 : ");
      name = sc.nextLine().trim();

      if (name.length() == 0) {
        System.out.println("이름을 입력하세요");
        continue;
      }

      break;
    }

    Member member = new Member(id, regDate, loginId, loginPw, name);
    Container.memberDao.add(member);

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
    Container.memberDao.add(new Member(Container.memberDao.getNewId(), Util.getNowDateStr(), "admin", "admin", "관리자"));
    Container.memberDao.add(new Member(Container.memberDao.getNewId(), Util.getNowDateStr(), "test1", "test1", "회원1"));
    Container.memberDao.add(new Member(Container.memberDao.getNewId(), Util.getNowDateStr(), "test2", "test2", "회원2"));
  }
}
