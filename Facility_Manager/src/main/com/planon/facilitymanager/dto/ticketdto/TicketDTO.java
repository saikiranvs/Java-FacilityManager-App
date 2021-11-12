package main.com.planon.facilitymanager.dto.ticketdto;

import java.util.Date;

public class TicketDTO {
	private String ticketCode;
	private String ticketMessage;
	private String ticketType;
	private Date dateTime;
	private int ticketPriority;
	private String ticketStatus;

	/**
	 * Constructor TicketDTO() sets the values of
	 * ticketCode,ticketMessage,ticketType,dateTime,ticketPriority and ticketStatus
	 * of the ticket.
	 * 
	 * @param aTicketCode,     contains the ticket code.
	 * @param aTicketMessage,  contains the ticket messaage.
	 * @param aTicketType,     contains the ticket type.
	 * @param aDateTime,       contains the ticket date and time.
	 * @param aTicketPriority, contains the ticket priority.
	 * @param aTicketStatus,   contains the ticket status.
	 */
	public TicketDTO(String aTicketCode, String aTicketMessage, String aTicketType, int aTicketPriority, Date aDateTime,
			String aTicketStatus) {
		this.setTicketCode(aTicketCode);
		this.setMessage(aTicketMessage);
		this.setTicketType(aTicketType);
		this.setDateTime(aDateTime);
		this.setTicketPriority(aTicketPriority);
		this.setTicketStatus(aTicketStatus);
	}

	/**
	 * toString() method returns a string representation of the object.
	 */
	@Override
	public String toString() {
		return "[TicketCode = " + ticketCode + ", Message = " + ticketMessage + ", TicketType = " + ticketType
				+ ", DateTime = " + dateTime + ", TicketPriority = " + ticketPriority + ", TicketStatus = "
				+ ticketStatus + "]";
	}

	/**
	 * 
	 * @return ticketCode, to get ticket code.
	 */
	public String getTicketCode() {
		return ticketCode;
	}

	/**
	 * 
	 * @param aTicketCode, to set ticket code.
	 */
	public void setTicketCode(String aTicketCode) {
		this.ticketCode = aTicketCode;
	}

	/**
	 * 
	 * @return ticketMessage, to get ticket message.
	 */
	public String getMessage() {
		return ticketMessage;
	}

	/**
	 * 
	 * @param aTicketMessage, to set ticket message.
	 */
	public void setMessage(String aTicketMessage) {
		this.ticketMessage = aTicketMessage;
	}

	/**
	 * 
	 * @return ticketType, to get ticket type.
	 */
	public String getTicketType() {
		return ticketType;
	}

	/**
	 * 
	 * @param aTicketType, to set ticket type.
	 */
	public void setTicketType(String aTicketType) {
		this.ticketType = aTicketType;
	}

	/**
	 * 
	 * @return dateTime, to get date and time.
	 */
	public Date getDateTime() {
		return dateTime;
	}

	/**
	 * 
	 * @param aDateTime, to set date and time.
	 */
	public void setDateTime(Date aDateTime) {
		this.dateTime = aDateTime;
	}

	/**
	 * 
	 * @return ticketPriority, to get ticket priority.
	 */
	public int getTicketPriority() {
		return ticketPriority;
	}

	/**
	 * 
	 * @param aTicketPriority, to set ticket priority.
	 */
	public void setTicketPriority(int aTicketPriority) {
		this.ticketPriority = aTicketPriority;
	}

	/**
	 * 
	 * @return to get.
	 */
	public String getTicketStatus() {
		return ticketStatus;
	}

	/**
	 * 
	 * @param aTicketStatus
	 */
	public void setTicketStatus(String aTicketStatus) {
		this.ticketStatus = aTicketStatus;
	}

}
