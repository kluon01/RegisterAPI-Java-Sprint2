package edu.uark.models.repositories.interfaces;

import edu.uark.dataaccess.repository.BaseRepositoryInterface;
import edu.uark.models.entities.EmployeeEntity;

public interface EmployeeRepositoryInterface extends BaseRepositoryInterface<EmployeeEntity> {
	boolean activeExists();
	boolean employeeIdExists(String employeeId);
	EmployeeEntity byEmployeeId(int employeeId);
}
