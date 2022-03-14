package ru.netology.domain.repository;

import ru.netology.domain.domain.Player;
import ru.netology.domain.exception.NotRegisteredException;

import java.util.*;
import java.util.function.Predicate;

public class Game {
    HashMap<Player, String> items = new HashMap<>();

    public void register(Player player) {
        items.put(player, player.getName());
    }

    public Player[] findAll() {
        Set<Player> keys = items.keySet();
        return keys.toArray(new Player[0]);
    }

    public int round(String playerName1, String playerName2) {
        Player player1 = findByName(playerName1);
        Player player2 = findByName(playerName2);
        int result = player1.getStrength() - player2.getStrength();
        if (result > 0) {
            return 1;
        }
        if (result < 0) {
            return 2;
        }
        return result;
    }

    public Player findByName(String name) {
        Player[] players = findBy(player -> player.getName() == name);
        if (players.length == 0) {
            throw new NotRegisteredException("Element with name: " + name + " not found");
        }
        if (players.length > 1) {
            throw new NotRegisteredException("Element with name: " + name + " not found");
        }
        return players[0];
    }

    private Player[] findBy(Predicate<Player> filter) {
        var itemsFromGame = findAll();
        var result = new ArrayList<Player>();
        for (Player player : itemsFromGame) {
            if (filter.test(player)) {
                result.add(player);
            }
        }
        return result.toArray(new Player[0]);
    }

}

