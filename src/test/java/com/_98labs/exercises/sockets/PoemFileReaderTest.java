package com._98labs.exercises.sockets;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PoemFileReaderTest {

    @Test
    public void testGetLineFromFile() throws IOException {
        PoemReader poemReader = new PoemFileReader("C:\\Users\\Lenovo\\IdeaProjects\\ExerciseProject1\\src\\main\\resources\\Still I Rise.txt");
        //String line = poemReader.getLine(1);
        assertEquals("Still I Rise", poemReader.getLine(1));
        assertEquals("BY MAYA ANGELOU", poemReader.getLine(2));
        assertEquals("You may write me down in history", poemReader.getLine(3));
        assertEquals("With your bitter, twisted lies,", poemReader.getLine(4));
        assertEquals("You may trod me in the very dirt", poemReader.getLine(5));
        assertEquals("But still, like dust, I'll rise.", poemReader.getLine(6));
        assertEquals(" ", poemReader.getLine(7));
        assertEquals("Does my sassiness upset you?", poemReader.getLine(8));
        assertEquals("Why are you beset with gloom?", poemReader.getLine(9));
        assertEquals("’Cause I walk like I've got oil wells", poemReader.getLine(10));
        assertEquals("Pumping in my living room.", poemReader.getLine(11));
        assertEquals(" ", poemReader.getLine(12));
        assertEquals("Just like moons and like suns,", poemReader.getLine(13));
        assertEquals("With the certainty of tides,", poemReader.getLine(14));
        assertEquals("Just like hopes springing high,", poemReader.getLine(15));
        assertEquals("Still I'll rise.", poemReader.getLine(16));
        assertEquals(" ", poemReader.getLine(17));
        assertEquals("Did you want to see me broken?", poemReader.getLine(18));
        assertEquals("Bowed head and lowered eyes?", poemReader.getLine(19));
        assertEquals("Shoulders falling down like teardrops,", poemReader.getLine(20));
        assertEquals("Weakened by my soulful cries?", poemReader.getLine(21));
        assertEquals(" ", poemReader.getLine(22));
        assertEquals("Does my haughtiness offend you?", poemReader.getLine(23));
        assertEquals("Don't you take it awful hard", poemReader.getLine(24));
        assertEquals("’Cause I laugh like I've got gold mines", poemReader.getLine(25));
        assertEquals("Diggin’ in my own backyard.", poemReader.getLine(26));
        assertEquals(" ", poemReader.getLine(27));
        assertEquals("You may shoot me with your words,", poemReader.getLine(28));
        assertEquals("You may cut me with your eyes,", poemReader.getLine(29));
        assertEquals("You may kill me with your hatefulness,", poemReader.getLine(30));
        assertEquals("But still, like air, I’ll rise.", poemReader.getLine(31));
        assertEquals("Line not found", poemReader.getLine(-1));
        assertEquals("Line not found", poemReader.getLine(54));
        assertEquals("Line not found", poemReader.getLine(55));
    }
}