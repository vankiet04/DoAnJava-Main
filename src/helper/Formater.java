/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package helper;

/**
 *
 * @author KIET
 */

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author Tran Nhat Sinh
 */
public class Formater {

    public static String FormatVND(long vnd) {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format(vnd) + " VND";
    }
    
    public static String FormatTime(Timestamp thoigian) {
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/YYYY HH:mm");
        return formatDate.format(thoigian);
    }
}
