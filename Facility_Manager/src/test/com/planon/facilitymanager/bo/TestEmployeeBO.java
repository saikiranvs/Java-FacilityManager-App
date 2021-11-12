package test.com.planon.facilitymanager.bo;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import main.com.planon.facilitymanager.bo.ticketbo.TicketBO;
import main.com.planon.facilitymanager.dto.ticketdto.TicketDTO;
import main.com.planon.facilitymanager.util.ConnectionUtil;

public class TestEmployeeBO {
	static Connection aConnection;

	@BeforeClass
	public static void openConnection() throws ClassNotFoundException, SQLException {
		aConnection = ConnectionUtil.openConnection();
	}

	@AfterClass
	public static void closeConnection() throws SQLException {
		aConnection.close();
	}

	@Test
	public void submitTicket() throws SQLException, ParseException, ClassNotFoundException {

		String aEmployeeCode = "PSSL-203";
		String aExpectedTickedMessge = "message";
		String aExpectedTickedType = "type";

		SimpleDateFormat aSimpleDateformat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date aDate = new Date();
		java.util.Date aUtilDate = aSimpleDateformat.parse(aSimpleDateformat.format(aDate));
		Timestamp aDateTime = new Timestamp(aUtilDate.getTime());

		int aExpectedTickedStatus = 1;
		TicketBO aTicketBO = new TicketBO();
		int aResult = aTicketBO.insertTicket(aEmployeeCode, aExpectedTickedMessge, aExpectedTickedType, aDateTime,
				aExpectedTickedStatus, aConnection);
		assertEquals(1, aResult);

	}

	@Test
	public void testGetAllTickets() throws SQLException, ParseException, ClassNotFoundException {

		String aEmployeeCode = "PSSL-203";
		String aExpectedTickedMessge = "message";
		String aExpectedTickedType = "type";

		SimpleDateFormat aSimpleDateformat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date aDate = new Date();
		java.util.Date aUtilDate = aSimpleDateformat.parse(aSimpleDateformat.format(aDate));
		Timestamp aDateTime = new Timestamp(aUtilDate.getTime());

		int aExpectedTickedPriority = 1;
		TicketBO aTicketBO = new TicketBO();
		int aResult = aTicketBO.insertTicket(aEmployeeCode, aExpectedTickedMessge, aExpectedTickedType, aDateTime,
				aExpectedTickedPriority, aConnection);
		assertEquals(1, aResult);
		List<TicketDTO> aAllTicketsList = aTicketBO.getAllTickets(aConnection);

		assertEquals(5, aAllTicketsList.size());

		assertEquals("message", aAllTicketsList.get(0).getMessage());
		assertEquals(1, aAllTicketsList.get(0).getTicketPriority());
		assertEquals("OPEN", aAllTicketsList.get(0).getTicketStatus());
		assertEquals("type", aAllTicketsList.get(0).getTicketType());

	}

	@Test
	public void testGetAllOpenTickets() throws SQLException, ParseException, ClassNotFoundException {

		String aEmployeeCode = "PSSL-203";
		String aExpectedTickedMessge = "message";
		String aExpectedTickedType = "GENERAL";
		int aExpectedTickedPriority = 2;
		String aExpectedTickedStatus = "OPEN";

		SimpleDateFormat aSimpleDateformat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date aDate = new Date();
		java.util.Date aUtilDate = aSimpleDateformat.parse(aSimpleDateformat.format(aDate));
		Timestamp aDateTime = new Timestamp(aUtilDate.getTime());

		TicketBO aTicketBO = new TicketBO();
		int aResult = aTicketBO.insertTicket(aEmployeeCode, aExpectedTickedMessge, aExpectedTickedType, aDateTime,
				aExpectedTickedPriority, aConnection);
		assertEquals(1, aResult);

		List<TicketDTO> aOpenTicketsList = aTicketBO.getAllOpenTickets(aConnection);

		assertEquals(aExpectedTickedMessge, aOpenTicketsList.get(0).getMessage());
		assertEquals(aExpectedTickedPriority, aOpenTicketsList.get(0).getTicketPriority());
		assertEquals(aExpectedTickedStatus, aOpenTicketsList.get(0).getTicketStatus());
		assertEquals(aExpectedTickedType, aOpenTicketsList.get(0).getTicketType());

	}

	@Test
	public void testGetAllCloseTickets() throws SQLException, ParseException, ClassNotFoundException {

		String aEmployeeCode = "PSSL-203";
		String aExpectedTickedMessge = "message";
		String aExpectedTickedType = "GENERAL";
		int aExpectedTickedPriority = 2;

		SimpleDateFormat aSimpleDateformat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date aDate = new Date();
		java.util.Date aUtilDate = aSimpleDateformat.parse(aSimpleDateformat.format(aDate));
		Timestamp aDateTime = new Timestamp(aUtilDate.getTime());

		TicketBO aTicketBO = new TicketBO();
		int aResult = aTicketBO.insertTicket(aEmployeeCode, aExpectedTickedMessge, aExpectedTickedType, aDateTime,
				aExpectedTickedPriority, aConnection);
		assertEquals(1, aResult);

		List<TicketDTO> aCloseTicketsList = aTicketBO.getAllCloseTickets(aConnection);
		assertEquals(0, aCloseTicketsList.size());

	}

	@Test
	public void testGetReportedTicketsByEmployee() throws SQLException, ParseException, ClassNotFoundException {

		String aEmployeeCode = "PSSL-203";
		String aExpectedTickedMessge = "message";
		String aExpectedTickedType = "GENERAL";
		int aExpectedTickedPriority = 2;

		SimpleDateFormat aSimpleDateformat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date aDate = new Date();
		java.util.Date aUtilDate = aSimpleDateformat.parse(aSimpleDateformat.format(aDate));
		Timestamp aDateTime = new Timestamp(aUtilDate.getTime());

		TicketBO aTicketBO = new TicketBO();
		int aResult = aTicketBO.insertTicket(aEmployeeCode, aExpectedTickedMessge, aExpectedTickedType, aDateTime,
				aExpectedTickedPriority, aConnection);
		assertEquals(1, aResult);

		List<TicketDTO> aCloseTicketsList = aTicketBO.getAllReportedTicketsByEmployee(aEmployeeCode, aConnection);
		assertEquals(3, aCloseTicketsList.size());

	}

}