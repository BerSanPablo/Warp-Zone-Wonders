package com.review.tfg.config;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.review.tfg.entity.Role;
import com.review.tfg.entity.Usuario;
import com.review.tfg.entity.Tag;
import com.review.tfg.entity.Videojuego;
import com.review.tfg.repository.TagRepository;
import com.review.tfg.repository.UsuarioRepository;
import com.review.tfg.repository.VideojuegoRepository;

@Profile("demo")
@Component
public class DatosIniciales implements CommandLineRunner {
	
	@Autowired
    private UsuarioRepository userRepo;
	
	@Autowired
	private VideojuegoRepository gameRepo;
	
	@Autowired
	private TagRepository tagRepo;
	
	@Value("${admin.nick}")
    private String adminNick;
	
	@Value("${admin.email}")
    private String adminEmail;
	
	@Value("${admin.password}")
    private String adminPassword;

	private static final Logger logger = LoggerFactory.getLogger(DatosIniciales.class);

	@Override
	public void run(String... args) throws Exception {
		
		logger.info("## Entrando al CommandLineRunner ##");

		//Si no encontramos el admin de test lo creamos
		if(userRepo.findByNick("admin") == null) {
			logger.info("## Creando admin ##");
			Usuario admin = new Usuario();
			admin.setNick(adminNick);
			admin.setEmail(adminEmail);
			admin.setPassword(new BCryptPasswordEncoder(10).encode(adminPassword));
			admin.getRoles().add(Role.ROLE_USER);
			admin.getRoles().add(Role.ROLE_ADMIN);
			userRepo.save(admin);
			logger.info("## Admin guardado ##");
		}
		
		//Rellenamos los tags
		if(tagRepo.findAll().size() == 0) {
			logger.info("## Creando tags ##");

			tagRepo.save(new Tag("Acción", "Un videojuego de acción es un videojuego en el que el jugador debe usar su velocidad, destreza y tiempo de reacción"));
			tagRepo.save(new Tag("Aventura", "Los videojuegos de aventura son un género de videojuegos, caracterizados por la investigación, exploración, la solución de rompecabezas, la interacción con personajes del videojuego, y un enfoque en el relato en vez de desafíos basados en reflejos"));
			tagRepo.save(new Tag("Plataformas", "Los videojuegos de plataformas, o simplemente plataformas, son un género de videojuegos que se caracterizan por tener que caminar, correr, saltar o escalar sobre una serie de plataformas y acantilados, con enemigos, mientras se recogen objetos para poder completar el juego"));
			tagRepo.save(new Tag("Shooter", "Videojuegos centrados en disparar"));
			tagRepo.save(new Tag("RogueLike", "Videojuegos centrados en repetir partidas desbloqueando mejoras para las siguientes"));
			tagRepo.save(new Tag("Multijugador", "Videojuegos que se pueden jugar con varios jugadores"));
			tagRepo.save(new Tag("Arcade", "Juegos que se podían jugar en recreativas"));
			tagRepo.save(new Tag("Rpg", "En los videojuegos, un RPG es un género que se caracteriza por ofrecer al jugador la posibilidad de asumir el papel de uno o varios personajes en un mundo ficticio"));
			tagRepo.save(new Tag("Estrategia", "Los videojuegos de estrategia son videojuegos que requieren que el jugador ponga en práctica sus habilidades de planeamiento para maniobrar, gestionando recursos de diverso tipo (materiales, humanos, militares...) para conseguir la victoria"));
			tagRepo.save(new Tag("Survival", "Videojuegos centrados en el aspecto de la supervivencia de tu personaje"));
			tagRepo.save(new Tag("Puzles", "Videojuegos centrados en resolver puzles"));
			
			logger.info("## Tags guardados ##");
		}
		
		logger.info("## Saliendo del CommandLineRunner ##");
	}
	
	
}
