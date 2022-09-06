package co.edu.utp.misiontic.cesardiaz.demosb2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.utp.misiontic.cesardiaz.demosb2.controller.dto.OperarRequest;
import co.edu.utp.misiontic.cesardiaz.demosb2.controller.dto.OperarResponse;

@RestController
@RequestMapping("api/calculator")
public class CalculadoraController {

    @GetMapping
    public String operarGet(
            @RequestParam("op") String op,
            @RequestParam("num1") Double num1,
            @RequestParam("num2") Double num2) {
        return calcular(op, num1, num2);

    }

    @PostMapping
    public OperarResponse operarPost(@RequestBody OperarRequest request) {
        var resultado = calcular(request.getOp(), request.getNum1(), request.getNum2());

        var response = new OperarResponse();
        response.setResultado(resultado);

        return response;
    }

    private String calcular(String op, Double num1, Double num2) {
        var resultado = 0.0;
        switch (op) {
            case "+":
                resultado = num1 + num2;
                break;
            case "-":
                resultado = num1 - num2;
                break;
            case "*":
                resultado = num1 * num2;
                break;
            case "/":
                resultado = num1 / num2;
                break;
            case "%":
                resultado = num1 % num2;
                break;
        }
        return String.format("%,.2f", resultado);
    }

}
