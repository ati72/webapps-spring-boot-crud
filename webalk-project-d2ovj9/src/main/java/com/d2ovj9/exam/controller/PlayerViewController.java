package com.d2ovj9.exam.controller;

import com.d2ovj9.exam.entity.Player;
import com.d2ovj9.exam.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Query;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/players")
public class PlayerViewController {

    PlayerService playerService;

    //Dependency Injection
    @Autowired
    public PlayerViewController(PlayerService thePlayerService) {
        playerService = thePlayerService;
    }

    // Táblázat
    @GetMapping("/players-list")
    public String listPlayers(Model theModel, String keyword) {
        List<Player> thePlayers;
        if (keyword != null) {
            thePlayers = playerService.findPlayerByKeyword(keyword);
        } else {
            thePlayers = playerService.findAll();
        }
        theModel.addAttribute("players", thePlayers);
        return "players/players-list";
    }

    // Új játékos hozzáadása view
    @GetMapping("/showAddForm")
    public String showAddForm(Model theModel) {
        Player thePlayer = new Player();
        theModel.addAttribute("player", thePlayer);
        return "players/player-form";
    }

    // Létező játékos update view
    @GetMapping("/showUpdateForm")
    public String showUpdateForm(@RequestParam("playerId") int theId, Model theModel) {
        Player thePlayer = playerService.findById(theId);
        theModel.addAttribute("player", thePlayer);
        return "players/player-form";
    }

    // TODO: lehet működik a consumes nélkül?
    // Új játékos hozzáadása / Létező felülírása
    @PostMapping(value = "/addNewPlayer", consumes = "application/x-www-form-urlencoded")
    public String addPlayer(@Valid @ModelAttribute("player") Player thePlayer, BindingResult bindingResult, Model theModel) {
        //thePlayer.setId(0);
        if (bindingResult.hasErrors()) {
            return "players/player-form";
        }
        playerService.save(thePlayer);
        return "redirect:/players/players-list";
    }

    // Játékos törlés
    @GetMapping("/deletePlayer")
    public String deletePlayer(@RequestParam("playerId") int theId) {
        playerService.deletePlayer(theId);
        return "redirect:/players/players-list";
    }

    // in dev
    @GetMapping("/players-list/sorted")
    public String listPlayers(Model theModel) {
        List<Player> thePlayers;
        thePlayers = playerService.findAllSorted();
        theModel.addAttribute("players", thePlayers);
        return "players/players-list";
    }

}
