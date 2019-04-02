package edu.uark.models.repositories;

import java.sql.SQLException;

import edu.uark.dataaccess.repository.BaseRepository;
import edu.uark.dataaccess.repository.DatabaseTable;
import edu.uark.dataaccess.repository.helpers.SQLComparisonType;
import edu.uark.dataaccess.repository.helpers.where.WhereClause;
import edu.uark.dataaccess.repository.helpers.where.WhereContainer;
import edu.uark.models.entities.EmployeeEntity;
import edu.uark.models.entities.fieldnames.EmployeeFieldNames;
import edu.uark.models.repositories.interfaces.EmployeeRepositoryInterface;

public class EmployeeRepository extends BaseRepository<EmployeeEntity> implements EmployeeRepositoryInterface
{
	@Override
	public boolean employeeIdExists(String employeeId)
	{
		return this.existsWhere(
			new WhereContainer(
				(new WhereClause())
					.table(this.primaryTable)
					.fieldName(EmployeeFieldNames.EMPLOYEE_ID)
					.comparison(SQLComparisonType.EQUALS)
			),
			(ps) -> {
				try {
					ps.setObject(1, employeeId);
				} catch (SQLException e) {}

				return ps;
			}
		);
	}
	
	@Override
	public EmployeeEntity byEmployeeId(int employeeId)
	{
		return this.firstOrDefaultWhere(
			new WhereContainer(
				(new WhereClause())
					.table(this.primaryTable)
					.fieldName(EmployeeFieldNames.EMPLOYEE_ID)
					.comparison(SQLComparisonType.EQUALS)
			),
			(ps) -> {
				try {
					ps.setObject(1, employeeId);
				} catch (SQLException e) {}

				return ps;
			}
		);
	}
	
	@Override
	public boolean activeExists()
	{
		return this.existsWhere(
			new WhereContainer(
				(new WhereClause())
					.table(this.primaryTable)
					.fieldName(EmployeeFieldNames.ACTIVE)
					.comparison(SQLComparisonType.EQUALS)
			),
			(ps) ->
            {
				try
                {
					ps.setObject(1, true);
				} catch (SQLException e) {}
				
				return ps;
			}
		);
	}
	
	@Override
	public EmployeeEntity createOne() {
		return new EmployeeEntity();
	}
	
	public EmployeeRepository() {
		super(DatabaseTable.EMPLOYEE);
	}
}
