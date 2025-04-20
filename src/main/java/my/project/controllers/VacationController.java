package my.project.controllers;

import jakarta.validation.Valid;
import my.project.models.VacationRequest;
import my.project.services.VacationCalculatorService;

import my.project.utils.IncorrectRequestBodyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/calculate")
public class VacationController {

	private final VacationCalculatorService vacationCalculatorService;

	@Autowired
	public VacationController(VacationCalculatorService vacationCalculatorService) {
		this.vacationCalculatorService = vacationCalculatorService;
	}

	@GetMapping
	public ResponseEntity<Double> calculateVacationPay(@RequestBody @Valid VacationRequest request,
													   BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			StringBuilder errorMessage = new StringBuilder();
			List<FieldError> errors = bindingResult.getFieldErrors();
			errors.forEach(error ->
				errorMessage.append(error.getField())
						.append("-")
						.append(error.getDefaultMessage())
						.append(";\n")
			);
			throw new IncorrectRequestBodyException(errorMessage.toString());
		}
		double vacationPay = vacationCalculatorService.calculateVacationPay(request);
		return ResponseEntity.ok(vacationPay);
	}

	@ExceptionHandler
	public ResponseEntity<String> handleException(IncorrectRequestBodyException e) {
		return ResponseEntity.badRequest().body(e.getMessage());
	}
}
