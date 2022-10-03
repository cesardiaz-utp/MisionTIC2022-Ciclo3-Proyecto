package co.edu.utp.misiontic.cesardiaz.videotienda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import co.edu.utp.misiontic.cesardiaz.videotienda.controller.dto.ContactDto;
import co.edu.utp.misiontic.cesardiaz.videotienda.controller.dto.FilterDto;
import co.edu.utp.misiontic.cesardiaz.videotienda.service.CatalogService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


// Spring MVC

@AllArgsConstructor
@Controller
@Slf4j
public class VideoShopController {

    private CatalogService catalogService;

    @GetMapping(value = { "/", "/index", "/index.html" })
    public String goToIndex(Model model) {
        model.addAttribute("page", "home");
        return "index";
    }

    @GetMapping("/catalog")
    public String goToCatalog(Model model) {
        var categories = this.catalogService.getCategories();

        model.addAttribute("title", "Welcome to my site");
        model.addAttribute("categories", categories);
        model.addAttribute("page", "catalog");

        return "catalog";
    }

    @GetMapping("/catalog/{id}")
    public String loadCatalogById(@PathVariable("id") Integer id, Model model) {
        log.info("Cargando informacion de categoria {}", id);

        var categories = catalogService.getCategories();
        model.addAttribute("categories", categories);

        var categoryOp = this.catalogService.getCategoryById(id);
        if (categoryOp.isEmpty()) {
            // Mostrar mensaje de error
            model.addAttribute("error", "La categoria no existe");
        } else {
            var category = categoryOp.get();

            model.addAttribute("title", category.getName());
            model.addAttribute("id", category.getId());

            var categoryMovies = this.catalogService.getMoviesByCategoryId(id);

            model.addAttribute("movies", categoryMovies);
        }
        model.addAttribute("page", "catalog");

        return "catalog";
    }

    @GetMapping("/contact")
    public String goToContact(Model model) {
        model.addAttribute("page", "contact");
        return "contact";
    }

    @GetMapping("/login")
    public String goToLogin(Model model) {
        return "login";
    }

    @GetMapping("/cart")
    public String goToCart(Model model) {
        return "cart";
    }

    @PostMapping("/contact/register")
    public String postContactRegister(@ModelAttribute ContactDto contactInfo, Model model) {
        log.info(contactInfo.toString());
        
        catalogService.saveContact(contactInfo);

        model.addAttribute("info", contactInfo);
        return "thanks";
    }

    @GetMapping("/contact/register")
    public String getContactRegister(@ModelAttribute ContactDto contactInfo, Model model) {
        log.info(contactInfo.toString());
        
        catalogService.saveContact(contactInfo);

        model.addAttribute("info", contactInfo);
        return "thanks";
    }

    @GetMapping("/filter")
    public String goToFilter(@ModelAttribute FilterDto filter, Model model) {
        log.info("Filtrer: {}", filter.toString());

        // var movies = catalogService.getMoviesByFilter(filter);

        return "filter";
    }
}
