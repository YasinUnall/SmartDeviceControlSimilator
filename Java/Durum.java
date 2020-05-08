package com.basepckg;

public class Durum implements IDurum{
    enum KullaniciDurumlar {bekleme, kapali, algilama};
    enum YoneticiDurumlar {bekleme, kapali, algilama, kontrol};

    private KullaniciDurumlar kullaniciDurum;
    private YoneticiDurumlar yoneticiDurum;



    public void setKullaniciDurum(KullaniciDurumlar kullaniciDurum) {
        this.kullaniciDurum = kullaniciDurum;
    }

    public KullaniciDurumlar kullaniciDurumGoruntule() {
        return kullaniciDurum;
    }

    public void setYoneticiDurum(YoneticiDurumlar yoneticiDurum) {
        this.yoneticiDurum = yoneticiDurum;
    }

    public YoneticiDurumlar yoneticiDurumGoruntule(){
        return yoneticiDurum;
    }
}
