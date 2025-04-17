public record EmployeeId(String value) {}
public record Name(String firstName, String lastName) {}
public record PersonalId(String value) {}
public record Address(String value) {}
public record Spouse(String name, PersonalId idNumber) {}
public record Child(String name, PersonalId idNumber) {}
public record MonthlyIncome(int baseSalary, int otherIncome) {}

public enum Gender { MALE, FEMALE }

// Nilai-nilai profil pajak
public record TaxProfile(
    MonthlyIncome income,
    int monthsWorked,
    int deductible,
    boolean isMarried,
    int numberOfChildren
) {}

// Batas pajak
public record TaxRules(
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