package com.koreaIT.java.AM.dao;

import com.koreaIT.java.AM.dto.Member;

import java.util.ArrayList;
import java.util.List;

public class MemberDao {
  public List<Member> members;

  public MemberDao() {
    members = new ArrayList<>();
  }
}
