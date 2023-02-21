package model.utils;

import exceptions.MyException;
import javafx.util.Pair;
import model.statement.IStatement;

import java.util.*;

public interface MyIProcTable {
    boolean isDefined(String key);
    void put(String key, Pair<List<String>, IStatement> value);
    Pair<List<String>, IStatement> lookUp(String key) throws MyException;
    void update(String key,  Pair<List<String>, IStatement> value) throws MyException;
    Collection< Pair<List<String>, IStatement>> values();
    void remove(String key) throws MyException;
    Set<String> keySet();
    HashMap<String,  Pair<List<String>, IStatement>> getContent();
    MyIDictionary<String, Pair<List<String>, IStatement>> deepCopy() throws MyException;
}