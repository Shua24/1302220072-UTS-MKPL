package lib;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Employee {

	private EmployeeId employeeId;
	private Name name;
	private PersonalId personalId;
	private Address address;
	private LocalDate joinDate;
	private boolean isForeigner;
	private Gender gender;

	private MonthlyIncome monthlyIncome;
	private int annualDeductible;

	private Spouse spouse;
	private List<Child> children = new ArrayList<>();

	public Employee(EmployeeId employeeId, Name name, PersonalId personalId, Address address,
	                LocalDate joinDate, boolean isForeigner, Gender gender) {
		this.employeeId = employeeId;
		this.name = name;
		this.personalId = personalId;
		this.address = address;
		this.joinDate = joinDate;
		this.isForeigner = isForeigner;
		this.gender = gender;
	}
	
	/**
	 * Fungsi untuk menentukan gaji bulanan pegawai berdasarkan grade kepegawaiannya (grade 1: 3.000.000 per bulan, grade 2: 5.000.000 per bulan, grade 3: 7.000.000 per bulan)
	 * Jika pegawai adalah warga negara asing gaji bulanan diperbesar sebanyak 50%
	 */

	public void setMonthlySalary(int grade) {
		int base;

		switch (grade) {
			case 1 -> base = 3000000;
			case 2 -> base = 5000000;
			case 3 -> base = 7000000;
			default -> throw new IllegalArgumentException("Angka Grade tidak valid");
		}

		if (isForeigner) {
			base *= 1.5;
		}

		this.monthlyIncome = new MonthlyIncome((int) base, this.monthlyIncome.otherIncome());
	}


	public void setAnnualDeductible(int deductible) {	
		this.annualDeductible = deductible;
	}
	
	public void setAdditionalIncome(int income) {
		this.monthlyIncome = new MonthlyIncome(this.monthlyIncome.baseSalary(), income);
	}

	
	public void setSpouse(String spouseName, String spouseIdNumber) {
		this.spouse = new Spouse(spouseName, new PersonalId(spouseIdNumber));
	}

	
	public void addChild(String childName, String childIdNumber) {
		this.children.add(new Child(childName, new PersonalId(childIdNumber)));
	}

	public Spouse getSpouse() {
		return this.spouse;
	}

	public List<Child> getChildren() {
		return List.copyOf(this.children); // optional: make it unmodifiable
	}


	/* Menghitung berapa lama pegawai bekerja dalam setahun ini, 
	 * jika pegawai sudah bekerja dari tahun sebelumnya 
	 * maka otomatis dianggap 12 bulan. */

	public int getAnnualIncomeTax() {
		LocalDate now = LocalDate.now();

		int monthsWorkedThisYear = (now.getYear() == joinDate.getYear())
			? now.getMonthValue() - joinDate.getMonthValue()
			: 12;

		boolean hasSpouse = this.spouse != null;
		int numberOfChildren = this.children.size();

		return TaxFunction.calculateTax(
			monthlyIncome.baseSalary(),
			monthlyIncome.otherIncome(),
			monthsWorkedThisYear,
			annualDeductible,
			!hasSpouse,
			numberOfChildren
		);
	}
}
