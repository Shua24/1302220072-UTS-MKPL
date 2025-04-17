package lib;

record EmployeeId(String value) {}
record Name(String firstName, String lastName) {}
record PersonalId(String value) {}
record Address(String value) {}
record Spouse(String name, PersonalId idNumber) {}
record Child(String name, PersonalId idNumber) {}
record MonthlyIncome(int baseSalary, int otherIncome) {}

enum Gender { MALE, FEMALE }

// Nilai-nilai profil pajak
record TaxProfile(
    MonthlyIncome income,
    int monthsWorked,
    int deductible,
    boolean isMarried,
    int numberOfChildren
) {}

// Batas pajak
record TaxRules(
	int basicDeduction,
	int marriageDeduction,
	int childDeduction,
	int maxChildren,
	double taxRate
) {
	public static TaxRules defaultRules() {
		return new TaxRules(54000000, 4500000, 1500000, 3, 0.05);
	}
}