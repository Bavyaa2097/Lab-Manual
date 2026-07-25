import java.util.*;
import java.util.stream.Collectors;

class Employee {
    private int id;
    private String name;
    private String department;
    private double salary;

    public Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }

    @Override
    public String toString() {
        return id + "   " + name + "   " + department + "   " + salary;
    }
}

public class EmployeeAnalytics {
    public static void main(String[] args) {
        // Step 4: Initialize a list of employees using Arrays.asList()
        List<Employee> employees = Arrays.asList(
            new Employee(101, "Rahul", "CSE", 55000.0),
            new Employee(102, "Sneha", "ECE", 62000.0),
            new Employee(103, "Kiran", "CSE", 48000.0),
            new Employee(104, "Divya", "MECH", 51000.0),
            new Employee(105, "Arjun", "ECE", 70000.0)
        );

        // Step 5: Print all employees
        System.out.println("---- All Employees ----");
        employees.forEach(emp -> System.out.println(emp));
        System.out.println();

        // Step 6: Salary Above 50000 sorted in descending order
        System.out.println("---- Salary Above 50000 (High to Low) ----");
        employees.stream()
                .filter(e -> e.getSalary() > 50000)
                .sorted((e1, e2) -> Double.compare(e2.getSalary(), e1.getSalary()))
                .forEach(e -> System.out.println(e.getName() + " -> " + e.getSalary()));
        System.out.println();

        // Step 7: Collect all employee names into a List using map()
        System.out.println("---- Employee Names ----");
        List<String> names = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());
        System.out.println(names);
        System.out.println();

        // Step 8: Group employee names by department
        System.out.println("---- Employees Grouped by Department ----");
        Map<String, List<String>> employeesByDept = employees.stream()
                .collect(Collectors.groupingBy(
                    Employee::getDepartment,
                    Collectors.mapping(Employee::getName, Collectors.toList())
                ));
        employeesByDept.forEach((dept, nameList) -> System.out.println(dept + " : " + nameList));
        System.out.println();

        // Step 9: Compute average salary per department
        System.out.println("---- Average Salary per Department ----");
        Map<String, Double> avgSalaryByDept = employees.stream()
                .collect(Collectors.groupingBy(
                    Employee::getDepartment,
                    Collectors.averagingDouble(Employee::getSalary)
                ));
        avgSalaryByDept.forEach((dept, avgSal) -> 
            System.out.printf("%s : %.2f\n", dept, avgSal)
        );
        System.out.println();

        // Step 10: Compute total salary using reduce() with Double::sum
        double totalSalary = employees.stream()
                .map(Employee::getSalary)
                .reduce(0.0, Double::sum);
        System.out.println("Total Salary Paid : " + totalSalary);

        // Step 11: Count CSE employees
        long cseCount = employees.stream()
                .filter(e -> e.getDepartment().equalsIgnoreCase("CSE"))
                .count();
        System.out.println("Number of CSE Employees : " + cseCount);

        // Step 11 (cont.): Find highest-paid employee using max() with Comparator and Optional
        Optional<Employee> highestPaid = employees.stream()
                .max(Comparator.comparingDouble(Employee::getSalary));
        
        highestPaid.ifPresent(e -> 
            System.out.println("Highest Paid : " + e.getName() + " (" + e.getSalary() + ")")
        );
    }
}
