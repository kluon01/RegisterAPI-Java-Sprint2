package edu.uark.commands.employees;

import org.apache.commons.lang3.StringUtils;

import edu.uark.commands.ResultCommandInterface;
import edu.uark.controllers.exceptions.UnauthorizedException;
import edu.uark.controllers.exceptions.UnprocessableEntityException;
import edu.uark.models.api.Employee;
import edu.uark.models.api.EmployeeSignIn;
import edu.uark.models.entities.EmployeeEntity;
import edu.uark.models.repositories.EmployeeRepository;
import edu.uark.models.repositories.interfaces.EmployeeRepositoryInterface;

public class EmployeeSignInCommand implements ResultCommandInterface<Employee> {
	@Override
	public Employee execute() {
		int employeeId = -1;

		//Validations
		if (StringUtils.isBlank(this.employeeSignIn.getEmployeeId())) {
			throw new UnprocessableEntityException("employee ID");
		}
		try {
			employeeId = Integer.parseInt(this.employeeSignIn.getEmployeeId());
		} catch (NumberFormatException e) {
			throw new UnprocessableEntityException("employee ID");
		}
		
		EmployeeEntity employeeEntity = this.employeeRepository.byEmployeeId(employeeId);

		if ((employeeEntity == null) || !employeeEntity.getPassword().equals(EmployeeEntity.hashPassword(this.employeeSignIn.getPassword()))) {
			throw new UnauthorizedException();
		}

		return new Employee(employeeEntity);
	}

	//Properties
	private EmployeeSignIn employeeSignIn;
	public EmployeeSignIn getEmployeeSignIn() {
		return this.employeeSignIn;
	}
	public EmployeeSignInCommand setEmployeeSignIn(EmployeeSignIn employeeSignIn) {
		this.employeeSignIn = employeeSignIn;
		return this;
	}
	
	private EmployeeRepositoryInterface employeeRepository;
	public EmployeeRepositoryInterface getProductRepository() {
		return this.employeeRepository;
	}
	public EmployeeSignInCommand setProductRepository(EmployeeRepositoryInterface employeeRepository) {
		this.employeeRepository = employeeRepository;
		return this;
	}
	
	public EmployeeSignInCommand() {
		this.employeeSignIn = new EmployeeSignIn();
		this.employeeRepository = new EmployeeRepository();
	}
}
