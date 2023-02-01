package com.d2ovj9.exam.controller;

import com.d2ovj9.exam.entity.Player;
import com.d2ovj9.exam.entity.Position;
import com.d2ovj9.exam.exception.PlayerApiRequestException;
import com.d2ovj9.exam.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PlayerRestController {

    private PlayerService playerService;

    // Dependency Injected
    @Autowired
    public PlayerRestController(PlayerService thePlayerService) {
        playerService = thePlayerService;
    }

    // Játékosok
     @GetMapping("/players")
     public List<Player> findAll() {
            return playerService.findAll();
        }

    // 1 Játékos ID alapján
    @GetMapping("/players/{playerId}")
    public Player getPlayer(@PathVariable int playerId) throws HttpRequestMethodNotSupportedException {
        Player thePlayer = playerService.findById(playerId);
        if(thePlayer == null) {
            throw new PlayerApiRequestException("No player found with the id: " + playerId);
        }
        return thePlayer;
    }

    // Minimum Magasság alapján keres - TODO: át lehetne nevezni
    @GetMapping("/players/minHeight/{playerMinHeight}")
    public List<Player> findPlayerByPosition(@PathVariable int playerMinHeight)
            throws HttpRequestMethodNotSupportedException, MethodArgumentTypeMismatchException {
        List<Player> thePlayers = playerService.findPlayerByPosition(playerMinHeight);
        if(thePlayers.size() == 0) {
            throw new PlayerApiRequestException("No player found taller than: " + playerMinHeight);
        }
        return thePlayers;
    }

    // TODO: 415-ös error után ez a 2 method oldotta meg, valamelyiket ki kéne törölni?
    // Új játékos
    @PostMapping(value = "/players", consumes = "application/x-www-form-urlencoded")
    public Player addPlayer(Player thePlayer)  throws HttpMessageNotReadableException {
        thePlayer.setId(0);
        playerService.save(thePlayer);
        return thePlayer;
    }

    @PostMapping(value = "/players", consumes = "application/json")
    public Player addPlayer2(@RequestBody Player thePlayer)  throws HttpMessageNotReadableException {
        thePlayer.setId(0);
        playerService.save(thePlayer);
        return thePlayer;
    }

    // Játékos törlése
    @DeleteMapping ("/players/{playerId}")
    public String deletePlayer(@PathVariable int playerId) {
        Player thePlayer = playerService.findById(playerId);
        if (thePlayer == null) {
            throw new PlayerApiRequestException("No player found with id: " + playerId);
        } else {
            playerService.deletePlayer(playerId);
            return "Player deleted with id: " + playerId;
        }

    }

    // Játékos update
    @PutMapping("/players/{playerId}")
    public Player updatePlayer(@PathVariable int playerId, @RequestBody @Validated Player thePlayer) throws TransactionSystemException {
        thePlayer.setId(playerId);
        playerService.save(thePlayer);
        return thePlayer;
    }
}
