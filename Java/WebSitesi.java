package com.basepckg;

import org.postgresql.jdbc.EscapedFunctions2;

import javax.swing.*;
import java.util.Scanner;

public class WebSitesi implements IWebSitesi{

    IAnaAgSistemi anaAgSistemi;

    public WebSitesi(IAnaAgSistemi anaAgSistemi)
    {
        this.anaAgSistemi = anaAgSistemi;
        this.anaAgSistemi.setWebSitesi(this);
        this.anaAgSistemi.baglan();

        do {
            girisEkrani();
        }while(anaAgSistemi.getKullaniciHesabi() == null);

        byte secenek = 0;
        Scanner scanner = new Scanner(System.in);
        do {
            menuYazdir();
            secenek = scanner.nextByte();
            AgArayuzu.Islemler islem = AgArayuzu.Islemler.values()[secenek - 1];
            anaAgSistemi.islemAl(islem);

            if (islem == AgArayuzu.Islemler.ALGILAMA_BASLA)
            {
                Scanner sc = new Scanner(System.in);
                sc.nextLine();
                anaAgSistemi.islemAl(AgArayuzu.Islemler.ALGILAMA_BITIR);
            }
        }while (secenek != 5);
    }

    public void girisEkrani()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Sisteme giriş yapmak için lütfen bir kullanıcı adı ve şifre giriniz.");
        System.out.println("-----------Sistem Girişi-----------");
        System.out.print("Kullanıcı Adı: ");
        String kullaniciAdi = scanner.nextLine();
        System.out.print("Şifre: ");
        String sifre = scanner.nextLine();
        System.out.println("-----------------------------------");
        anaAgSistemi.kullaniciDogrula(kullaniciAdi,sifre);
    }

    private void menuYazdir()
    {
        System.out.println("1-) Soğutucuyu Aç");
        System.out.println("2-) Soğutucuyu Kapat");
        System.out.println("3-) Sıcaklık Oku");
        System.out.println("4-) Durum Görüntüle");
        System.out.println("5-) Hesaptan Çık");
        System.out.print("\nSeçenek: ");
    }

    public void sicaklikYaz()
    {
        System.out.println("Anlık sıcaklık değeri: " + anaAgSistemi.sicaklikGonder());
    }

    public void durumYaz(Durum.KullaniciDurumlar durum)
    {
        System.out.println("Cihaz Durumu: " + durum);
    }

    public void mesajYaz(String mesaj)
    {
        System.out.print(mesaj);
    }

    public void cikis()
    {
        anaAgSistemi.cikisYap();
    }
}
