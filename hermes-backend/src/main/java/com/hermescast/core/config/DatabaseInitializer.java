package com.hermescast.core.config;

import com.hermescast.core.model.Utilisateur;
import com.hermescast.core.model.Utilisateur.RoleType;
import com.hermescast.core.repository.UtilisateurRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DatabaseInitializer {

    @Bean
    public CommandLineRunner initDatabase(
            UtilisateurRepository utilisateurRepository,
            PasswordEncoder passwordEncoder
    ) {
        return args -> {
            String pseudoInitial = "admin";

            if (utilisateurRepository.findByPseudo(pseudoInitial).isEmpty()) {

                Utilisateur adminParDefaut = Utilisateur.builder()
                        .pseudo(pseudoInitial)
                        .password(passwordEncoder.encode("admin123"))
                        .mail("admin@hermescast.fr")
                        .actif(true)
                        .role(RoleType.REGISSEUR)
                        .build();

                utilisateurRepository.save(adminParDefaut);
                System.out.println("[Database] Aucun utilisateur trouvé. Compte '" + pseudoInitial + "' créé et sécurisé avec succès !");
            } else {
                System.out.println("[Database] Un compte '" + pseudoInitial + "' existe déjà. Étape d'initialisation ignorée.");
            }
        };
    }
}