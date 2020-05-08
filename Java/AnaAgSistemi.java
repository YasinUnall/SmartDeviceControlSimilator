package com.basepckg;

import java.lang.management.ManagementFactory;
import java.sql.*;

public class AnaAgSistemi implements IAnaAgSistemi{
    private KullaniciHesabi kullaniciHesabi; //Kullanıcının çok sayıda hesabı olabilir.
    private Connection connection = null;

    private IAgArayuzu agArayuzu;
    private IWebSitesi webSitesi;

    public void baglan()
    {
        try
        {
            if (connection == null){
                connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/AnaAgSistemi",
                        "postgres", "admin_123");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void kullaniciDogrula(String kullaniciAdi, String sifre)
    {
        try {
            String sql= "SELECT EXISTS (" +
                    "SELECT * FROM \"kullanicihesabi\" " +
                    "WHERE \"kullanici_adi\" = '" + kullaniciAdi + "' " +
                    "AND \"sifre\" = '" + sifre + "'" +
                    ")";

            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                if ((rs.getBoolean(1))) {
                    LogYoneticisi.getInstance("Log.txt", this).dosyayaYaz(kullaniciAdi + " için başarılı kullanıcı girişi.");
                    mesajGonder("Kullanıcı girişi başarılı...!\n");
                    kullaniciOlustur(kullaniciAdi);
                    agArayuzu.ilkDurumAl();
                    durumGuncelle(Durum.YoneticiDurumlar.kontrol);
                } else {
                    LogYoneticisi.getInstance("Log.txt", this).dosyayaYaz("başarısız kullanıcı girişi.");
                    mesajGonder("Kullanıcı girişi başarısız...!\n");
                }
            }

            rs.close();
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void kullaniciOlustur(String kullaniciAdi)
    {
        try {
            String sql= "SELECT \"ad\", \"soyad\" FROM \"musteri\" WHERE \"id\" = (" +
                    "SELECT \"id\" FROM \"kullanicihesabi\" " +
                    "WHERE \"kullanici_adi\" = '" + kullaniciAdi + "'"+
                    ")";

            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            rs.next();
            Musteri musteri = new Musteri(rs.getString("ad"), rs.getString("soyad"));

            rs.close();
            stmt.close();

            this.kullaniciHesabi = new KullaniciHesabi(kullaniciAdi, musteri);
            mesajGonder("Merhaba " + kullaniciHesabi.getMusteri().getAd() + ", cihaz senin yönetiminde.\n\n");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void islemAl(AgArayuzu.Islemler islem)
    {
        agArayuzu.islemAl(islem);
    }

    public void sicaklikGuncelle(float sicaklik)
    {
        try {
            String sql= "UPDATE \"akillicihaz\" SET \"sicaklik_degeri\" = '" + sicaklik + "' WHERE(\"id\" = '"+
                   kullaniciHesabi.getKullaniciAdi() +"');";

            Statement stmt = connection.createStatement();
            stmt.executeUpdate(sql);

            stmt.close();
            sicaklikDegisimBildir();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sicaklikDegisimBildir()
    {
        webSitesi.sicaklikYaz();
        LogYoneticisi.getInstance("Log.txt", this).dosyayaSicaklikYaz();
    }

    public float sicaklikGonder()
    {
        try {
            String sql= "SELECT \"sicaklik_degeri\" FROM \"akillicihaz\" WHERE \"id\" = (" +
                    "SELECT \"kullanici_adi\" FROM \"kullanicihesabi\" " +
                    "WHERE \"kullanici_adi\" = '" + kullaniciHesabi.getKullaniciAdi() + "'"+
                    ")";

            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            rs.next();
            float sicaklik = rs.getFloat("sicaklik_degeri");

            rs.close();
            stmt.close();

            return sicaklik;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public Durum.YoneticiDurumlar dbDurumAl()
    {
        try {
            String sql= "SELECT \"durum\" FROM \"akillicihaz\" WHERE \"id\" = (" +
                    "SELECT \"kullanici_adi\" FROM \"kullanicihesabi\" " +
                    "WHERE \"kullanici_adi\" = '" + kullaniciHesabi.getKullaniciAdi() + "'"+
                    ")";

            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            rs.next();
            Durum.YoneticiDurumlar durum = Durum.YoneticiDurumlar.valueOf(rs.getString("durum"));

            rs.close();
            stmt.close();

            return durum;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void durumGuncelle(Durum.YoneticiDurumlar durum)
    {
        try {
            String sql= "UPDATE \"akillicihaz\" SET \"durum\" = '" + durum + "' WHERE(\"id\" = '"+
                    kullaniciHesabi.getKullaniciAdi() +"');";

            Statement stmt = connection.createStatement();
            stmt.executeUpdate(sql);

            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void kullaniciDurumAl(Durum.KullaniciDurumlar durum)
    {
        webSitesi.durumYaz(durum);
    }

    public void mesajGonder(String mesaj)
    {
        webSitesi.mesajYaz(mesaj);
    }

    public KullaniciHesabi getKullaniciHesabi() {
        return kullaniciHesabi;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setAgArayuzu(IAgArayuzu agArayuzu) {
        this.agArayuzu = agArayuzu;
    }

    public void setWebSitesi(IWebSitesi webSitesi) {
        this.webSitesi = webSitesi;
    }

    public void cikisYap()
    {
        agArayuzu.islemAl(AgArayuzu.Islemler.CIKIS);
        this.kullaniciHesabi = null;
        this.connection = null;
        this.webSitesi = null;
    }
}
