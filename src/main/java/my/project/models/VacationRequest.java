package my.project.models;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class VacationRequest {

	@Digits(integer = 10, fraction = 2)
	@Min(value = 0, message = "Средняя зарплата должна быть больше 0")
	private double averageSalary;

	@Digits(integer = 10, fraction = 0)
	@Min(value = 0, message = "Кол-во дней отпуска должно быть больше 0")
	private int vacationDays;

	@Pattern(regexp = "^(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$", message = "Дата ухода в отпуска должна быть введена в формате MM-dd")
	private String vacationStartDate;

}
