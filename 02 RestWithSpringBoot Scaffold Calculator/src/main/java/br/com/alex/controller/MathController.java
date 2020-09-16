package br.com.alex.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.alex.calculadora.Calculadora;
import br.com.alex.conversores.Conversor;
import br.com.alex.exception.UnsuportedMathOperatiponException;

@RestController
public class MathController {

	@RequestMapping(value = "/sum/{idOperacao}/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double soma(@PathVariable("idOperacao") String idOperacao, @PathVariable("numberOne") String numberOne,
			@PathVariable("numberTwo") String numberTwo) throws Exception {

		if (!Conversor.isNumeric(numberOne) || !Conversor.isNumeric(numberTwo)) {
			throw new UnsuportedMathOperatiponException("Favor setar um valor númerico!");
		}

		return Calculadora.calcular(Conversor.convertDouble(numberOne), Conversor.convertDouble(numberTwo), idOperacao);
	}

	@RequestMapping(value = "/subtracao/{idOperacao}/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double subtracao(@PathVariable("idOperacao") String idOperacao, @PathVariable("numberOne") String numberOne,
			@PathVariable("numberTwo") String numberTwo) throws Exception {

		if (!Conversor.isNumeric(numberOne) || !Conversor.isNumeric(numberTwo)) {
			throw new UnsuportedMathOperatiponException("Favor setar um valor númerico!");
		}

		return Calculadora.calcular(Conversor.convertDouble(numberOne), Conversor.convertDouble(numberTwo), idOperacao);
	}

	@RequestMapping(value = "/multiplicacao/{idOperacao}/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double multiplicacao(@PathVariable("idOperacao") String idOperacao,
			@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {

		if (!Conversor.isNumeric(numberOne) || !Conversor.isNumeric(numberTwo)) {
			throw new UnsuportedMathOperatiponException("Favor setar um valor númerico!");
		}

		return Calculadora.calcular(Conversor.convertDouble(numberOne), Conversor.convertDouble(numberTwo), idOperacao);
	}

	@RequestMapping(value = "/divisao/{idOperacao}/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double divisao(@PathVariable("idOperacao") String idOperacao, @PathVariable("numberOne") String numberOne,
			@PathVariable("numberTwo") String numberTwo) throws Exception {

		if (!Conversor.isNumeric(numberOne) || !Conversor.isNumeric(numberTwo)) {
			throw new UnsuportedMathOperatiponException("Favor setar um valor númerico!");
		}

		return Calculadora.calcular(Conversor.convertDouble(numberOne), Conversor.convertDouble(numberTwo), idOperacao);
	}

	@RequestMapping(value = "/media/{idOperacao}/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double media(@PathVariable("idOperacao") String idOperacao, @PathVariable("numberOne") String numberOne,
			@PathVariable("numberTwo") String numberTwo) throws Exception {

		if (!Conversor.isNumeric(numberOne) || !Conversor.isNumeric(numberTwo)) {
			throw new UnsuportedMathOperatiponException("Favor setar um valor númerico!");
		}

		return Calculadora.calcular(Conversor.convertDouble(numberOne), Conversor.convertDouble(numberTwo), idOperacao);
	}

	@RequestMapping(value = "/raizQuadrada/{idOperacao}/{numberOne}", method = RequestMethod.GET)
	public Double raizQuadrada(@PathVariable("idOperacao") String idOperacao,
			@PathVariable("numberOne") String numberOne) throws Exception {

		if (!Conversor.isNumeric(numberOne)) {
			throw new UnsuportedMathOperatiponException("Favor setar um valor númerico!");
		}

		return Calculadora.calcular(Conversor.convertDouble(numberOne), null, idOperacao);
	}
}
