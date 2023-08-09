package org.kr.exredis;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableCaching
public class ExRedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExRedisApplication.class, args);
	}

	@Bean
	ApplicationRunner runner(final ContatoService contatoService) {
		return args -> {
			System.out.println("\n\n\n\n\n\n");
			System.out.println(contatoService.get(1L));
			System.out.println(contatoService.get(2L));
			System.out.println(contatoService.get(3L));
			System.out.println(contatoService.get(2L));
		};
	}
}

@Service
class ContatoService {
	private final Map<Long, Contato> contatoMap = new HashMap() {
		{
			put(1L, new Contato(1L, "fulano A", "98765-4321"));
			put(2L, new Contato(2L, "fulano BB", "97531-8642"));
			put(3L, new Contato(3L, "fulano CCC", "24680-1357"));
		}
	};

	@Cacheable(value = "contato")
	Contato get(final Long id) {
		System.out.println("... contatoService.get(" + id + ")");
		return this.contatoMap.get(id);
	}
}

record Contato(Long id, String nome, String telefone) implements Serializable {}