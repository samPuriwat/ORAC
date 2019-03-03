import java.util.List;

public class TestJDBC2 {
    public static void main(String[] args) {
        //GET_ALL_EMP
        MyDAOImpl dao = MyDAOImpl.getInstant();

        showAllData(dao);
        //ADD_NEW_EMP
        dao.addEmployee(new Employee(103,"Jiraporn ThomKaew","Programmer",30000.00));

        //FIND_BY_ID
        Employee emp = dao.getEmployee(101);
        if (emp ==null)
            System.out.println("Not found.");
        else
         System.out.println(emp.toString());

        //UPDATE_EMP (Update)
        emp.setSalary(40000);
        dao.updateEmployee(emp);
        System.out.println("After updated.");
        showAllData(dao);

        //DELETE_EMP (Delete)
        dao.deleteEmployee(103);
        showAllData(dao);


    }//main

    private static void showAllData(MyDAOImpl dao) {
        List<Employee> allEmp =  dao.getAllEmployee();

        for (Employee emp: allEmp) {
            System.out.println(emp.toString());
        }
    }

}//class
