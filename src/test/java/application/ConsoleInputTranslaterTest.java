package application;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import domain.Player;

class ConsoleInputTranslaterTest {

    @Test
    void translatePlayerALowercase(){
        ConsoleInputTranslater consoleInputTranslater = new ConsoleInputTranslater();

        String inputString ="a";
        ConsoleInput consoleInput =consoleInputTranslater.translate(inputString);

        assertThat(consoleInput).isEqualTo(new ConsoleInput(Player.A));
    }


    @Test
    void translatePlayerBUppercase(){
        ConsoleInputTranslater consoleInputTranslater = new ConsoleInputTranslater();

        String inputString ="B";
        ConsoleInput consoleInput =consoleInputTranslater.translate(inputString);

        assertThat(consoleInput).isEqualTo(new ConsoleInput(Player.B));
    }

    @Test
    void translateNoncenceInput(){
        ConsoleInputTranslater consoleInputTranslater = new ConsoleInputTranslater();

        String inputString ="noncence";
        ConsoleInput consoleInput =consoleInputTranslater.translate(inputString);

        assertThat(consoleInput).isEqualTo(new ConsoleInput(Player.NONE));
    }

}