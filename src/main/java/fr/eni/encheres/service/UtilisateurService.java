package fr.eni.encheres.service;

import fr.eni.encheres.bo.Utilisateur;

import java.util.List;

/**
 * Fonctionnalités que doivent implémenter mes services
 */
public interface UtilisateurService {
    public List<Utilisateur> getUtilisateurs();

    public Utilisateur getUtilisateurById(int id);

    public Utilisateur getUtilisateurByPseudo(String pseudo);

    public void addUtilisateur(Utilisateur participanrt);

    public void deleteUtilisateurById(int id);
}
