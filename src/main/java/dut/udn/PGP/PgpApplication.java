package dut.udn.PGP;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "dut.udn.PGP")
public class PgpApplication {

	public static void main(String[] args) {
		SpringApplication.run(PgpApplication.class, args);
	}

}
