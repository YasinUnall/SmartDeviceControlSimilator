package com.basepckg;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class MerkeziIslemBirimi implements IMerkeziIslemBirimi{

    private IEyleyici eyleyici;
    private ISicaklikAlgilayici sicaklikAlgilayici;
    private IAgArayuzu agArayuzu;

    private Timer timer;
    private TimerTask task;

    Durum durum;

    public MerkeziIslemBirimi(IEyleyici eyleyici, ISicaklikAlgilayici sicaklikAlgilayici, IAgArayuzu agArayuzu){
        this.eyleyici = eyleyici;
        this.sicaklikAlgilayici = sicaklikAlgilayici;
        this.agArayuzu = agArayuzu;
        this.agArayuzu.setMerkeziIslemBirimi(this);

        durum = new Durum();
    }

    public void ilkDurumAl()
    {
        durum.setYoneticiDurum(this.agArayuzu.dbDurumAl());
        String kullaniciDurum = durum.yoneticiDurumGoruntule().toString();
        if (kullaniciDurum == "kontrol"){
            if (eyleyici.sogutucuAcikmi()) {
                durum.setKullaniciDurum(Durum.KullaniciDurumlar.bekleme);
            }
            else {
                durum.setKullaniciDurum(Durum.KullaniciDurumlar.kapali);
            }
        }
        else {
            durum.setKullaniciDurum(Durum.KullaniciDurumlar.valueOf(kullaniciDurum));
        }
    }

    public void islemAl(AgArayuzu.Islemler islem)
    {
        switch (islem){
            case SOGUTUCU_AC:
                eyleyici.sogutucuAc();
                if (eyleyici.sogutucuAcikmi())
                {
                    durum.setKullaniciDurum(Durum.KullaniciDurumlar.bekleme);
                    agArayuzu.mesajGonder("Sogutucu açıldı...!\n");
                }
                break;
            case SOGUTUCU_KAPAT:
                eyleyici.sogutucuKapat();
                if (!(eyleyici.sogutucuAcikmi()))
                {
                    durum.setKullaniciDurum(Durum.KullaniciDurumlar.kapali);
                    agArayuzu.mesajGonder("Sogutucu kapatıldı...!\n");
                }
                break;
            case ALGILAMA_BASLA:
                durum.setKullaniciDurum(Durum.KullaniciDurumlar.algilama);
                sicaklikAlgila();
                break;
            case ALGILAMA_BITIR:
                sicaklikAlgilamaBitir();
                if (eyleyici.sogutucuAcikmi()){
                    durum.setKullaniciDurum(Durum.KullaniciDurumlar.bekleme);
                } else {
                    durum.setKullaniciDurum(Durum.KullaniciDurumlar.kapali);
                }
                break;
            case DURUM_GORUNTULE:
                agArayuzu.kullaniciDurumGonder(durum.kullaniciDurumGoruntule());
                break;
            case CIKIS:
                durum.setYoneticiDurum(Durum.YoneticiDurumlar.values()[durum.kullaniciDurumGoruntule().ordinal()]);
                agArayuzu.durumGuncelle(durum.yoneticiDurumGoruntule());
                break;
            default:
                agArayuzu.mesajGonder("Yanlış bir seçim yaptınız...!\n");
                break;
        }
    }

    private void sicaklikAlgila(){
        this.task = new TimerTask() {
            @Override
            public void run() {
                float sicaklik = sicaklikAlgilayici.sicaklikOku(eyleyici);
                agArayuzu.sicaklikGonder(sicaklik);
            }
        };

        this.timer = new Timer(true);
        this.timer.scheduleAtFixedRate(task, 0, 2*1000);
    }

    private void sicaklikAlgilamaBitir()
    {
        timer.cancel();
    }

    public Timer getTimer() {
        return timer;
    }
}
