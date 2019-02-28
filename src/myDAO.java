import java.util.List;

public interface myDAO {
    public List<Employee> getAllEmployee();
    public void addEmployee(Employee newEmp);
    public void updateEmployee(Employee newEmp, Employee oldEmp);
    public void deleteEmployee(Employee newEmp, Employee oldEmp);
    public Employee getEmployee(int id);

}
