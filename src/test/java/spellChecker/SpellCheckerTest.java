package spellChecker;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Akky on 24-05-2016.
 */
public class SpellCheckerTest {

    @Test
    public void verify() throws Exception {
        assertTrue(SpellChecker.verify("would"));
        assertTrue(SpellChecker.verify("inception"));
        assertFalse(SpellChecker.verify("Winteriscoming"));
    }
}