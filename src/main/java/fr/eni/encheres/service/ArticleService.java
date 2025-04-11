package fr.eni.encheres.service;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Enchere;

import java.util.List;

public interface ArticleService {


    public List<ArticleVendu> consulterArticleVendus();

    public ArticleVendu consulterArticleVenduParId(int id);

    public void creerArticleVendu(ArticleVendu article) ;

    public void creerEnchere(Enchere enchere) ;

}
