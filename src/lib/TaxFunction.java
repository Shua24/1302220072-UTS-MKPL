package lib;

public class TaxFunction {

	
	/**
	 * Fungsi untuk menghitung jumlah pajak penghasilan pegawai yang harus dibayarkan setahun.
	 * 
	 * Pajak dihitung sebagai 5% dari penghasilan bersih tahunan (gaji dan pemasukan bulanan lainnya
	 * dikalikan jumlah bulan bekerja dikurangi pemotongan) dikurangi penghasilan tidak kena pajak.
	 * 
	 * Jika pegawai belum menikah dan belum punya anak maka penghasilan tidak kena pajaknya adalah Rp 54.000.000.
	 * Jika pegawai sudah menikah maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000.
	 * Jika pegawai sudah memiliki anak maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000 per anak sampai anak ketiga.
	 * 
	 */
	
	
	public static int calculateTax(TaxProfile profile, TaxRules rules) {
		int monthsWorked = Math.min(profile.monthsWorked(), 12);

		int cappedChildren = Math.min(profile.numberOfChildren(), rules.maxChildren());

		int totalIncome = (profile.income().baseSalary() + profile.income().otherIncome()) * monthsWorked;

		int deductions = rules.basicDeduction() + profile.deductible();
		if (profile.isMarried()) {
			deductions += rules.marriageDeduction() + (cappedChildren * rules.childDeduction());
		}

		int taxableIncome = totalIncome - deductions;
		int tax = (int) Math.round(rules.taxRate() * taxableIncome);

		return Math.max(tax, 0);
	}
	
}
