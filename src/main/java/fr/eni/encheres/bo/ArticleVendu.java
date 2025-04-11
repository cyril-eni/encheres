package fr.eni.encheres.bo;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

/**
 * Classe à compléter avec création classes Membre, Personne, Genre etc....
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ARTICLES_VENDUS")
public class ArticleVendu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noArticle;
    @NotEmpty
    private String nomArticle;
    private String description;
    @Min(0)
    @Column(name = "prix_initial")
    private Integer miseAPrix;
    @Min(0)
    private Integer prixVente;
    @Transient
    private boolean etatVente;

    private LocalDate dateDebutEncheres;
    private LocalDate dateFinEncheres;

    @ManyToOne
    @JoinColumn(name = "no_categorie")
    private Categorie categorie;

    @ManyToOne
    @JoinColumn(name = "no_utilisateur", nullable = false)
    private Utilisateur vendeur;


    @OneToOne
    @PrimaryKeyJoinColumn(name="noArticle")
    private Retrait retrait;

    @Transient
    private MultipartFile image;

    public ArticleVendu(Long noArticle, String nomArticle, String description, Integer miseAPrix, Integer prixVente, boolean etatVente) {
        this.noArticle = noArticle;
        this.nomArticle = nomArticle;
        this.description = description;
        this.miseAPrix = miseAPrix;
        this.prixVente = prixVente;
        this.etatVente = etatVente;
    }

    public ArticleVendu(String nomArticle, String description, Integer miseAPrix, Integer prixVente, boolean etatVente, LocalDate dateDebutEncheres, LocalDate dateFinEncheres, Categorie categorie, Utilisateur vendeur) {
        this.nomArticle = nomArticle;
        this.description = description;
        this.miseAPrix = miseAPrix;
        this.prixVente = prixVente;
        this.etatVente = etatVente;
        this.dateDebutEncheres = dateDebutEncheres;
        this.dateFinEncheres = dateFinEncheres;
        this.categorie = categorie;
        this.vendeur = vendeur;
    }
}
