package fr.eni.encheres.security;

import fr.eni.encheres.bo.Utilisateur;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Cette classe est un "wrapper" autour de ma classe Membre
 * Afin de pouvoir gérer moi même mes utilisateurs qui vont être verifiés par Spring Security
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UtilisateurSpringSecurity implements UserDetails {

    private Utilisateur utilisateur;

    /**
     * Comment je recupère les permissions  ?
     * => à partir de l'utilisateur
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // si le utilisateur est admin
        if (utilisateur.isAdministrateur()) {
            // je lui donne le rôle "admin" (équivalent à la permission "ROLE_admin")
            return List.of(new SimpleGrantedAuthority("ROLE_admin"));
        }
        // sinon je lui donne le rôle "user (équivalent à la permission "ROLE_user")
        return List.of(new SimpleGrantedAuthority("ROLE_user"));
    }

    /**
     * Comment je recupère le mot de passe ?
     * => à partir de l'utilisateur
     */
    @Override
    public String getPassword() {
        return this.utilisateur.getMotDePasse();
    }

    /**
     * Comment je recupère le pseudo  ?
     * => à partir de l'utilisateur
     */
    @Override
    public String getUsername() {
        return this.utilisateur.getPseudo();
    }
}
