package hayvanlar_alemi;
import java.util.ArrayList;
import java.util.Random;

class Avci {
    int x, y;

    Avci(ArrayList<Hayvan> hayvanlar) {
        Random rand = new Random();
        boolean uygunYerBulundu = false;

        while (!uygunYerBulundu) {
            int yeniX = rand.nextInt(500);
            int yeniY = rand.nextInt(500);

            boolean cakismaVar = false;

            for (Hayvan hayvan : hayvanlar) {
                if (hayvan.x == yeniX && hayvan.y == yeniY) {
                    cakismaVar = true;
                    break;
                }
            }

            if (!cakismaVar) {
                this.x = yeniX;
                this.y = yeniY;
                uygunYerBulundu = true;
            }
        }
    }

    void hareketEt() {
        Random rand = new Random();
        int yon = rand.nextInt(2); 
        int ileriGeri = rand.nextBoolean() ? 1 : -1;

        if (yon == 0) {
            x += ileriGeri;
        } 
        else {
            y += ileriGeri;
        }

        
        if (x < 0) x = 0;
        if (x > 499) x = 499;
        if (y < 0) y = 0;
        if (y > 499) y = 499;
    }

    void avla(ArrayList<Hayvan> hayvanlar) {
        for (Hayvan hayvan : hayvanlar) {
            if (hayvan.hayatta) {
                double mesafe = Math.sqrt(Math.pow(hayvan.x - x, 2) + Math.pow(hayvan.y - y, 2));
                if (mesafe <= 8) {
                    hayvan.hayatta = false;
                }
            }
        }
    }
}
