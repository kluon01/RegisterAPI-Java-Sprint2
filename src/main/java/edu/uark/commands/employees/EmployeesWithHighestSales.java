package edu.uark.commands.employees;

import edu.uark.commands.ResultCommandInterface;
import edu.uark.models.api.Employee;
import edu.uark.models.repositories.EmployeeRepository;
import edu.uark.models.repositories.interfaces.EmployeeRepositoryInterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeesWithHighestSales implements ResultCommandInterface<List<Employee>>
{
    private EmployeeRepositoryInterface employeeRepository;
    private ArrayList<Employee> allemployees;

    @Override
    public List<Employee> execute()
    {
        List<Employee> topemployee = new ArrayList<Employee>();
        int count = 10;

        sortEmployeesBySales(allemployees);

        if(allemployees.size()< 10)
            count = allemployees.size();

        for(int x = 0; x < count; x++)
            topemployee.add(allemployees.get(x));

        return topemployee;
    }

    public static void sortEmployeesBySales(List<Employee> p)
    {
        Collections.sort(p, new Comparator<Employee>()
        {
            @Override
            public int compare(Employee o1, Employee o2)
            {
                return Integer.compare(o2.getSales(), o1.getSales());
            }
        });
    }

    //Properties
    public EmployeesWithHighestSales(List<Employee> p)
    {
        this.allemployees = new ArrayList<Employee>(p);

        this.employeeRepository = new EmployeeRepository();
    }
}
