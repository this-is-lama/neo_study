package my.project;

import static org.junit.jupiter.api.Assertions.assertEquals;

import my.project.models.VacationRequest;
import my.project.services.VacationCalculatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class VacationCalculatorServiceTest {

    private VacationCalculatorService vacationCalculatorService;

    @BeforeEach
    public void setUp() {
        vacationCalculatorService = new VacationCalculatorService();
    }

    @Test
    public void testCalculateVacationPay_withNoHolidays() {
        VacationRequest request = new VacationRequest();
        request.setAverageSalary(3000);
        request.setVacationDays(10);
        request.setVacationStartDate("06-01");

        double result = vacationCalculatorService.calculateVacationPay(request);
        assertEquals(700.0, result);
    }

    @Test
    public void testCalculateVacationPay_withHolidays() {
        VacationRequest request = new VacationRequest();
        request.setAverageSalary(3000);
        request.setVacationDays(10);
        request.setVacationStartDate("01-01");

        double result = vacationCalculatorService.calculateVacationPay(request);
        assertEquals(200.0, result);
    }

    @Test
    public void testCalculateVacationPay_withWeekends() {
        VacationRequest request = new VacationRequest();
        request.setAverageSalary(8000);
        request.setVacationDays(15);
        request.setVacationStartDate("06-01");

        double result = vacationCalculatorService.calculateVacationPay(request);
        assertEquals(2400.0, result);
    }
}
