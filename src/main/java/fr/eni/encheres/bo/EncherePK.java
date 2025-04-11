package fr.eni.encheres.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EncherePK implements Serializable {
        private Utilisateur utilisateur;
        private ArticleVendu articleVendu;
}
