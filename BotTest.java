package tp.chinesecheckers;

import static org.junit.Assert.*;

import org.junit.Test;

public class BotTest {

  @Test
  public void testBota() {
    Zawodnik bot = new Bot("Nazwa Bota");
    assertEquals("Nazwa Bota", bot.podajNazwe());
    assertTrue(bot instanceof Bot);
  }

  //Reszta test�w pokrywa si� z testami z GraczTest
}
