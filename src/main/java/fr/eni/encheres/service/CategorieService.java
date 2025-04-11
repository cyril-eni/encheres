package fr.eni.encheres.service;

import fr.eni.encheres.bo.Categorie;

import java.util.List;

/**
 * Fonctionnalités que doivent implémenter mes services
 */
public interface CategorieService {
    public List<Categorie> getCategories();

    public Categorie getCategorieById(int id);

    public void addCategorie(Categorie genre);

    public void deleteCategorieById(int id);
}
