package main.com.planon.facilitymanager.bo.employeebo;

import java.sql.SQLException;
import java.util.List;

import main.com.planon.facilitymanager.dao.employeedao.EmployeeDAO;
import main.com.planon.facilitymanager.dto.employeedto.EmployeeDTO;

public class EmployeeBO {
	/**
	 * method getAllEmployees() of type List<EmployeeDTO>, gets a list of employees
	 * from the EmployeeDAO class.
	 * 
	 * @return aEmployees, list of all employees.
	 * @throws SQLException retrieves the exception chained to this SQLException
	 *                      object.
	 */
	public List<EmployeeDTO> getEmployees() throws SQLException {
		EmployeeDAO aEmployeeDAO = new EmployeeDAO();

		List<EmployeeDTO> aEmployeeDTO = aEmployeeDAO.getAllEmployees();
		return aEmployeeDTO;
	}
}