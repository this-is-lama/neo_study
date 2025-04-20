package my.project.services;

import my.project.models.VacationRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Service
public class VacationCalculatorService {

	private static final Set<LocalDate> HOLIDAYS = new HashSet<>();
	private static final int YEAR = 2025;

	static {
		//Новогодние каникулы и 7 января — Рождество Христово;
		for (int day = 1; day <= 8; day++) {
			HOLIDAYS.add(LocalDate.of(YEAR, 1, day));
		}
		HOLIDAYS.add(LocalDate.of(YEAR, 1, 7));
		// День защитника Отечества
		HOLIDAYS.add(LocalDate.of(YEAR, 2, 23));
		// Международный женский день
		HOLIDAYS.add(LocalDate.of(YEAR, 3, 8));
		// Праздник Весны и Труда
		HOLIDAYS.add(LocalDate.of(YEAR, 5, 1));
		// День Победы
		HOLIDAYS.add(LocalDate.of(YEAR, 5, 9));
		// День России
		HOLIDAYS.add(LocalDate.of(YEAR, 6, 12));
		// День народного единства
		HOLIDAYS.add(LocalDate.of(YEAR, 11, 4));
	}

	public double calculateVacationPay(VacationRequest request) {
		int vacationDays = request.getVacationDays();
		int daysForPay = vacationDays;
		LocalDate vacationStartDate = LocalDate.parse(YEAR + "-" + request.getVacationStartDate());
		for (int day = 0; day < vacationDays; day++) {
			LocalDate current = vacationStartDate.plusDays(day);
			int dayOfWeek = current.getDayOfWeek().getValue();
			if (HOLIDAYS.contains(current) || dayOfWeek == 6 || dayOfWeek == 7) {
				daysForPay--;
			}
		}
		double dailyRate = request.getAverageSalary() / 30;
		return dailyRate * daysForPay;
	}

}
