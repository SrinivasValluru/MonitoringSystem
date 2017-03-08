package com.monitor.system.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by IntelliJ IDEA.
 * User: user
 * Date: 11/15/15
 * Time: 11:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class Test {
    public static void main(String[] args) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("/spring/applicationContext.xml");
        SystemDetailsService service = (SystemDetailsService) context.getBean("systemDetailsService");
        //service.loadAllSystemDetails();
        /*Class.forName("com.mysql.jdbc.Driver").newInstance();
        String cs = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String password = "root";
        Connection con = DriverManager.getConnection(cs, user, password);
        if (con == null) {
            System.out.println("connection not established");
        } else {
            System.out.println(con);
            DatabaseMetaData db = con.getMetaData();
            System.out.println("db===========>" + db.getDatabaseProductName());
        }*/
    }
}
