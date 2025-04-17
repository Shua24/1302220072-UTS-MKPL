public record EmployeeId(String value) {}
public record Name(String firstName, String lastName) {}
public record PersonalId(String value) {}
public record Address(String value) {}
public record Spouse(String name, PersonalId idNumber) {}
public record Child(String name, PersonalId idNumber) {}
public record MonthlyIncome(int baseSalary, int otherIncome) {}

public enum Gender { MALE, FEMALE }