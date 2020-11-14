package br.com.alura.alurator.playground.reflexao;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import br.com.alura.alurator.playground.controle.SubControle;

public class TesteInvocaMetodoSemParametro {

	public static void main(String[] args) throws Exception {
		
		Class<?> subControleClasse = Class.forName("br.com.alura.alurator.playground.controle.SubControle");
		
		for (Method metodo : subControleClasse.getMethods()) {
			System.out.println(metodo);
		}
		
		System.out.println("----------");
		
		for (Method metodo : subControleClasse.getDeclaredMethods()) {
			System.out.println(metodo);
		}
		
		System.out.println("----------");
		
		Constructor<?> construtorPadrao = subControleClasse.getDeclaredConstructor();
		construtorPadrao.setAccessible(true);
		
		Object subControle = construtorPadrao.newInstance();
		
		Method metodo = subControleClasse.getDeclaredMethod("metodoSubControle1");
		metodo.setAccessible(true);
		
		Object retorno = metodo.invoke(subControle);
		
		System.out.println(retorno);
	}

}
