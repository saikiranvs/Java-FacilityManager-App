package main.com.planon.facilitymanager.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import main.com.planon.facilitymanager.dto.ticketdto.TicketDTO;

public class TicketUtil {
	/**
	 * method sortTickets() of type List<TicketDTO> sorts the list of all tickets
	 * according to rhe priority of the ticket, if the priority for the tickets are
	 * same then it goes to sort according to the date and time of a ticket.
	 * 
	 * @param list, list of all tickets.
	 * @return list, list of tickets with greater priority.
	 */
	public static List<TicketDTO> sortTickets(List<TicketDTO> aList) {

		Collections.sort(aList, new Comparator<TicketDTO>() {

			@Override
			public int compare(TicketDTO aFirstPriority, TicketDTO aSecondPriority) {
				if (aFirstPriority.getTicketPriority() != aSecondPriority.getTicketPriority()) {
					return aFirstPriority.getTicketPriority() - aSecondPriority.getTicketPriority();
				} else {
					return aFirstPriority.getDateTime().compareTo(aSecondPriority.getDateTime());
				}
			}
		});
		return aList;

	}
}
