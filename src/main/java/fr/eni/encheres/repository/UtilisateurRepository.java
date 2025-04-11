package fr.eni.encheres.repository;

import fr.eni.encheres.bo.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {

    public Utilisateur getUtilisateurByPseudo(String pseudo);
}
