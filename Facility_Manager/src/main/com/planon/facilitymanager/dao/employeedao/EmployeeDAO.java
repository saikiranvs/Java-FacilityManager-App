package main.com.planon.facilitymanager.dao.employeedao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.com.planon.facilitymanager.dto.employeedto.EmployeeDTO;
import main.com.planon.facilitymanager.util.ConnectionUtil;

public class EmployeeDAO {
	/**
	 * method getAllEmployees(), gets a list code, name and role of an employee from
	 * the database.
	 * 
	 * @return aEmployeesDetails, list of all employees with code name and role.
	 * @throws SQLException retrieves the exception chained to this SQLException
	 *                      object.
	 */
	public List<EmployeeDTO> getAllEmployees() throws SQLException {
		List<EmployeeDTO> aListOfEmployeesDetails = new ArrayList<EmployeeDTO>();
		Connection aConnection = null;
		PreparedStatement aPreparedStatement = null;
		ResultSet aResultSet = null;
		try {

			aConnection = ConnectionUtil.openConnection();
			aPreparedStatement = aConnection
					.prepareStatement("use facility_manager; select EMP_CODE,EMP_NAME,EMP_ROLE from EMPLOYEE");
			aResultSet = aPreparedStatement.executeQuery();
			while (aResultSet.next()) {
				String aEmployeeCode = aResultSet.getString(1);
				String aEmployeeName = aResultSet.getString(2);
				String aEmployeeRole = aResultSet.getString(3);

				EmployeeDTO aEmployeeDTO = new EmployeeDTO(aEmployeeCode, aEmployeeName, aEmployeeRole);
				aListOfEmployeesDetails.add(aEmployeeDTO);
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.closeConnection(aResultSet, aPreparedStatement, aConnection);
		}
		return aListOfEmployeesDetails;
	}

}
