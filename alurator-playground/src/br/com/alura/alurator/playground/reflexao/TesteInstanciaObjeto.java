package br.com.alura.alurator.playground.reflexao;

import br.com.alura.alurator.playground.controle.Controle;

public class TesteInstanciaObjeto {

	public static void main(String[] args) throws Exception {
		Class<Controle> controleClasse1 = Controle.class;
		
		Controle controle = new Controle();
		
		Class<? extends Controle> controleClasse2 = controle.getClass();
		
		Class<?> controleClasse3 = Class.forName("br.com.alura.alurator.playground.controle.Controle");
		
		Controle objetoControle = controleClasse1.getDeclaredConstructor().newInstance();
		
		Object outroObjetoControle = controleClasse3.getDeclaredConstructor().newInstance();
		
		System.out.println(objetoControle instanceof Controle);
		
		System.out.println(outroObjetoControle instanceof Controle);
	}

}
