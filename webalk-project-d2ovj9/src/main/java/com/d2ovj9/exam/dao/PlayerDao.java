package com.d2ovj9.exam.dao;

import com.d2ovj9.exam.entity.Player;
import com.d2ovj9.exam.entity.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class PlayerDao {

    private EntityManager entityManager;

    // Dependency Injected
    @Autowired
    public PlayerDao(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    public List<Player> findAll() {
        Query theQuery = entityManager.createQuery("from Player ");
        List<Player> players = theQuery.getResultList();
        return players;
    }

    public Player findById(int theId) {
        Player thePlayer = entityManager.find(Player.class, theId);
        return thePlayer;
    }

    public void save(Player thePlayer) {
        Player dbPlayer = entityManager.merge(thePlayer);
        thePlayer.setId(dbPlayer.getId());
    }

    public void deletePlayer(int theId) {
        Query theQuery = entityManager.createQuery("delete from Player where id=:playerId");
        theQuery.setParameter("playerId", theId);
        theQuery.executeUpdate();
    }

    public List<Player> findPlayerByPosition(int minHeight) {
        Query theQuery = entityManager.createQuery("select p from Player p where p.height >=: minHeight");
        theQuery.setParameter("minHeight", minHeight);
        List<Player> thePlayers = theQuery.getResultList();
        return thePlayers;
    }

    public List<Player> findPlayerByKeyword(String keyword) {
        Query theQuery = entityManager
                .createQuery("select p from Player p where p.lastName like :keyword or p.firstName like :keyword");
        theQuery.setParameter("keyword","%" + keyword +"%");
        List<Player> thePlayers = theQuery.getResultList();
        return thePlayers;
    }

    // in dev
    public List<Player> findAllSorted() {
        Query theQuery = entityManager.createQuery("from Player order by firstName");
        List<Player> players = theQuery.getResultList();
        return players;
    }

}
