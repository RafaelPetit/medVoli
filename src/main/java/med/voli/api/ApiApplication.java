package med.voli.api;

import med.voli.api.domain.user.User;
import med.voli.api.domain.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}


	@Bean
	public CommandLineRunner inicializarUsuarioAdmin(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			if (userRepository.count() == 0) {
				String senhaEncriptada = passwordEncoder.encode("admin");
				User usuarioAdmin = new User("admin", senhaEncriptada);
				userRepository.save(usuarioAdmin);

				System.out.println("===========================================");
				System.out.println("Usuário administrador criado com sucesso!");
				System.out.println("Login: admin");
				System.out.println("Senha: admin");
				System.out.println("===========================================");
			}
		};
	}
}
