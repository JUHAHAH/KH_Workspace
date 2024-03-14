package edu.ks.jdbc1.model.vo;

public class Emp {
	private String empName;
	private String deptTitle;
	private int salary;
	
	public Emp() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Emp(String empName, String deptTitle, int salary) {
		super();
		this.empName = empName;
		this.deptTitle = deptTitle;
		this.salary = salary;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getDeptTitle() {
		return deptTitle;
	}

	public void setDeptTitle(String deptTitle) {
		this.deptTitle = deptTitle;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Emp [empName=" + empName + ", deptTitle=" + deptTitle + ", salary=" + salary + "]";
	}
	
	
	
	
}
