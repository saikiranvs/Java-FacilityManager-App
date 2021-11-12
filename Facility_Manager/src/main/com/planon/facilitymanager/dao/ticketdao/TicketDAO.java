package main.com.planon.facilitymanager.dao.ticketdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import main.com.planon.facilitymanager.dto.ticketdto.TicketDTO;

public class TicketDAO {
	private Connection connection;

	public TicketDAO(Connection connection) {
		this.connection = connection;
	}

	/**
	 * method getAllTickets() of type List<TicketDTO>, gets the list of all tickets
	 * from the database.
	 * 
	 * @param aConnection2
	 * 
	 * @return aTickets, list of all tickets.
	 * @throws SQLException retrieves the exception chained to this SQLException
	 *                      object.
	 */
	public List<TicketDTO> getAllTickets() throws SQLException {
		List<TicketDTO> aListOfAllTickets = new ArrayList<TicketDTO>();
		PreparedStatement aPreparedStatement = null;
		ResultSet aResultSet = null;

		try {

			aPreparedStatement = connection.prepareStatement(
					"use facility_manager; select TIC_CODE,TIC_MESSAGE,TIC_TYPE,TIC_DATETIME,TIC_PRIORITY,TIC_STATUS from TICKET");

			aResultSet = aPreparedStatement.executeQuery();

			while (aResultSet.next()) {
				String aTicketCode = aResultSet.getString(1);
				String aTicketMessage = aResultSet.getString(2);
				String aTicketType = aResultSet.getString(3);
				Date aDateTime = aResultSet.getTimestamp(4);
				int aTicketPriority = aResultSet.getInt(5);
				String aTicketStatus = aResultSet.getString(6);

				TicketDTO aTicketDTO = new TicketDTO(aTicketCode, aTicketMessage, aTicketType, aTicketPriority,
						aDateTime, aTicketStatus);
				aListOfAllTickets.add(aTicketDTO);
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return aListOfAllTickets;

	}

	/**
	 * method getReportedTicketsByEmployee() of type List<TicketDTO>, accepts the
	 * employee code and gets the reported tickets by an employee from the database.
	 * 
	 * @param aEmployeeCode contains employee code.
	 * @return aEmployeeTicket, list of employee tickets.
	 * @throws SQLException retrieves the exception chained to this SQLException
	 *                      object.
	 */
	public List<TicketDTO> getReportedTicketsByEmployee(String aEmployeeCode) throws SQLException {
		List<TicketDTO> aListOfReportedTicketsByEmployee = new ArrayList<TicketDTO>();
		PreparedStatement aPreparedStatement = null;
		ResultSet aResultSet = null;
		try {

			aPreparedStatement = connection.prepareStatement(
					"use facility_manager; SELECT TIC_CODE,TIC_MESSAGE,TIC_TYPE,TIC_DATETIME,TIC_PRIORITY,TIC_STATUS FROM EMPLOYEE,TICKET WHERE EMPLOYEE.EMP_ID = TICKET.EMP_ID AND EMP_CODE = '"
							+ aEmployeeCode + "'");
			aResultSet = aPreparedStatement.executeQuery();
			while (aResultSet.next()) {
				String aTicketCode = aResultSet.getString(1);
				String aTicketMessage = aResultSet.getString(2);
				String aTicketType = aResultSet.getString(3);
				Date aDateTime = aResultSet.getTimestamp(4);
				int aTicketPriority = aResultSet.getInt(5);
				String aTicketStatus = aResultSet.getString(6);

				TicketDTO aTicketDTO = new TicketDTO(aTicketCode, aTicketMessage, aTicketType, aTicketPriority,
						aDateTime, aTicketStatus);
				aListOfReportedTicketsByEmployee.add(aTicketDTO);
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return aListOfReportedTicketsByEmployee;
	}

	/**
	 * method insertTicketIntoDatabase() of type int, accepts ticket of type
	 * TicketDTO and employee code and inserts the ticket code, message, type,
	 * employee id, date and time, and priority of the ticket.
	 * 
	 * @param ticket        contains ticket code, message, type, date and time,
	 *                      priority.
	 * @param aEmployeeCode contains employee code.
	 * @param aConnection2
	 * @throws SQLException retrieves the exception chained to this SQLException
	 *                      object.
	 */
	public int submitTicket(TicketDTO aTicket, String aEmployeeCode) throws SQLException {// b3498d10-270b-4d5e-9f34-d642966964fc
		PreparedStatement aPreparedStatement = null;
		ResultSet aResultSet = null;
		try {

			aPreparedStatement = connection.prepareStatement(
					"use facility_manager; select EMP_ID from EMPLOYEE where EMP_CODE = '" + aEmployeeCode + "'");
			aResultSet = aPreparedStatement.executeQuery();
			aResultSet.next();
			int employeeId = aResultSet.getInt("EMP_ID");

			aPreparedStatement.close();
			aResultSet.close();
			aPreparedStatement = connection.prepareStatement(
					"use facility_manager;insert into TICKET(TIC_CODE,TIC_MESSAGE,TIC_TYPE,EMP_ID,TIC_DATETIME,TIC_PRIORITY,TIC_STATUS) values(?,?,?,?,?,?,?)");
			aPreparedStatement.setString(1, getTicketCode());
			aPreparedStatement.setString(2, aTicket.getMessage());
			aPreparedStatement.setString(3, aTicket.getTicketType());
			aPreparedStatement.setInt(4, employeeId);
			aPreparedStatement.setTimestamp(5, (Timestamp) aTicket.getDateTime());
			aPreparedStatement.setInt(6, aTicket.getTicketPriority());
			aPreparedStatement.setString(7, "OPEN");
			return aPreparedStatement.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;

	}

	/**
	 * method getTicketCode() generates a hash code of an AlphaNumeric String using
	 * math.random function for the ticket code.
	 * 
	 * @return sb, contains a random string of 10 digits.
	 */
	public String getTicketCode() {
		String aAlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";
		StringBuilder aStringbuilder = new StringBuilder(10);
		for (int i = 0; i < 10; i++) {

			int aIndex = (int) (aAlphaNumericString.length() * Math.random());
			aStringbuilder.append(aAlphaNumericString.charAt(aIndex));
		}
		return aStringbuilder.toString();

	}

	/**
	 * method getOpenTickets() of type List<TicketDTO>, gets the list of all open
	 * tickets that are not yet assigned to an employee from the database.
	 * 
	 * @return openTickets, contains a list of open tickets.
	 * @throws SQLException retrieves the exception chained to this SQLException
	 *                      object.
	 */
	public List<TicketDTO> getOpenTickets() throws SQLException {
		List<TicketDTO> aListOfOpenTickets = new ArrayList<TicketDTO>();
		PreparedStatement aPreparedStatement = null;
		ResultSet aResultSet = null;

		try {

			aPreparedStatement = connection.prepareStatement(
					"use facility_manager; SELECT TIC_CODE,TIC_MESSAGE,TIC_TYPE,TIC_DATETIME,TIC_PRIORITY,TIC_STATUS FROM TICKET WHERE TIC_STATUS = 'OPEN'");
			aResultSet = aPreparedStatement.executeQuery();
			while (aResultSet.next()) {
				String aTicketCode = aResultSet.getString(1);
				String aTicketMessage = aResultSet.getString(2);
				String aTicketType = aResultSet.getString(3);
				Date aDateTime = aResultSet.getTimestamp(4);
				int aTicketPriority = aResultSet.getInt(5);
				String aTicketStatus = aResultSet.getString(6);
				TicketDTO aTicketDTO = new TicketDTO(aTicketCode, aTicketMessage, aTicketType, aTicketPriority,
						aDateTime, aTicketStatus);
				aListOfOpenTickets.add(aTicketDTO);
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return aListOfOpenTickets;

	}

	/**
	 * method getCloseTickets() of type List<TicketDTO>, gets the list of all close
	 * tickets from the database.
	 * 
	 * @return closeTickets, contains a list of close tickets.
	 * @throws SQLException retrieves the exception chained to this SQLException
	 *                      object.
	 */
	public List<TicketDTO> getCloseTickets() throws SQLException {
		List<TicketDTO> aListOfCloseTickets = new ArrayList<TicketDTO>();
		PreparedStatement aPreparedStatement = null;
		ResultSet aResultSet = null;

		try {

			aPreparedStatement = connection.prepareStatement(
					"use facility_manager; SELECT TIC_CODE,TIC_MESSAGE,TIC_TYPE,TIC_DATETIME,TIC_PRIORITY,TIC_STATUS FROM TICKET WHERE TIC_STATUS = 'CLOSE'");
			aResultSet = aPreparedStatement.executeQuery();
			while (aResultSet.next()) {
				String aTicketCode = aResultSet.getString(1);
				String aTicketMessage = aResultSet.getString(2);
				String aTicketType = aResultSet.getString(3);
				Date aDateTime = aResultSet.getTimestamp(4);
				int aTicketPriority = aResultSet.getInt(5);
				String aTicketStatus = aResultSet.getString(6);

				TicketDTO aTicketDTO = new TicketDTO(aTicketCode, aTicketMessage, aTicketType, aTicketPriority,
						aDateTime, aTicketStatus);
				aListOfCloseTickets.add(aTicketDTO);
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return aListOfCloseTickets;

	}
}
