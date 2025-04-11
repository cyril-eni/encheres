package fr.eni.encheres.controller;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.security.UtilisateurSpringSecurity;
import fr.eni.encheres.service.ArticleService;
import fr.eni.encheres.service.CategorieService;
import fr.eni.encheres.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/")
public class EncheresController {

    @Autowired
    private ArticleService   articleService;

    @Autowired
    private CategorieService categorieService;

    @Autowired
    private UtilisateurService utilisateurService;

    @Value("${file.upload-dir}")
    private String uploadDir;

    /*
     * Attributs du modèle partagés par toutes les méthodes
     */
    // on va définir un attribut de modèle "categories" pour toutes les méthodes de mon controller
    @ModelAttribute("categories")public List<Categorie> getListeCategories(){
        return categorieService.getCategories();
    }


    @GetMapping
    public String articles(Model model) {
        // j'ajoute dans le modèle la liste des films
        model.addAttribute("articles", articleService.consulterArticleVendus());
        // je redirige sur le template "encheres.html"
        return "encheres";
    }

    @GetMapping("/ventes/nouvelle")
    public String nouveleEnchere(Model model) {
        model.addAttribute("article", new ArticleVendu());
        // je redirige sur le template "enchere.html"
        return "enchere";
    }

    // @PostMapping(path = "/ventes", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    @PostMapping("/ventes")
    public String nouveleVente(ArticleVendu articleVendu,  @AuthenticationPrincipal UtilisateurSpringSecurity user) throws IOException {
       articleVendu.setVendeur(user.getUtilisateur());
       articleVendu.setPrixVente(articleVendu.getMiseAPrix());
       articleService.creerArticleVendu(articleVendu);
        saveImage(articleVendu.getImage(), articleVendu.getNoArticle());

        // je redirige sur le template "enchere.html"
        return "redirect:/";
    }

    /**
     * On sauvegaede l'image dans le dossier défini dans : file.upload-dir
     * qui est aussi accesssible au chemin /imagesArticles/**
     * tel que défini dans la classe WebConfiguration
     */
    private void saveImage(MultipartFile file, Long noArticle) throws IOException {
        Path uploadPath = Paths.get(uploadDir + noArticle.toString());
        Files.copy(file.getInputStream(), uploadPath, StandardCopyOption.REPLACE_EXISTING);
    }

    @GetMapping("/ventes/{id}")
    public String recupererEnchere(@PathVariable int id, Model model) {
        // j'ajoute au modèle le film d'id correspondant
        model.addAttribute("article", articleService.consulterArticleVenduParId(id));
        return "enchere";
    }

    @PostMapping("/ventes/{id}/enchere")
    public String creerEnchere(@PathVariable int id,  Integer montantEnchere, @AuthenticationPrincipal UtilisateurSpringSecurity user) {

        ArticleVendu articleAssocie = articleService.consulterArticleVenduParId(id);
        Enchere enchere = new Enchere();
        enchere.setArticleVendu(articleAssocie);
        enchere.setMontantEnchere(montantEnchere);
        enchere.setDateEnchere(LocalDate.now());
        enchere.setUtilisateur(utilisateurService.getUtilisateurByPseudo(user.getUtilisateur().getPseudo()));
        articleService.creerEnchere(enchere);
        articleAssocie.setPrixVente(enchere.getMontantEnchere());
        articleService.creerArticleVendu(articleAssocie);
        // si création Ok, je redirige sur la page des films en GET
        return "redirect:/";
    }





}
