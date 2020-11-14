package br.com.alura.alurator.playground.reflexao;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class TesteInvocaMetodoComParametro {

	public static void main(String[] args) throws Exception {
		Class<?> controleClasse = Class.forName("br.com.alura.alurator.playground.controle.Controle");
		
		Constructor<?> construtorPadrao = controleClasse.getDeclaredConstructor();
		
		Object controle = construtorPadrao.newInstance();
		
		Method metodo = controleClasse.getDeclaredMethod("metodoControle2", String.class, Integer.class);
		
		Object retorno = metodo.invoke(controle, "Ringneck", 2);
		
		System.out.println(retorno);
	}
}
