package com.vinsguru.playground;

import org.springframework.boot.SpringApplication;

public class TestPlaygroundApplication {

	public static void main(String[] args) {
		SpringApplication.from(PlaygroundApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
