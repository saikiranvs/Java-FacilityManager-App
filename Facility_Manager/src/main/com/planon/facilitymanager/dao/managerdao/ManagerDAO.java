package main.com.planon.facilitymanager.dao.managerdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.com.planon.facilitymanager.dto.employeedto.EmployeeDTO;
import main.com.planon.facilitymanager.util.ConnectionUtil;

public class ManagerDAO {
	/**
	 * method getSubOrdinatesByManager()of type List<EmployeeDTO>, gets a list code,
	 * name and role of an employee from the database by the manager.
	 * 
	 * @return aSubOrdinates, list of all subordinates.
	 * @throws SQLException retrieves the exception chained to this SQLException
	 *                      object.
	 */
	public List<EmployeeDTO> getSubOrdinatesByManager(int aManagerId) throws SQLException {
		List<EmployeeDTO> aListOfSubOrdinates = new ArrayList<EmployeeDTO>();
		Connection aConnection = null;
		PreparedStatement aPreparedStatement = null;
		ResultSet aResultSet = null;
		try {
			aConnection = ConnectionUtil.openConnection();

			aPreparedStatement = aConnection.prepareStatement(
					"use facility_manager;SELECT e1.EMP_CODE,e1.EMP_NAME,e1.EMP_ROLE,E2.EMP_NAME AS MANAGER FROM EMPLOYEE E1 JOIN "
							+ "EMPLOYEE E2 ON E1.EMP_MANAGER_ID=E2.EMP_ID WHERE E1.EMP_MANAGER_ID = E2.EMP_MANAGER_ID AND E1.EMP_MANAGER_ID<>e1.EMP_ID AND E1.EMP_MANAGER_ID = ? ");
			aPreparedStatement.setInt(1, aManagerId);

			aResultSet = aPreparedStatement.executeQuery();
			while (aResultSet.next()) {
				String aEmployeeCode = aResultSet.getString(1);
				String aEmployeeName = aResultSet.getString(2);
				String aEmployeeRole = aResultSet.getString(3);
				EmployeeDTO aTicketDTO = new EmployeeDTO(aEmployeeCode, aEmployeeName, aEmployeeRole);
				aListOfSubOrdinates.add(aTicketDTO);

			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.closeConnection(aResultSet, aPreparedStatement, aConnection);
		}
		return aListOfSubOrdinates;
	}

	/**
	 * method getAssignedTicketByEmployee() of type int, gets the assigned tickets
	 * by employee from the database.
	 * 
	 * @param aEmployeeCode contains employee code.
	 * @param aTicketCode   contains ticket code.
	 * @return
	 * @throws SQLException retrieves the exception chained to this SQLException
	 *                      object.
	 */
	public int assignTicketToEmployee(String aEmployeeCode, String aTicketCode) throws SQLException {
		Connection aConnection = null;
		PreparedStatement aPreparedStatement = null;
		ResultSet aResultSet = null;
		try {
			aConnection = ConnectionUtil.openConnection();

			aPreparedStatement = aConnection.prepareStatement(
					"use facility_manager;update TICKET SET TIC_ASSIGNED_EMPLOYEE = (SELECT EMP_NAME FROM EMPLOYEE WHERE EMP_CODE = '"
							+ aEmployeeCode + "'),TIC_STATUS = 'IN PROGRESS' WHERE TIC_CODE ='" + aTicketCode + "'");
			return aPreparedStatement.executeUpdate();

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.closeConnection(aResultSet, aPreparedStatement, aConnection);
		}
		return 0;
	}
}