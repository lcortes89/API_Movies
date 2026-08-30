package org.luisa;

import org.springframework.boot.SpringApplication;

public class TestApp {

	public static void main(String[] args) {
		SpringApplication.from(App::main).with(TestcontainersConfiguration.class).run(args);
	}

}
