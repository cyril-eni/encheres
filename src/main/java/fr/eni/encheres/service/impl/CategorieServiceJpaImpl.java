package fr.eni.encheres.service.impl;


import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.repository.CategorieRepository;
import fr.eni.encheres.service.CategorieService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Profile("prod") // cette classe sera utilisée comme implémentation uniquement si le profil actif est "dev"
@Service // On doit annoter notre classe de service avec @Service afin qu'elle soit dans le contexte Spring
public class CategorieServiceJpaImpl implements CategorieService {


    private CategorieRepository categorieRepository;

    /**
     * On injecte le categorieRepository dans le constructeur
     */
    public CategorieServiceJpaImpl(CategorieRepository categorieRepository) {
        this.categorieRepository = categorieRepository;

        /**
         * Lors du démarrage de l'application, si aucun categorie n'est créé : on le fait
         */
        if (categorieRepository.findAll().isEmpty()) {
            addCategorie(new Categorie( "Maison"));
            addCategorie(new Categorie("Outils"));
            addCategorie(new Categorie("Informatique"));
            addCategorie(new Categorie( "Sport"));
            addCategorie(new Categorie( "Art et littérature"));
        }

    }

    @Override
    public List<Categorie> getCategories() {
        return categorieRepository.findAll();
    }

    @Override
    public Categorie getCategorieById(int id) {
       // ATTENTION : getReferenceById(id) retourne un proxy => ca peut provoquer des bugs
       // on retourne le categorie correspondant à l'id si on le trouve, sinon null
       return categorieRepository.findById(id).orElse(null);
    }

    @Override
    public void addCategorie(Categorie categorie) {
        categorieRepository.save(categorie);
    }


    @Override
    public void deleteCategorieById(int id) {
        categorieRepository.deleteById(id);
    }
}
