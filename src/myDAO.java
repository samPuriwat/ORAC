import java.util.List;

public interface myDAO {
    public List<Employee> getAllEmployee();
    public void addEmployee(Employee newEmp);
    public void updateEmployee(Employee emp);
    public void deleteEmployee(int id);
    public Employee getEmployee(int id);

}
