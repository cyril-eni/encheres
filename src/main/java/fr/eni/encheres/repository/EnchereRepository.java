package fr.eni.encheres.repository;

import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.EncherePK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnchereRepository extends JpaRepository<Enchere, EncherePK> {

   
}
