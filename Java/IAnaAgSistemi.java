package com.basepckg;

import java.sql.Connection;

public interface IAnaAgSistemi {
    public void baglan();
    public void kullaniciDogrula(String kullaniciAdi, String sifre);
    public void kullaniciOlustur(String kullaniciAdi);
    public void islemAl(AgArayuzu.Islemler islem);
    public void sicaklikGuncelle(float sicaklik);
    public void sicaklikDegisimBildir();
    public float sicaklikGonder();
    public Durum.YoneticiDurumlar dbDurumAl();
    public void durumGuncelle(Durum.YoneticiDurumlar durum);
    public void kullaniciDurumAl(Durum.KullaniciDurumlar durum);
    public void mesajGonder(String mesaj);
    public Connection getConnection();
    public KullaniciHesabi getKullaniciHesabi();
    public void setAgArayuzu(IAgArayuzu agArayuzu);
    public void setWebSitesi(IWebSitesi webSitesi);
    public void cikisYap();
}
