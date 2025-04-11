package fr.eni.encheres.service.impl;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.repository.ArticleRepository;
import fr.eni.encheres.repository.EnchereRepository;
import fr.eni.encheres.repository.RetraitRepository;
import fr.eni.encheres.service.ArticleService;
import fr.eni.encheres.service.CategorieService;
import fr.eni.encheres.service.UtilisateurService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Profile("prod") // cette classe sera utilisée comme implémentation uniquement si le profil actif est "dev"
@Service // On doit annoter notre classe de service avec @Service afin qu'elle soit dans le contexte Spring
public class ArticleServiceJpaImpl implements ArticleService {


	// pas besoin d'@Autowired car attributs injectés dans le constructeur
	private CategorieService categorieService;

	// pas besoin d'@Autowired car attributs injectés dans le constructeur
	private ArticleRepository articleRepository;

	// pas besoin d'@Autowired car attributs injectés dans le constructeur
	private EnchereRepository enchereRepository;

	// pas besoin d'@Autowired car attributs injectés dans le constructeur
	private RetraitRepository retraitRepository;


	// pas besoin d'@Autowired car attributs injectés dans le constructeur
	private UtilisateurService utilisateurService;

	/**
	 * Injection AUTOMATIQUE de dans le constructeur
	 * => ne nécessite pas d'@Autowire
	 */
	public ArticleServiceJpaImpl(CategorieService categorieService, ArticleRepository articleRepository, EnchereRepository enchereRepository, RetraitRepository retraitRepository, UtilisateurService utilisateurService) {
		this.categorieService = categorieService;
		this.enchereRepository = enchereRepository;
		this.articleRepository = articleRepository;
		this.retraitRepository = retraitRepository;
		this.utilisateurService = utilisateurService;

		/*si aucun article n'est présent en base de donéne, on ajoute*/
		if (articleRepository.findAll().isEmpty()) {
			// creerArticleVendu(new ArticleVendu("skis", "super skis", 10, 10, false, LocalDate.now(), LocalDate.of(2025,4,26), categorieService.getCategorieById(4), utilisateurService.getUtilisateurById(2)), new Retrait("3 rue du port", "29200", "Brest"));
			// creerArticleVendu(new ArticleVendu("ordi", "super ordi", 20, 20, false, LocalDate.now(), LocalDate.of(2025,4,28), categorieService.getCategorieById(3), utilisateurService.getUtilisateurById(2)), new Retrait("2 rue des mouettes", "29100", "Douarnenez"));
			// creerArticleVendu(new ArticleVendu("table", "super table", 15, 15, false, LocalDate.now(), LocalDate.of(2025,5,12), categorieService.getCategorieById(1), utilisateurService.getUtilisateurById(2)), new Retrait("12 avenue des goelans", "29000", "Quimper"));
		}
	}

	@Override
	public List<ArticleVendu> consulterArticleVendus() {
		return articleRepository.findAll();
	}

	@Override
	public ArticleVendu consulterArticleVenduParId(int id) {
		return articleRepository.findById(id).orElse(null);
	}

	@Override
	public void creerArticleVendu(ArticleVendu article) {
		Retrait retrait = article.getRetrait();
		article.setRetrait(null);
		articleRepository.save(article);
		retrait.setNoArticle(article.getNoArticle());
		retraitRepository.save(retrait);
		article.setRetrait(retrait);
		articleRepository.save(article);

	}

	@Override
	public void creerEnchere(Enchere enchere) {
		this.enchereRepository.save(enchere);

	}


}
