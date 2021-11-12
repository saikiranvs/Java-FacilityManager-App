package main.com.planon.facilitymanager.dto.employeedto;

public class EmployeeDTO {
	private String employeeCode;
	private String employeeName;
	private String employeeRole;

	/**
	 * Constructor EmployeeDTO() sets the values of employeeCode,employeeName and
	 * employeeRole of an employee.
	 * 
	 * @param aEmployeeCode, contains the employee code.
	 * @param aEmployeeName, contains the employee name.
	 * @param aEmployeeRole, contains the employee role.
	 */
	public EmployeeDTO(String aEmployeeCode, String aEmployeeName, String aEmployeeRole) {
		this.setEmployeeCode(aEmployeeCode);
		this.setEmployeeName(aEmployeeName);
		this.setEmployeeRole(aEmployeeRole);
	}

	/**
	 * toString() method returns a string representation of the object.
	 */
	@Override
	public String toString() {
		return "EmployeeCode = " + employeeCode + ", EmployeeName = " + employeeName + ", EmployeeRole = "
				+ employeeRole;
	}

	/**
	 * 
	 * @return employeeCode, to get employee code.
	 */
	public String getEmployeeCode() {
		return employeeCode;
	}

	/**
	 * 
	 * @param employeeCode, to set employee code.
	 */
	public void setEmployeeCode(String aEmployeeCode) {
		this.employeeCode = aEmployeeCode;
	}

	/**
	 * 
	 * @return employeeName, to get employee name.
	 */
	public String getEmployeeName() {
		return employeeName;
	}

	/**
	 * 
	 * @param employeeName, to set employee name.
	 */
	public void setEmployeeName(String aEmployeeName) {
		this.employeeName = aEmployeeName;
	}

	/**
	 * 
	 * @return employeeRole, to get employee role.
	 */
	public String getEmployeeRole() {
		return employeeRole;
	}

	/**
	 * 
	 * @param employeeRole, to set employee role.
	 */
	public void setEmployeeRole(String aEmployeeRole) {
		this.employeeRole = aEmployeeRole;
	}

}
