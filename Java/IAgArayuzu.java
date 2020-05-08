package com.basepckg;

public interface IAgArayuzu {
    public void ilkDurumAl();
    public void islemAl(AgArayuzu.Islemler islem);
    public void sicaklikGonder(float sicaklik);
    public void setMerkeziIslemBirimi(IMerkeziIslemBirimi merkeziIslemBirimi);
    public Durum.YoneticiDurumlar dbDurumAl();
    public void durumGuncelle(Durum.YoneticiDurumlar durum);
    public void kullaniciDurumGonder(Durum.KullaniciDurumlar durum);
    public void mesajGonder(String mesaj);
}
