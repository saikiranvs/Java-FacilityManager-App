package main.com.planon.facilitymanager.bo.mangerbo;

import java.sql.SQLException;
import java.util.List;

import main.com.planon.facilitymanager.dao.managerdao.ManagerDAO;
import main.com.planon.facilitymanager.dto.employeedto.EmployeeDTO;

public class ManagerBO {
	/**
	 * method getSubOrdinators() of type List<EmployeeDTO>, gets a list of
	 * subordinators from the ManagerDAO class.
	 * 
	 * @return aSubOrdinates, list of all subordinates.
	 * @throws SQLException retrieves the exception chained to this SQLException
	 *                      object.
	 */
	public List<EmployeeDTO> getSubOrdinators(int aEmployeeId) throws SQLException {
		ManagerDAO aManagerDAO = new ManagerDAO();
		// pk value of manager from DB , for now.
		List<EmployeeDTO> aListOfSubOrdinates = aManagerDAO.getSubOrdinatesByManager(aEmployeeId);
		return aListOfSubOrdinates;
	}

	/**
	 * method assignTicketToEmployee(), assigns the tickets to the employees and
	 * passes to the ManagerDAO class.
	 * 
	 * @param aEmployeeCode contains the employee code.
	 * @param aTicketCode   contains the ticket code.
	 * @return
	 * @throws SQLException retrieves the exception chained to this SQLException
	 *                      object.
	 */
	public int assignTicketToEmployee(String aEmployeeCode, String aTicketCode) throws SQLException {
		ManagerDAO aManagerDAO = new ManagerDAO();
		return aManagerDAO.assignTicketToEmployee(aEmployeeCode, aTicketCode);

	}
}