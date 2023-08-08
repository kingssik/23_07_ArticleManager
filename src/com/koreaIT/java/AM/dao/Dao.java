package com.koreaIT.java.AM.dao;

public abstract class Dao {
  public int lastId;

  Dao() {
    lastId = 0;
  }

  public int getLastId() {
    return lastId;
  }

  public int getNewId() {
    return lastId + 1;
  }
}
