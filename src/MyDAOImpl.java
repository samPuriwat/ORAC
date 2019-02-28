import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MyDAOImpl implements myDAO {

    private static String driverName = "org.sqlite.JDBC";
    private static String url = "jdbc:sqlite:D:/company.sqlite";

    //constant operation
    private static final String GET_ALL_EMP = "select * from employee";
    private static final String FIND_BY_ID = "select * from employee where id = ?";
    private static final String ADD_NEW_EMP = "insert into employee (id,name,position,salary) values (?,?,?,?)";
    private static final String UPDATE_EMP = "select * from employee";
    private static final String DELETE_EMP = "select * from employee";


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
        Connection conn = null;
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
        Connection conn = null;

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
    public void updateEmployee(Employee newEmp, Employee oldEmp) {

    }

    @Override
    public void deleteEmployee(Employee newEmp, Employee oldEmp) {

    }

    @Override
    public Employee getEmployee(int idd) {
        Connection conn = null;
        Employee emp = null;

        try {
            conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(FIND_BY_ID);

            while (rs.next()) {
                int id = rs.getInt(idd);
                String name = rs.getString(2);
                String position = rs.getString(3);
                double salary = rs.getDouble(4);

                emp = new Employee(id, name, position, salary);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emp;

    }
}
