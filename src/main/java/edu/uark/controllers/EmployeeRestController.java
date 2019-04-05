package edu.uark.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.uark.commands.employees.*;
import edu.uark.commands.employees.ActiveEmployeeExistsQuery;
import edu.uark.commands.employees.EmployeeCreateCommand;
import edu.uark.commands.employees.EmployeeDeleteCommand;
import edu.uark.commands.employees.EmployeeQuery;
import edu.uark.commands.employees.EmployeeSignInCommand;
import edu.uark.commands.employees.EmployeeUpdateCommand;
import edu.uark.models.api.Employee;
import edu.uark.models.api.EmployeeSignIn;

@RestController
@RequestMapping(value = "/api/employee")
public class EmployeeRestController
{
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<Employee> getEmployees()
	{
		return (new EmployeesQuery()).execute();
	}

	@RequestMapping(value = "/{employeeId}", method = RequestMethod.GET)
	public Employee getEmployee(@PathVariable UUID employeeId)
	{
		return (new EmployeeQuery())
			.setEmployeeId(employeeId)
			.execute();
	}

	@RequestMapping(value = "/byHighestSales", method = RequestMethod.GET)
	public List<Employee> getEmployeeWithHighestSales()
	{
		return (new EmployeesWithHighestSales(getEmployees())).
				execute();
	}
	
	@RequestMapping(value = "/activeexists", method = RequestMethod.GET)
	public void getActiveEmployeeExists()
	{
		(new ActiveEmployeeExistsQuery())
			.execute();
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public Employee createEmployee(@RequestBody Employee employee)
	{
		return (new EmployeeCreateCommand())
			.setApiEmployee(employee)
			.execute();
	}

	@RequestMapping(value = "/{employeeId}", method = RequestMethod.PUT)
	public Employee updateEmployee(@PathVariable UUID employeeId, @RequestBody Employee employee)
	{
		return (new EmployeeUpdateCommand())
			.setEmployeeId(employeeId)
			.setApiEmployee(employee)
			.execute();
	}

	@RequestMapping(value = "/{employeeId}", method = RequestMethod.DELETE)
	public void deleteEmployee(@PathVariable UUID employeeId)
	{
		(new EmployeeDeleteCommand())
			.setEmployeeId(employeeId)
			.execute();
	}

	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public Employee employeeSignIn(@RequestBody EmployeeSignIn employeeSignIn)
	{
		return (new EmployeeSignInCommand())
			.setEmployeeSignIn(employeeSignIn)
			.execute();
	}

	@ResponseBody
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test() {
		return "Successful test. (EmployeeRestController)";
	}
}
