package tp.chinesecheckers;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author mdlot
 *
 */
public class TworcaBota extends TworcaZawodnika {
  
  /**
   * Lista pionkow.
   */
  private transient List<Pionek> pionek;
  
  /**
   * Lista promienia.
   */
  private transient List<Pionek> promien;
  
  /**
   * Konstruktor.
   * @param nazwa Nazwa bota
   */
  public TworcaBota(final String nazwa) {
    super(nazwa);
    pionek = new ArrayList<Pionek>();
    promien = new ArrayList<Pionek>();
  }
  
  /**
   * Tworzy bota.
   */
  @Override
  public Zawodnik stworzZawodnika() {
    final Zawodnik bot = new Bot(nazwa);
    
    //Dodanie pionk�w do bota
    Pionek pion;
    for (int i = 0; i < pionek.size(); i++) {
      pion = pionek.get(i);
      bot.dodajPionek(pion.podajX(), pion.podajY());
    }
    
    //Dodanie promienia do bota
    Pionek prom;
    for (int i = 0; i < pionek.size(); i++) {
      prom = promien.get(i);
      bot.dodajPromien(prom.podajX(), prom.podajY());
    }
    
    return bot;
  }
  
  /**
   * Wczytuje dane gracza.
   * @param gracz Gracz do wczytania.
   */
  public void wczytajGracza(final Gracz gracz) {
    this.pionek = gracz.podajPionki();
    this.promien = gracz.podajPromien();
    this.nazwa = gracz.podajNazwe();
  }
  
  /**
   * Rozk�ada pionki na p�noc.
   * @param pozycjaX
   * @param pozycjaY
   */
  private void rozlozNaN(final int pozycjaX, final int pozycjaY, List<Pionek> lista) {
    lista.add(new Pionek(pozycjaX, pozycjaY));
    lista.add(new Pionek(pozycjaX - 1, pozycjaY - 1));
    lista.add(new Pionek(pozycjaX + 1, pozycjaY - 1));
    lista.add(new Pionek(pozycjaX - 2, pozycjaY - 2));
    lista.add(new Pionek(pozycjaX, pozycjaY - 2));
    lista.add(new Pionek(pozycjaX + 2, pozycjaY - 2));
    lista.add(new Pionek(pozycjaX - 3, pozycjaY - 3));
    lista.add(new Pionek(pozycjaX - 1, pozycjaY - 3));
    lista.add(new Pionek(pozycjaX + 1, pozycjaY - 3));
    lista.add(new Pionek(pozycjaX + 3, pozycjaY - 3));
  }

  /**
   * Rozk�ada pionki na po�udnie.
   * @param pozycjaX
   * @param pozycjaY
   */
  private void rozlozNaS(final int pozycjaX, final int pozycjaY, List<Pionek> lista) {
    lista.add(new Pionek(pozycjaX, pozycjaY));
    lista.add(new Pionek(pozycjaX - 1, pozycjaY + 1));
    lista.add(new Pionek(pozycjaX + 1, pozycjaY + 1));
    lista.add(new Pionek(pozycjaX - 2, pozycjaY + 2));
    lista.add(new Pionek(pozycjaX, pozycjaY + 2));
    lista.add(new Pionek(pozycjaX + 2, pozycjaY + 2));
    lista.add(new Pionek(pozycjaX - 3, pozycjaY + 3));
    lista.add(new Pionek(pozycjaX - 1, pozycjaY + 3));
    lista.add(new Pionek(pozycjaX + 1, pozycjaY + 3));
    lista.add(new Pionek(pozycjaX + 3, pozycjaY + 3));
  }
  
  /**
   * Rozk�ada pionki.
   * @param pozycjaX
   * @param pozycjaY
   * @param kierunek Kierunek rozk�adania
   */
  public void ustawMiejsceStartowe(final int pozycjaX, final int pozycjaY,
      final KierunekTworzeniaPionkow kierunek) {
    pionek.clear();
    
    int pozX;
    int pozY;
    
    switch (kierunek) {
      case N:
        rozlozNaN(pozycjaX, pozycjaY, pionek);
        pozX = pozycjaX;
        pozY = pozycjaY - 16;
        rozlozNaS(pozX, pozY, promien);
        break;
      case S:
        rozlozNaS(pozycjaX, pozycjaY, pionek);
        pozX = pozycjaX;
        pozY = pozycjaY + 16;
        rozlozNaS(pozX, pozY, promien);
        break;
      case NW:
        pozX = pozycjaX - 3;
        pozY = pozycjaY - 3;
        rozlozNaS(pozX, pozY, pionek);
        pozX -= 18;
        rozlozNaS(pozX, pozY, promien);
        break;
      case NE:
        pozX = pozycjaX + 3;
        pozY = pozycjaY - 3;
        rozlozNaS(pozX, pozY, pionek);
        pozX += 18;
        rozlozNaS(pozX, pozY, pionek);
        break;
      case SW:
        pozX = pozycjaX - 3;
        pozY = pozycjaY + 3;
        rozlozNaN(pozX, pozY, pionek);
        pozX -= 18;
        rozlozNaN(pozX, pozY, promien);
        break;
      case SE:
        pozX = pozycjaX + 3;
        pozY = pozycjaY + 3;
        rozlozNaN(pozX, pozY, pionek);
        pozX += 18;
        rozlozNaN(pozX, pozY, promien);
        break;
      default:
        break;
    }
  }
}
