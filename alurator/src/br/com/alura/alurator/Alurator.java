package br.com.alura.alurator;

import java.util.Map;

import br.com.alura.alurator.conversor.ConversorXML;
import br.com.alura.alurator.ioc.ContainerIoc;
import br.com.alura.alurator.protocolo.Request;
import br.com.alura.alurator.reflexao.ManipuladorObjeto;
import br.com.alura.alurator.reflexao.Reflexao;

public class Alurator {

	private String pacoteBase;
	private ContainerIoc container;

	public Alurator(String pacoteBase) {
		this.pacoteBase = pacoteBase;
		this.container = new ContainerIoc();
	}

	public Object executa(String url) {
		// TODO - processa a requisicao executando o metodo
		// da classe em questao

		Request request = new Request(url);

		String nomeControle = request.getNomeControle();
		String nomeMetodo = request.getNomeMetodo();
		
		Map<String, Object> params = request.getQueryParams();
		
		Class<?> classeControle = new Reflexao().getClasse(pacoteBase + nomeControle);
		Object instanciaControle = container.getInstancia(classeControle);
		Object retorno = new ManipuladorObjeto(instanciaControle)
								.getMetodo(nomeMetodo, params)
								.comTratamentoDeExcecao((metodo, excecao) -> {
									System.out.println("Erro no método " + metodo.getName() + " da classe " + metodo.getDeclaringClass().getName());
									throw new RuntimeException("ERRO!");
								})
								.invoca();
		
		System.out.println(retorno);
		
		retorno = new ConversorXML().converte(retorno);
		
		return retorno;
	}

	public <T, K extends T> void registra(Class<T> tipoFonte, Class<K> tipoDestino) {
		container.registra(tipoFonte, tipoDestino);
	}
}
