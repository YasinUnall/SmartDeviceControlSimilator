package com.basepckg;

public interface IDurum {
    public Durum.KullaniciDurumlar kullaniciDurumGoruntule();
    public void setKullaniciDurum(Durum.KullaniciDurumlar kullaniciDurum);
    public void setYoneticiDurum(Durum.YoneticiDurumlar yoneticiDurum);
    public Durum.YoneticiDurumlar yoneticiDurumGoruntule();
}
