package br.com.alura.alurator.playground.reflexao;

import java.lang.reflect.InvocationTargetException;

import br.com.alura.alurator.playground.controle.SubControle;

public class TesteInstanciaObjetoCorretamente {

	public static void main(String[] args) throws Exception {
		
		Class<SubControle> subControleClasse1 = SubControle.class;
		
		Class<?> subControleClasse2 = Class.forName("br.com.alura.alurator.playground.controle.SubControle");
		
		Class<?> controleClasse1 = Class.forName("br.com.alura.alurator.playground.controle.Controle");
		
		try {
			controleClasse1.getDeclaredConstructor().newInstance();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			System.out.println(e.getTargetException());
		}
		
		//controleClasse1.newInstance();
		
		//Constructor<SubControle> constructorSubControle = subControleClasse1.getDeclaredConstructor(String.class);
		
		/*
		Constructor<SubControle> constructorSubControle = subControleClasse1.getDeclaredConstructor();
		
		System.out.println(constructorSubControle);
		
		constructorSubControle.setAccessible(true);
		
		SubControle subControle = constructorSubControle.newInstance();
		
		System.out.println(subControle);
		*/
	}

}
