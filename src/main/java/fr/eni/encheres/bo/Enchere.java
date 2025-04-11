package fr.eni.encheres.bo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ENCHERES")
@IdClass(EncherePK.class)
public class Enchere {
    @ManyToOne
    @JoinColumn(name = "no_article")
    @Id
    private ArticleVendu articleVendu;

    @ManyToOne
    @JoinColumn(name = "no_utilisateur")
    @Id
    private Utilisateur utilisateur;

    private LocalDate dateEnchere;
    private Integer montantEnchere;

}
