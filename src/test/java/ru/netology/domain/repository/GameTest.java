package ru.netology.domain.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.domain.domain.Player;
import ru.netology.domain.exception.NotRegisteredException;


import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private final Game players = new Game();
    private final Player comparator = new Player();
    private final Player first = new Player(1, "SpiderMan", 500);
    private final Player second = new Player(2, "BatMan", 600);
    private final Player third = new Player(3, "SuperMan", 500);
    private final Player fourth = new Player(4, "Thor", 400);
    private final Player fifth = new Player(1, "SpiderMan", 500);

    @Test
    void FindByName() {
        players.register(first);
        players.register(second);
        players.register(third);
        players.register(fourth);


        assertEquals(first, players.findByName(first.getName()));
    }

    @Test
    void FindByName2() {

        Assertions.assertThrows(NotRegisteredException.class, () -> players.findByName("SpiderMan"));

        Player[] expected = {};
        Player[] actual = players.findAll();


        assertArrayEquals(expected, actual);
    }

    @Test
    void FindByName3() {
        players.register(first);
        players.register(fifth);


        Assertions.assertThrows(NotRegisteredException.class, () -> players.findByName("SpiderMan"));

        Player[] expected = {fifth, first};
        Player[] actual = players.findAll();


        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldRegistered() {
        players.register(first);
        players.register(second);
        players.register(fourth);

        Player[] expected = {fourth, first, second};
        Player[] actual = players.findAll();

        Arrays.sort(actual, comparator);

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldCompare() {
        players.register(first);
        players.register(second);

        int expected = 2;
        int actual = players.round("SpiderMan", "BatMan");

        assertEquals(expected, actual);
    }

    @Test
    void shouldCompare2() {
        players.register(first);
        players.register(second);
        players.register(third);

        var expected = 0;
        var actual = players.round("SpiderMan", "SuperMan");

        assertEquals(expected, actual);
    }

    @Test
    void shouldCompare3() {
        players.register(first);
        players.register(fourth);


        var expected = 1;
        var actual = players.round("SpiderMan", "Thor");


        assertEquals(expected, actual);
    }

    @Test
    void shouldRegister() throws NotRegisteredException {
        players.register(first);

        Assertions.assertThrows(NotRegisteredException.class, () -> players.round("SpiderMan", "Thor"));

        Player[] expected = {first};
        Player[] actual = players.findAll();


        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldRegister2() throws NotRegisteredException {
        players.register(second);

        Assertions.assertThrows(NotRegisteredException.class, () -> players.round("Thor", "BatMan"));

        Player[] expected = {second};
        Player[] actual = players.findAll();


        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldRegister3() throws NotRegisteredException {

        Assertions.assertThrows(NotRegisteredException.class, () -> players.round("Thor", "SpiderMan"));

        Player[] expected = {};
        Player[] actual = players.findAll();


        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldRegister4() throws NotRegisteredException {
        players.register(second);
        players.register(third);

        Assertions.assertThrows(NotRegisteredException.class, () -> players.round("Thor", "SpiderMan"));

        Player[] expected = {third, second};
        Player[] actual = players.findAll();

        Arrays.sort(actual, comparator);

        assertArrayEquals(expected, actual);
    }

}


