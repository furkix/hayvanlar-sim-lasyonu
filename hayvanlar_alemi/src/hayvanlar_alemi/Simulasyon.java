package hayvanlar_alemi;
import java.util.ArrayList;
import java.util.Random;
import java.util.HashSet;

public class Simulasyon {
	 ArrayList<Hayvan> hayvanlar = new ArrayList<>();
	    Avci avci;

	    void baslat() {
	        ekleHayvan("KOYUN", 15, "ERKEK");
	        ekleHayvan("KOYUN", 15, "DISI");
	        ekleHayvan("INEK", 5, "ERKEK");
	        ekleHayvan("INEK", 5, "DISI");
	        ekleHayvan("TAVUK", 10, "DISI");
	        ekleHayvan("HOROZ", 10, "ERKEK");
	        ekleHayvan("KURT", 5, "ERKEK");
	        ekleHayvan("KURT", 5, "DISI");
	        ekleHayvan("ASLAN", 4, "ERKEK");
	        ekleHayvan("ASLAN", 4, "DISI");

	        avci = new Avci(hayvanlar); 
	    }

	    void ekleHayvan(String tur, int sayi, String cinsiyet) {
	        Random rand = new Random();
	        HashSet<String> doluPozisyonlar = new HashSet<>();

	        for (Hayvan hayvan : hayvanlar) {
	            doluPozisyonlar.add(hayvan.x + "," + hayvan.y);
	        }

	        for (int i = 0; i < sayi; i++) {
	            int x, y;
	            String poz;

	            do {
	                x = rand.nextInt(500);
	                y = rand.nextInt(500);
	                poz = x + "," + y;
	            } while (doluPozisyonlar.contains(poz));

	            doluPozisyonlar.add(poz);
	            hayvanlar.add(new Hayvan(tur, cinsiyet, x, y));
	        }
	    }

	    int hareketMesafesi(String tur) {
	        switch (tur) {
	            case "KOYUN":
	            case "INEK":
	                return 2;
	            case "TAVUK":
	            case "HOROZ":
	                return 1;
	            case "KURT":
	                return 3;
	            case "ASLAN":
	                return 4;
	            default:
	                return 0;
	        }
	    }

	    void calistir() {
	        for (int adim = 0; adim < 100; adim++) {
	            for (Hayvan hayvan : hayvanlar) {
	                if (hayvan.hayatta) {
	                    int adimMiktari = hareketMesafesi(hayvan.tur);
	                    hayvan.hareketEt(adimMiktari);
	                }
	            }

	            avlanma();
	            ciftlesme();
	            avci.hareketEt();
	            avci.avla(hayvanlar);
	        }

	        yazdirSonuc();
	    }

	    void avlanma() {
	        for (Hayvan avciHayvan : hayvanlar) {
	            if (!avciHayvan.hayatta) continue;

	            for (Hayvan av : hayvanlar) {
	                if (!av.hayatta || avciHayvan == av) continue;

	                double mesafe = avciHayvan.mesafe(av);

	                if (avciHayvan.tur.equals("KURT") && mesafe <= 4 &&
	                        (av.tur.equals("KOYUN") || av.tur.equals("TAVUK") || av.tur.equals("HOROZ"))) {
	                    av.hayatta = false;
	                } else if (avciHayvan.tur.equals("ASLAN") && mesafe <= 5 &&
	                        (av.tur.equals("KOYUN") || av.tur.equals("INEK"))) {
	                    av.hayatta = false;
	                }
	            }
	        }
	    }

	    void ciftlesme() {
	        ArrayList<Hayvan> yeniDogumlar = new ArrayList<>();

	        for (int i = 0; i < hayvanlar.size(); i++) {
	            Hayvan hayvan1 = hayvanlar.get(i);
	            if (!hayvan1.hayatta) continue;

	            for (int j = i + 1; j < hayvanlar.size(); j++) {
	                Hayvan hayvan2 = hayvanlar.get(j);
	                if (!hayvan2.hayatta) continue;

	                if (hayvan1.tur.equals(hayvan2.tur) && !hayvan1.cinsiyet.equals(hayvan2.cinsiyet) && hayvan1.mesafe(hayvan2) <= 3) {
	                    String yeniCinsiyet = Math.random() < 0.5 ? "ERKEK" : "DISI";
	                    yeniDogumlar.add(new Hayvan(hayvan1.tur, yeniCinsiyet, hayvan1.x, hayvan1.y));
	                }
	            }
	        }

	        hayvanlar.addAll(yeniDogumlar);
	    }

	    void yazdirSonuc() {
	        String[] turler = {"KOYUN", "INEK", "TAVUK", "HOROZ", "KURT", "ASLAN"};

	        System.out.println("\n--- SONUÇLAR ---");

	        for (String tur : turler) {
	            int sayi = 0;
	            for (Hayvan hayvan : hayvanlar) {
	                if (hayvan.hayatta && hayvan.tur.equals(tur)) {
	                    sayi++;
	                }
	            }
	            System.out.println(tur + ": " + sayi);
	        }

	        long toplamCanli = hayvanlar.stream().filter(hayvan -> hayvan.hayatta).count();
	        long toplamOlu = hayvanlar.size() - toplamCanli;

	        System.out.println("Toplam Canlı: " + toplamCanli);
	        System.out.println("Toplam Ölü  : " + toplamOlu);
	    }
}