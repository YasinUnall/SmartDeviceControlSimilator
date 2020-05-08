package com.basepckg;

public class AkilliCihazUygulama { //Pekçok akıllı cihaz nesnesi var. Hangisine bağlanılacağı internet üzerinden seçilmeli.

    public static void main(String[] args) {
        IAnaAgSistemi anaAgSistemi = new AnaAgSistemi();
        AkilliCihaz akilliCihaz = new AkilliCihaz(anaAgSistemi);
        IWebSitesi webSitesi = new WebSitesi(anaAgSistemi);
        webSitesi.cikis();
    }
}
