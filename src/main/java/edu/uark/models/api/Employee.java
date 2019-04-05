package edu.uark.models.api;

import java.time.LocalDateTime;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import edu.uark.models.entities.EmployeeEntity;
import edu.uark.models.enums.EmployeeClassification;

public class Employee
{
	private UUID id;
	private String employeeId;
	private String firstName;
	private String lastName;
	private String password;
	private boolean active;
	private int classification;
	private UUID managerId;
	private LocalDateTime createdOn;
	private int sales;

	public UUID getId() {
		return this.id;
	}
	public String getEmployeeId() {
		return this.employeeId;
	}
	public String getFirstName() {
		return this.firstName;
	}
	public String getLastName() {
		return this.lastName;
	}
	public String getPassword() {
		return this.password;
	}
	public boolean getActive() {
		return this.active;
	}
	public int getClassification() {
		return this.classification;
	}
	public UUID getManagerId() {
		return this.managerId;
	}
	public LocalDateTime getCreatedOn() {
		return this.createdOn;
	}
	public int getSales() {
		return this.sales;
	}


	public Employee setId(UUID id)
	{
		this.id = id;
		return this;
	}

	public Employee setEmployeeId(String employeeId)
	{
		this.employeeId = employeeId;
		return this;
	}

	public Employee setFirstName(String firstName)
	{
		this.firstName = firstName;
		return this;
	}

	public Employee setLastName(String lastName)
	{
		this.lastName = lastName;
		return this;
	}

	public Employee setPassword(String password)
	{
		this.password = password;
		return this;
	}

	public Employee setActive(boolean active)
	{
		this.active = active;
		return this;
	}

	public Employee setClassification(int classification)
	{
		this.classification = classification;
		return this;
	}

	public Employee setManagerId(UUID managerId)
	{
		this.managerId = managerId;
		return this;
	}
	
	public Employee setCreatedOn(LocalDateTime createdOn)
	{
		this.createdOn = createdOn;
		return this;
	}

	public Employee setSales(int sales)
	{
		this.sales = sales;
		return this;
	}
	
	public Employee()
	{
		this.active = false;
		this.id = new UUID(0, 0);
		this.managerId = new UUID(0, 0);
		this.lastName = StringUtils.EMPTY;
		this.password = StringUtils.EMPTY;
		this.firstName = StringUtils.EMPTY;
		this.employeeId = StringUtils.EMPTY;
		this.createdOn = LocalDateTime.now();
		this.classification = EmployeeClassification.NOT_DEFINED.getValue();
		this.sales = 0;
	}
	
	public Employee(EmployeeEntity employeeEntity)
	{
		this.id = employeeEntity.getId();
		this.password = StringUtils.EMPTY;
		this.active = employeeEntity.getActive();
		this.lastName = employeeEntity.getLastName();
		this.createdOn = employeeEntity.getCreatedOn();
		this.firstName = employeeEntity.getFirstName();
		this.managerId = employeeEntity.getManagerId();
		this.employeeId = employeeEntity.getEmployeeIdAsString();
		this.classification = employeeEntity.getClassification().getValue();
		this.sales = employeeEntity.getSales();
	}
}
