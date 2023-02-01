package com.d2ovj9.exam.service;

import com.d2ovj9.exam.dao.PlayerDao;
import com.d2ovj9.exam.entity.Player;
import com.d2ovj9.exam.entity.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PlayerService {

    private PlayerDao playerDao;

    // Constructor Injection
    @Autowired
    public PlayerService(PlayerDao thePlayerDao) {
        playerDao = thePlayerDao;
    }

    @Transactional
    public List<Player> findAll() {
        return playerDao.findAll();
    }

    @Transactional
    public Player findById(int theId) {
        return playerDao.findById(theId);
    }

    @Transactional
    public void save(Player thePlayer) {
        playerDao.save(thePlayer);
    }

    @Transactional
    public void deletePlayer(int theId) {
        playerDao.deletePlayer(theId);
    }

    @Transactional
    public List<Player> findPlayerByPosition(int minHeight) {
        return playerDao.findPlayerByPosition(minHeight);
    }

    @Transactional
    public List<Player> findPlayerByKeyword(String keyword) {
        return playerDao.findPlayerByKeyword(keyword);
    }

    // in dev
    @Transactional
    public List<Player> findAllSorted() {
        return playerDao.findAllSorted();
    }


}
