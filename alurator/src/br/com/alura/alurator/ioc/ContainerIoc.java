package br.com.alura.alurator.ioc;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public class ContainerIoc {
	
	private Map<Class<?>, Class<?>> mapaTipos = new HashMap<>();

	public Object getInstancia(Class<?> tipoFonte) {
		Class<?> tipoDestino = mapaTipos.get(tipoFonte);
		if (tipoDestino != null)
			return getInstancia(tipoDestino);
		
		Stream<Constructor<?>> construtores = Stream.of(tipoFonte.getDeclaredConstructors());
		Optional<Constructor<?>> construtorPadrao = construtores.filter(construtor -> construtor.getParameterCount() == 0).findFirst();
		try {
			if (construtorPadrao.isPresent())
				return construtorPadrao.get().newInstance();
			
			Constructor<?> construtor = tipoFonte.getDeclaredConstructors()[0];
			List<Object> params = new ArrayList<>();
			for (Parameter param : construtor.getParameters()) {
				Class<?> tipoParametro = param.getType();
				params.add(getInstancia(tipoParametro));
			}
			return construtor.newInstance(params.toArray());
			
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) { 
            throw new RuntimeException(e);
        }
	}

	public <T, K extends T> void registra(Class<T> tipoFonte, Class<K> tipoDestino) {
		mapaTipos.put(tipoFonte, tipoDestino);
	}

	/*
	private Boolean verificaCompatibilidade(Class<?> tipoFonte, Class<?> tipoDestino) {
		// Verificação com reflection.
		return tipoFonte.isAssignableFrom(tipoDestino);
		
		// Verificação na raça
		if (tipoFonte.isInterface())
			return Stream.of(tipoDestino.getInterfaces()).anyMatch(tipoFonte::equals);
		else
			return tipoDestino.getSuperclass().equals(tipoFonte) || tipoDestino.equals(tipoFonte);
	}
	*/
}
