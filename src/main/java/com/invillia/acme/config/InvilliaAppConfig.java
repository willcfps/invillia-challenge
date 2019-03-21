package com.invillia.acme.config;

import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InvilliaAppConfig {

	private Validator validator;

	@Bean
	public Validator beanValidator() {
		if (this.validator == null) {
			this.validator = Validation.buildDefaultValidatorFactory().getValidator();
		}

		return this.validator;
	}
}
