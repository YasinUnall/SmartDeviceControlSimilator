package com.basepckg;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class LogYoneticisi {

    private static LogYoneticisi instance;
    private PrintWriter out;
    private IAnaAgSistemi anaAgSistemi;

    private LogYoneticisi(String logDosyasi, IAnaAgSistemi anaAgSistemi){
        try {
            out = new PrintWriter(new FileWriter(logDosyasi,true), true);
            this.anaAgSistemi = anaAgSistemi;
        } catch (IOException e) {e.printStackTrace();}
    }

    public static synchronized LogYoneticisi getInstance(String logDosyasi, IAnaAgSistemi anaAgSistemi){
        if(instance==null)
            instance = new LogYoneticisi(logDosyasi, anaAgSistemi);
        return instance;
    }

    public void dosyayaYaz(String mesaj) {
        out.println(LocalDateTime.now()+": "+mesaj);
    }

    public void dosyayaSicaklikYaz()
    {
        out.println(LocalDateTime.now() + ": " + anaAgSistemi.getKullaniciHesabi().getKullaniciAdi() +
                " cihazından gelen anlık sıcaklık " + anaAgSistemi.sicaklikGonder() + "C");
    }
}
