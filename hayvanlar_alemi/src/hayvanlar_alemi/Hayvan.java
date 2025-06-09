package hayvanlar_alemi;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

class Hayvan {
    int x, y;
    String tur;
    String cinsiyet;
    boolean hayatta = true;

    Hayvan(String tur, String cinsiyet, int x, int y) {
        this.x = x;
        this.y = y;
        this.tur = tur;
        this.cinsiyet = cinsiyet;
    }

    void hareketEt(int adim) {
        Random rand = new Random();
        int kalanAdim = adim;

        while (kalanAdim > 0) {
            int yon = rand.nextInt(2); 
            int ileriGeri = rand.nextBoolean() ? 1 : -1;

            if (yon == 0) {
                x += ileriGeri;
            } 
            else {
                y += ileriGeri;
            }

            // Sınır kontrolü
            if (x < 0) x = 0;
            if (x > 499) x = 499;
            if (y < 0) y = 0;
            if (y > 499) y = 499;

            kalanAdim--;
        }
    }

    double mesafe(Hayvan mesafe) {
        return Math.sqrt(Math.pow(x - mesafe.x, 2) + Math.pow(y - mesafe.y, 2));
    }
}