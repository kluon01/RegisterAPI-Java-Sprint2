package edu.uark.commands.employees;

import edu.uark.commands.VoidCommandInterface;
import edu.uark.controllers.exceptions.NotFoundException;
import edu.uark.models.repositories.EmployeeRepository;
import edu.uark.models.repositories.interfaces.EmployeeRepositoryInterface;

public class ActiveEmployeeExistsQuery implements VoidCommandInterface {
	@Override
	public void execute() {
		if (!this.employeeRepository.activeExists()) {
			throw new NotFoundException("Employee");
		}
	}
	
	private EmployeeRepositoryInterface employeeRepository;
	public EmployeeRepositoryInterface getProductRepository() {
		return this.employeeRepository;
	}
	public ActiveEmployeeExistsQuery setProductRepository(EmployeeRepositoryInterface employeeRepository) {
		this.employeeRepository = employeeRepository;
		return this;
	}
	
	public ActiveEmployeeExistsQuery() {
		this.employeeRepository = new EmployeeRepository();
	}
}
