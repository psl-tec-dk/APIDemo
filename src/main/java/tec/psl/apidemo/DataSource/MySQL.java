package tec.psl.apidemo.DataSource;

import com.mysql.cj.protocol.Resultset;
import tec.psl.apidemo.DataModels.Person;

import java.sql.*;
import java.util.ArrayList;

public class MySQL {

    private String connStr = "jdbc:mysql://localhost/demoapidb";
    private String usr = "wwwuser";
    private String pwd = "wwwuser";
    private Connection conn = null;

    private void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(connStr, usr, pwd);
            System.out.println("Connected..");
        }
        catch (ClassNotFoundException | SQLException e) {
            System.out.println("Fejl med driver-loading eller connection. " + e.getMessage());
        }
    }

    private void closeConn() {
        try {
            conn.close();
        }
        catch (SQLException e) {
            //throw new RuntimeException(e);
        }
    }

    public ArrayList<Person> getAllPersons() {
        connect();
        ArrayList<Person> pList = new ArrayList<>();
        String sql = "select * from persons";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                Person p = new Person(
                        rs.getInt("persId"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getBoolean("student"),
                        rs.getString("lastUpdated")
                );
                //System.out.println(p.toString());
                pList.add(p);
            }
        }
        catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }

        closeConn();
        return pList;
    }

    public Person addPerson(Person person) {
        Person p = null;
        connect();
        String sql = " insert into persons (firstName, lastName, student) values(?,?,?)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, person.getFirstName());
            pstmt.setString(2, person.getLastName());
            pstmt.setBoolean(3, person.isStudent());
            pstmt.executeUpdate();
            Statement stmt = conn.createStatement();
            ResultSet pid = stmt.executeQuery("select last_insert_id()");
            pid.next();
            int id = pid.getInt(1);
            p = getPersonById(id);
        }
        catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }
        closeConn();
        return p;
    }

    public Person getPersonById(int persId){

        connect();
        Person p = null;
        String sql = "select * from persons where persId = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, persId);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                p = new Person(
                        rs.getInt("persId"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getBoolean("student"),
                        rs.getString("lastUpdated")
                );
            }
        }
        catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }
        closeConn();
        return p;
    }

    public Person updatePerson(int persId, Person person) {
        connect();
        Person p = null;
        String sql = "update persons set firstName = ?, lastName = ?, student = ? where persId = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, person.getFirstName());
            pstmt.setString(2, person.getLastName());
            pstmt.setBoolean(3, person.isStudent());
            pstmt.setInt(4, persId);
            pstmt.executeUpdate();
            p = getPersonById(persId);
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        closeConn();
        return p;
    }

    public int deletePerson(int persId) {
        connect();
        int res = 0;
        String sql = "delete from persons where persId = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, persId);
            res = pstmt.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        closeConn();
        return res;
    }

}
