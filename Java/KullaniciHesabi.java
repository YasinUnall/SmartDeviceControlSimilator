package com.basepckg;

public class KullaniciHesabi {
    private String kullaniciAdi;
    private Musteri musteri;

    public KullaniciHesabi(String kullaniciAdi, Musteri musteri) {
        this.kullaniciAdi = kullaniciAdi;
        this.musteri = musteri;
    }

    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public Musteri getMusteri() {
        return musteri;
    }
}
