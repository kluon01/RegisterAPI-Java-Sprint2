package edu.uark.models.entities;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import edu.uark.dataaccess.entities.BaseEntity;
import edu.uark.dataaccess.repository.DatabaseTable;
import edu.uark.models.api.Employee;
import edu.uark.models.entities.fieldnames.EmployeeFieldNames;
import edu.uark.models.enums.EmployeeClassification;

public class EmployeeEntity extends BaseEntity<EmployeeEntity> {
	@Override
	protected void fillFromRecord(ResultSet rs) throws SQLException {
		this.active = rs.getBoolean(EmployeeFieldNames.ACTIVE);
		this.lastName = rs.getString(EmployeeFieldNames.LAST_NAME);
		this.employeeId = rs.getInt(EmployeeFieldNames.EMPLOYEE_ID);
		this.firstName = rs.getString(EmployeeFieldNames.FIRST_NAME);
		this.managerId = ((UUID) rs.getObject(EmployeeFieldNames.MANAGER_ID));
		this.password = new String((byte[]) rs.getObject(EmployeeFieldNames.PASSWORD));
		this.classification = EmployeeClassification.map(rs.getInt(EmployeeFieldNames.CLASSIFICATION));
	}

	@Override
	protected Map<String, Object> fillRecord(Map<String, Object> record) {
		record.put(EmployeeFieldNames.ACTIVE, this.active);
		record.put(EmployeeFieldNames.LAST_NAME, this.lastName);
		record.put(EmployeeFieldNames.FIRST_NAME, this.firstName);
		record.put(EmployeeFieldNames.MANAGER_ID, this.managerId);
		record.put(EmployeeFieldNames.PASSWORD, this.password.getBytes());
		record.put(EmployeeFieldNames.CLASSIFICATION, this.classification.getValue());

		return record;
	}

	private int employeeId;
	public int getEmployeeId() {
		return this.employeeId;
	}
	public String getEmployeeIdAsString() {
		return StringUtils.leftPad(
			Integer.toString(this.employeeId),
			EMPLOYEE_ID_LENGTH, '0'
		);
	}

	private String firstName;
	public String getFirstName() {
		return this.firstName;
	}
	public EmployeeEntity setFirstName(String firstName) {
		if (!StringUtils.equals(this.firstName, firstName)) {
			this.firstName = firstName;
			this.propertyChanged(EmployeeFieldNames.FIRST_NAME);
		}
		
		return this;
	}

	private String lastName;
	public String getLastName() {
		return this.lastName;
	}
	public EmployeeEntity setLastName(String lastName) {
		if (!StringUtils.equals(this.lastName, lastName)) {
			this.lastName = lastName;
			this.propertyChanged(EmployeeFieldNames.LAST_NAME);
		}
		
		return this;
	}

	private String password;
	public String getPassword() {
		return this.password;
	}
	public EmployeeEntity setPassword(String password) {
		if (!StringUtils.equals(this.password, password)) {
			this.password = password;
			this.propertyChanged(EmployeeFieldNames.PASSWORD);
		}
		
		return this;
	}

	private boolean active;
	public boolean getActive() {
		return this.active;
	}
	public EmployeeEntity setActive(boolean active) {
		if (this.active != active) {
			this.active = active;
			this.propertyChanged(EmployeeFieldNames.ACTIVE);
		}
		
		return this;
	}

	private EmployeeClassification classification;
	public EmployeeClassification getClassification() {
		return this.classification;
	}
	public EmployeeEntity setClassification(EmployeeClassification classification) {
		if (this.classification != classification) {
			this.classification = classification;
			this.propertyChanged(EmployeeFieldNames.CLASSIFICATION);
		}
		
		return this;
	}

	private UUID managerId;
	public UUID getManagerId() {
		return this.managerId;
	}
	public EmployeeEntity setManagerId(UUID managerId) {
		if (!this.managerId.equals(managerId)) {
			this.managerId = managerId;
			this.propertyChanged(EmployeeFieldNames.MANAGER_ID);
		}
		
		return this;
	}
	
	public Employee synchronize(Employee apiEmployee) {
		this.setActive(apiEmployee.getActive());
		this.setLastName(apiEmployee.getLastName());
		this.setFirstName(apiEmployee.getFirstName());
		this.setManagerId(apiEmployee.getManagerId());
		this.setClassification(EmployeeClassification.map(apiEmployee.getClassification()));
		if (!StringUtils.isBlank(apiEmployee.getPassword())) {
			this.setPassword(
				EmployeeEntity.hashPassword(
					apiEmployee.getPassword()));
		}
		
		apiEmployee.setId(this.getId());
		apiEmployee.setPassword(StringUtils.EMPTY); //Only send the password over the network when modifying the database.
		apiEmployee.setCreatedOn(this.getCreatedOn());
		apiEmployee.setEmployeeId(this.getEmployeeIdAsString()); //The employee ID may not be changed from a client.
		
		return apiEmployee;
	}
	
	public static String hashPassword(String password) {
		String hashedPassword;

		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update(password.getBytes());
			hashedPassword = new String(messageDigest.digest());
		} catch (NoSuchAlgorithmException e) {
			hashedPassword = StringUtils.EMPTY;
		}
		
		return hashedPassword;
	}
	
	private static final int EMPLOYEE_ID_LENGTH = 5;
	
	public EmployeeEntity() {
		super(DatabaseTable.EMPLOYEE);
		
		this.active = false;
		this.employeeId = -1;
		this.managerId = new UUID(0, 0);
		this.lastName = StringUtils.EMPTY;
		this.password = StringUtils.EMPTY;
		this.firstName = StringUtils.EMPTY;
		this.classification = EmployeeClassification.NOT_DEFINED;
	}

	public EmployeeEntity(Employee apiEmployee) {
		super(DatabaseTable.EMPLOYEE);
		
		this.active = apiEmployee.getActive();
		this.lastName = apiEmployee.getLastName();
		this.firstName = apiEmployee.getFirstName();
		this.managerId = apiEmployee.getManagerId();
		this.classification = EmployeeClassification.map(apiEmployee.getClassification());
		this.password = EmployeeEntity.hashPassword(
			apiEmployee.getPassword()
		);

		try {
			this.employeeId = Integer.parseInt(apiEmployee.getEmployeeId());
		} catch (NumberFormatException e) {
			this.employeeId = -1;
		}
	}
}
