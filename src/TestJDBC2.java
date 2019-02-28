import java.util.List;

public class TestJDBC2 {
    public static void main(String[] args) {
        //GET_ALL_EMP
        MyDAOImpl dao = MyDAOImpl.getInstant();
        List<Employee> allEmp =  dao.getAllEmployee();

        for (Employee emp: allEmp) {
            System.out.println(emp.toString());
        }
        //ADD_NEW_EMP
        dao.addEmployee(new Employee(104,"Jiraporn ThomKaew","Programmer",30000.00));

        System.out.println(dao.getEmployee(102).toString());


    }//main

}//class
