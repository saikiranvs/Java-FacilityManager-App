package main.com.planon.facilitymanager;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import main.com.planon.facilitymanager.bo.employeebo.EmployeeBO;
import main.com.planon.facilitymanager.bo.mangerbo.ManagerBO;
import main.com.planon.facilitymanager.bo.ticketbo.TicketBO;
import main.com.planon.facilitymanager.dto.employeedto.EmployeeDTO;
import main.com.planon.facilitymanager.dto.ticketdto.TicketDTO;
import main.com.planon.facilitymanager.util.ConnectionUtil;

/**
 * As a facility manager I do facilitate things in the office for employees
 * which includes managers.
 * 
 * People do approach me asking/requesting/complaining.
 * 
 * Design a system which helps facility manager to manage things. An
 * employee/manager should be able to create
 * incident/request/reservation/complaint/etc. using the system. The facility
 * manager gets an overview of what people want and pick them based on the
 * priority which is in in the request. and Assign staff like office boy,
 * security, maid to it if needed.
 * 
 * @author saveer
 *
 */
public class Application {

	private static Scanner sc;
	private static String employeeCode;
	private static String ticketCode;
	private static String ticketmessage;
	private static String ticketType;

	/**
	 * main method(), contains a menu driven user interfase to select the options
	 * and perform actions.
	 * 
	 * @param args, String[] args is an array of strings which stores arguments
	 *              passed by command line while starting a program. All the command
	 *              line arguments are stored in that array.
	 * @throws Exception,             The class Exception and its subclasses are a
	 *                                form of Throwable that indicates conditions
	 *                                that a reasonableapplication might want to
	 *                                catch.
	 * @throws ParseException,        Signals that an error has been reached
	 *                                unexpectedly while parsing
	 * @throws ClassNotFoundException
	 */

	public static void main(String[] args) throws SQLException, ParseException, ClassNotFoundException {
		Connection aConnection = ConnectionUtil.openConnection();
		while (true) {
			sc = new Scanner(System.in);
			System.out.println(
					"1.Facility Manger Option 2.Employee Option 3. All Employees Details 4. Exit autocommitIsFalse");
			try {
				int aSelectedOption = sc.nextInt();
				switch (aSelectedOption) {
				case 1:
					System.out.println(
							" 1.View tickets 2.Subordinates 3.Assign Employees to Tickets 4. Go to Main Menu ");
					int aManagerOption = sc.nextInt();
					switch (aManagerOption) {
					case 1:
						System.out.println(
								" 1.View Open tickets 2. View Closed tickets 3. View All Tickets 4. Go to Main Menu ");
						int aViewTicket = sc.nextInt();
						switch (aViewTicket) {
						case 1:
							TicketBO aTicketBOForOpenTickets = new TicketBO();
							List<TicketDTO> aOpenTicketsList = aTicketBOForOpenTickets.getAllOpenTickets(aConnection);
							if (aOpenTicketsList.isEmpty()) {
								System.out.println(" There are no Tickets to Show ");
							}
							for (TicketDTO aTicketDTO : aOpenTicketsList) {
								System.out.println(aTicketDTO.toString() + "\n");
							}
							break;
						case 2:
							TicketBO aTicketBOForCloseTickets = new TicketBO();
							List<TicketDTO> aCloseTicketsList = aTicketBOForCloseTickets
									.getAllCloseTickets(aConnection);
							if (aCloseTicketsList.isEmpty()) {
								System.out.println(" There are no Tickets to Show ");
							}
							for (TicketDTO aTicketDTO : aCloseTicketsList) {
								System.out.println(aTicketDTO.toString() + "\n");
							}
							break;
						case 3:
							TicketBO aTicketBOForAllTickets = new TicketBO();
							List<TicketDTO> aAllTicketsList = aTicketBOForAllTickets.getAllTickets(aConnection);
							if (aAllTicketsList.isEmpty()) {
								System.out.println(" There are no Tickets to Show ");
							}
							for (TicketDTO aTicketDTO : aAllTicketsList) {
								System.out.println(aTicketDTO.toString() + "\n");
							}
							break;
						case 4:

							continue;
						default:
							System.out.println(" Invalid Input Please Try Again ");
							break;
						}
						break;
					case 2:
						ManagerBO aManagerBO = new ManagerBO();
						List<EmployeeDTO> aListOfSubOrdinates = aManagerBO.getSubOrdinators(2);
						for (EmployeeDTO aEmployeeDTO : aListOfSubOrdinates) {
							System.out.println(aEmployeeDTO.toString() + "\n");
						}
						break;
					case 3:
						sc.nextLine();
						System.out.println(" Enter Employee Code : ");
						employeeCode = sc.nextLine();
						if (employeeCode.isEmpty()) {
							System.out.println(" Try Again and Please Enter Employee Code ");
							continue;
						}
						System.out.println(" Enter TicketCode Code : ");
						ticketCode = sc.nextLine();
						if (employeeCode.isEmpty()) {
							System.out.println(" Try Again and Please Enter Ticket Code ");
							continue;
						}
						ManagerBO aManagerBO1 = new ManagerBO();
						int aResult = aManagerBO1.assignTicketToEmployee(employeeCode, ticketCode);
						if (aResult == 1) {
							System.out.println(" Successfully Assigned the Employee to the Ticket ");
						} else {
							System.out.println("Failed to Assign the Employee to the Ticket ");
						}
						break;
					case 4:

						continue;
					default:
						System.out.println(" Invalid Input Please Try Again ");
						break;
					}
					break;
				case 2:
					System.out.println(" 1.Submit tickets  2.View tickets 3. Go to Main Menu ");
					System.out.println("");

					int aEmployeeOption = sc.nextInt();
					sc.nextLine();
					switch (aEmployeeOption) {
					case 1:
						System.out.println(" Enter Employee Code : ");
						employeeCode = sc.nextLine();
						if (employeeCode.isEmpty()) {
							System.out.println(" Try Again and Please Enter Employee Code ");
							continue;
						}
						System.out.println(" Enter Message : ");
						ticketmessage = sc.nextLine();
						if (ticketmessage.isEmpty()) {
							System.out.println(" Try Again and Please Enter Ticket Message ");
							continue;
						}
						System.out.println(" Enter Ticket Type(Incident/General/Service) : ");
						ticketType = sc.nextLine();
						if (ticketType.isEmpty()) {
							System.out.println(" Try Again and Please Enter Ticket Type ");
							continue;
						}
						System.out.print("\nDate and Time is : ");
						SimpleDateFormat aSimpleDateformat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
						Date aDate = new Date();
						java.util.Date aUtilDate = aSimpleDateformat.parse(aSimpleDateformat.format(aDate));
						Timestamp aDateTime = new Timestamp(aUtilDate.getTime());
						System.out.println(aDateTime);
						System.out.println(" Enter Priority(1(HIGH)/2(MEDIUM)/3(LOW))");
						int ticketPriority = sc.nextInt();
						System.out.println(ticketPriority);
						TicketBO aTicketBO = new TicketBO();
						int aResult = aTicketBO.insertTicket(employeeCode, ticketmessage, ticketType, aDateTime,
								ticketPriority, aConnection);
						if (aResult == 1) {
							System.out.println("Successfully inserted a ticket");
						} else {
							System.out.println("Failed to insert a Ticket");
						}
						break;
					case 2:
						System.out.println(" Enter Employee Code : ");
						employeeCode = sc.nextLine();
						if (employeeCode.isEmpty()) {
							System.out.println(" Try Again and Please Enter Employee Code ");
							continue;
						}
						TicketBO aTicketBOForAllTickets = new TicketBO();
						List<TicketDTO> aListOfAllTickets = aTicketBOForAllTickets
								.getAllReportedTicketsByEmployee(employeeCode, aConnection);
						if (aListOfAllTickets.isEmpty()) {
							System.out.println(" There are no Tickets to Show  ");
						}
						for (TicketDTO aTicketDTO : aListOfAllTickets) {
							System.out.println(aTicketDTO.toString() + "\n");
						}
						break;
					case 3:
						continue;
					default:
						System.out.println(" Invalid Input Please Try Again ");
						break;
					}
					break;
				case 3:
					EmployeeBO aEmployeeBO = new EmployeeBO();
					List<EmployeeDTO> aListOfEmployees = aEmployeeBO.getEmployees();
					for (EmployeeDTO aEmployeeDTO : aListOfEmployees) {
						System.out.println(aEmployeeDTO.toString() + "\n");
					}
					break;
				case 4:
					System.out.println(" Application is Terminated ");
					System.exit(0);
					break;
				default:
					System.out.println(" Invalid Input Please Try Again ");
					break;
				}
			} catch (SQLServerException aSQLServerException) {
				throw new IllegalArgumentException(aSQLServerException);
			} catch (IllegalArgumentException aIllegalArgumentException) {
				System.out.println(" Invalid Input Please Try Again ");
			} catch (InputMismatchException aInputMismatchException) {
				System.out.println(" Invalid Input Please Try Again ");
			} catch (NoSuchElementException aNoSuchElementException) {
				System.out.println(" Invalid Input Please Try Again ");
			} catch (NullPointerException aNullPointerException) {
				System.out.println(" Invalid Input Please Try Again ");
			}
		}
	}
}
