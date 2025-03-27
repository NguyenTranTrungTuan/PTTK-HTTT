package qlkho.dao;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

public interface DAOInterface<T> {
    public void insert(T obj);

    public void update(T obj);

    public void delete(T obj);

    public ArrayList<T> selectAll();

    public T selectById(T obj);

    public ArrayList<T> selectByCondtion(String condition);

    public DefaultTableModel loadDataToTable(String tableName);
}