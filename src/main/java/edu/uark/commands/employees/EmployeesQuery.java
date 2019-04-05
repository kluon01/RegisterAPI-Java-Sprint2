package edu.uark.commands.employees;

import java.util.List;
import java.util.stream.Collectors;

import edu.uark.commands.ResultCommandInterface;
import edu.uark.models.api.Employee;
import edu.uark.models.repositories.EmployeeRepository;
import edu.uark.models.repositories.interfaces.EmployeeRepositoryInterface;

public class EmployeesQuery implements ResultCommandInterface<List<Employee>> {
    @Override
    public List<Employee> execute()
    {
        return this.employeeRepository.
                all().
                stream().
                map(mp -> (new Employee(mp))).
                collect(Collectors.toList());
    }

    //Properties
    private EmployeeRepositoryInterface employeeRepository;
    public EmployeeRepositoryInterface getEmployeetRepository() {
        return this.employeeRepository;
    }
    public EmployeesQuery setEmployeeRepository(EmployeeRepositoryInterface productRepository)
    {
        this.employeeRepository = productRepository;
        return this;
    }

    public EmployeesQuery() {
        this.employeeRepository = new EmployeeRepository();
    }
}
