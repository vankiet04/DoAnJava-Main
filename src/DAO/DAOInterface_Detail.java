/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.util.ArrayList;

/**
 *
 * @author Kiet
 */
public interface DAOInterface_Detail<T> {
    public int insert(ArrayList<T> t);
    public void delete(String t1, String t2);
    public int update(ArrayList<T> t, String pk);
    public ArrayList<T> selectAll(String t);
}
