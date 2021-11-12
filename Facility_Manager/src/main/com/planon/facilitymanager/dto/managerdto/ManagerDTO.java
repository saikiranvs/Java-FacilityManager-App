package main.com.planon.facilitymanager.dto.managerdto;

import java.util.ArrayList;
import java.util.List;

import main.com.planon.facilitymanager.dto.employeedto.EmployeeDTO;

public class ManagerDTO extends EmployeeDTO {

	List<EmployeeDTO> subOrdinates = new ArrayList<EmployeeDTO>();

	/**
	 * Constructor ManagerDTO() accepts the values of employeeCode,employeeName and
	 * employeeRole of an employee and passes using super keyword.
	 * 
	 * @param aEmployeeCode, contains the employee code.
	 * @param aEmployeeName, contains the employee name.
	 * @param aEmployeeRole, contains the employee role.
	 */
	public ManagerDTO(String aEmployeeCode, String aEmployeeName, String aEmployeeRole) {
		super(aEmployeeCode, aEmployeeName, aEmployeeRole);
	}

	/**
	 * method getSubOrdinates() of type List<EmployeeDTO> gets the list of all
	 * subordinates.
	 * 
	 * @return subOrdinates,to get subordinates.
	 */
	public List<EmployeeDTO> getSubOrdinates() {
		return subOrdinates;
	}

	/**
	 * method setSubOrdinates() of type List<EmployeeDTO> sets the list of all
	 * subordinates.
	 * 
	 * @param subOrdinates, to set subordinates.
	 */
	public void setSubOrdinates(List<EmployeeDTO> aSubOrdinates) {
		this.subOrdinates = aSubOrdinates;
	}

}
