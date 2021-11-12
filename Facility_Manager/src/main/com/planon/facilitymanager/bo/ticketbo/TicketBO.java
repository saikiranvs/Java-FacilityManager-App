package main.com.planon.facilitymanager.bo.ticketbo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import main.com.planon.facilitymanager.dao.ticketdao.TicketDAO;
import main.com.planon.facilitymanager.dto.ticketdto.TicketDTO;
import main.com.planon.facilitymanager.util.TicketUtil;

public class TicketBO {
	/**
	 * method getAllTickets() of type List<EmployeeDTO>, gets a list of all tickets
	 * from the TicketDTO class.
	 * 
	 * @param aConnection
	 * @param aConnection
	 * 
	 * @return allTickets, list of all the tickets.
	 * @throws SQLException retrieves the exception chained to this SQLException
	 *                      object.
	 */
	public List<TicketDTO> getAllTickets(Connection aConnection) throws SQLException {
		TicketDAO aTicketDAO = new TicketDAO(aConnection);

		List<TicketDTO> aListOfAllTickets = aTicketDAO.getAllTickets();
		return TicketUtil.sortTickets(aListOfAllTickets);
	}

	/**
	 * method ReportedTicketsByEmployee() of type List<EmployeeDTO>, gets a list of
	 * all reported tickets by an employee from the TicketDTO class.
	 * 
	 * @param aEmployeeCode
	 * @return aEmployeeTicket, list of employee tickets.
	 * @throws SQLException retrieves the exception chained to this SQLException
	 *                      object.
	 */
	public List<TicketDTO> getAllReportedTicketsByEmployee(String aEmployeeCode, Connection aConnection)
			throws SQLException {

		TicketDAO aTicketDAO = new TicketDAO(aConnection);
		List<TicketDTO> aListOfReportedTicketsByEmployee = aTicketDAO.getReportedTicketsByEmployee(aEmployeeCode);
		return aListOfReportedTicketsByEmployee;

	}

	/**
	 * method insertTicket(), accepts the employee code ticket message, type , date
	 * and time and priority from the user and pass these values to insert into the
	 * database.
	 * 
	 * @param aEmployeeCode   contains the employee code.
	 * @param aTicketMessage  contains the ticket message.
	 * @param aTicketType     contains the ticket type.
	 * @param aDateTime       contains the ticket time.
	 * @param aTicketPriority contains the ticket priority.
	 * @param aConnection
	 * @param aConnection
	 * @throws SQLException retrieves the exception chained to this SQLException
	 *                      object.
	 */
	public int insertTicket(String aEmployeeCode, String aTicketMessage, String aTicketType, Date aDateTime,
			int aTicketPriority, Connection aConnection) throws SQLException {
		TicketDTO aTicketDAO = new TicketDTO(null, aTicketMessage, aTicketType, aTicketPriority, aDateTime, null);
		TicketDAO aTicketDao = new TicketDAO(aConnection);
		return aTicketDao.submitTicket(aTicketDAO, aEmployeeCode);

	}

	/**
	 * method openTickets() of type List<EmployeeDTO>, gets a list of all open
	 * tickets that are not yet assigned to an employee from the TicketDTO class.
	 * 
	 * @return openTicketsm, list of all open tickets.
	 * @throws SQLException retrieves the exception chained to this SQLException
	 *                      object.
	 */
	public List<TicketDTO> getAllOpenTickets(Connection aConnection) throws SQLException {
		TicketDAO aTicketDAO = new TicketDAO(aConnection);
		List<TicketDTO> aListOfOpenTickets = aTicketDAO.getOpenTickets();
		return TicketUtil.sortTickets(aListOfOpenTickets);
	}

	/**
	 * method closeTickets() of type List<EmployeeDTO>, gets a list of all close
	 * tickets from the TicketDTO class.
	 * 
	 * @return closeTickets, list of all close tickets.
	 * @throws SQLException retrieves the exception chained to this SQLException
	 *                      object.
	 */
	public List<TicketDTO> getAllCloseTickets(Connection aConnection) throws SQLException {
		TicketDAO aTicketDAO = new TicketDAO(aConnection);
		List<TicketDTO> aListOfCloseTickets = aTicketDAO.getCloseTickets();
		return TicketUtil.sortTickets(aListOfCloseTickets);
	}

}