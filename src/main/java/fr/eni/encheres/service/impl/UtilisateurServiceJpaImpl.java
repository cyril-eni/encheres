package fr.eni.encheres.service.impl;

import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.repository.UtilisateurRepository;
import fr.eni.encheres.service.UtilisateurService;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Profile("prod") // cette classe sera utilisée comme implémentation uniquement si le profil actif est "dev"
@Service // On doit annoter notre classe de service avec @Service afin qu'elle soit dans le contexte Spring
public class UtilisateurServiceJpaImpl implements UtilisateurService {


    private UtilisateurRepository utilisateurRepository;
    private PasswordEncoder passwordEncoder;

/*    @Value("${config.pointBaseNouvelUtilisateur}")
    private String pointBaseNouvelUtilisateur;*/

    // on injecte le password encoder dans le constructeur
    public UtilisateurServiceJpaImpl(UtilisateurRepository utilisateurRepository, PasswordEncoder passwordEncoder) {
        this.utilisateurRepository = utilisateurRepository;
        this.passwordEncoder = passwordEncoder;

        if (utilisateurRepository.findAll().isEmpty()) {
            // on ajoute un utilisateur par défaut
            addUtilisateur(new Utilisateur( "cyrilmace", "Mace", "Cyril", "cmace@campus-en.fr", "0111223322","12 rue des mouettes", "29100", "Douarnenez",  "Pa$$w0rd", 10, true));
        }

    }


    @Override
    public List<Utilisateur> getUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    @Override
    public Utilisateur getUtilisateurById(int id) {
        // ATTENTION : getReferenceById(id) retourne un proxy => ca peut provoquer des bugs
        // on retourne le genre correspondant à l'id si on le trouve, sinon null
        return utilisateurRepository.findById(id).orElse(null);
    }

    @Override
    public Utilisateur getUtilisateurByPseudo(String pseudo) {
        return utilisateurRepository.getUtilisateurByPseudo(pseudo);
    }

    @Override
    public void addUtilisateur(Utilisateur utilisateur) {
        utilisateur.setMotDePasse(passwordEncoder.encode(utilisateur.getMotDePasse()));
        utilisateurRepository.save(utilisateur);
    }

    @Override
    public void deleteUtilisateurById(int id) {
        utilisateurRepository.deleteById(id);
    }
}
