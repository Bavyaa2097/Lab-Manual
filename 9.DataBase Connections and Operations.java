import java.sql.*;

public class StudentJDBC {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/college";
        String user = "root";
        String password = "root";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(url, user, password);

            // Insert Records
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO student VALUES(?,?,?,?)");

            ps.setInt(1, 101);
            ps.setString(2, "Rahul");
            ps.setString(3, "CSE");
            ps.setInt(4, 87);
            ps.executeUpdate();

            ps.setInt(1, 102);
            ps.setString(2, "Sneha");
            ps.setString(3, "ISE");
            ps.setInt(4, 91);
            ps.executeUpdate();

            System.out.println("Records Inserted Successfully.");

            // Update Marks
            PreparedStatement update = con.prepareStatement(
                "UPDATE student SET marks=? WHERE rollno=?");

            update.setInt(1, 95);
            update.setInt(2, 101);
            update.executeUpdate();

            System.out.println("Record Updated Successfully.");

            // Search Student
            PreparedStatement search = con.prepareStatement(
                "SELECT * FROM student WHERE rollno=?");

            search.setInt(1, 101);

            ResultSet rs = search.executeQuery();

            while (rs.next()) {
                System.out.println("\nStudent Details");
                System.out.println("Roll No : " + rs.getInt(1));
                System.out
