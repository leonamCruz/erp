package tech.leonam.erp;

import com.github.javafaker.Faker;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Locale;

@SpringBootApplication
public class ErpApplication {

	public static void main(String[] args) {
        SpringApplication.run(ErpApplication.class, args);

	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public JavaMailSender mailSender() {
		return new JavaMailSenderImpl();
	}

	@Bean
	@SuppressWarnings("deprecation")
	public Faker faker() {
		return new Faker(new Locale("pt-br"));
	}

}
