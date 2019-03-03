import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MyDAOImpl implements myDAO {

    private static String driverName = "org.sqlite.JDBC";
    private static String url = "jdbc:sqlite:E:/company.sqlite";
    private static Connection conn = null;

    //constant operation
    private static final String GET_ALL_EMP = "select * from employee";
    private static final String FIND_BY_ID = "select * from employee where id = ?";
    private static final String ADD_NEW_EMP = "insert into employee (id,name,position,salary) values (?,?,?,?)";
    private static final String UPDATE_EMP = "update employee set name=?, position=?, salary=? where id=?";
    private static final String DELETE_EMP = "delete from employee where id = ?";


    private static MyDAOImpl instant = new MyDAOImpl();

    private MyDAOImpl() {

        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Load Driver successfully.");

    }

    public static MyDAOImpl getInstant() {
        return instant;
    }


    @Override
    public List<Employee> getAllEmployee() {
        List<Employee> emp = new ArrayList<>();
        try {
            //step 3: get connection
            conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(GET_ALL_EMP);
            //step 4 : viewing data
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String position = rs.getString(3);
                double salary = rs.getDouble(4);

                emp.add(new Employee(id, name, position, salary));
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return emp;
    }

    @Override
    public void addEmployee(Employee newEmp) {
        try {
            conn = DriverManager.getConnection(url);
            PreparedStatement stmt = conn.prepareStatement(ADD_NEW_EMP);
            //int x = stmt.executeUpdate(ADD_NEW_EMP);
            stmt.setInt(1, newEmp.getId());
            stmt.setString(2, newEmp.getName());
            stmt.setString(3, newEmp.getPosition());
            stmt.setDouble(4, newEmp.getSalary());

            boolean ex = stmt.execute();
            if (ex == false) System.out.println("Add data already.");
            else System.out.println("Could not add data.");

            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateEmployee(Employee emp) {
        try {
            conn = DriverManager.getConnection(url);
            PreparedStatement ps = conn.prepareStatement(UPDATE_EMP);
            ps.setString(1, emp.getName());
            ps.setString(2, emp.getPosition());
            ps.setDouble(3, emp.getSalary());
            ps.setInt(4, emp.getId());
            int result = ps.executeUpdate();
            if (result != 0 )
                System.out.println("Employee with id "+emp.getId()+ " was updated following details:\n "+emp.toString());
            else
                System.out.println("Employee data was not updated.");
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteEmployee(int id) {
        try {
            conn = DriverManager.getConnection(url);
            PreparedStatement ps = conn.prepareStatement(DELETE_EMP);
            ps.setInt(1, id);
            int resultSet = ps.executeUpdate();
            if(resultSet != 0) {
                System.out.println("Employee deleted with id = "+id);
            }
            else {
                System.out.println("Not found employee id = " + id);

            }
            ps.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Employee getEmployee(int idd) {
        Employee emp = null;
        try {
            conn = DriverManager.getConnection(url);
            PreparedStatement ps = conn.prepareStatement(FIND_BY_ID);
            ps.setInt(1, idd);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String position = rs.getString(3);
                double salary = rs.getDouble(4);

                emp = new Employee(id, name, position, salary);
            } else {
                System.out.println("Employee with id = " + idd + " not found.");
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException  | NullPointerException e ) {
            e.printStackTrace();
        }
        return emp;
    }

}
