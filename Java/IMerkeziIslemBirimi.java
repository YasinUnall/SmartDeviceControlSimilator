package com.basepckg;

import java.util.Timer;

public interface IMerkeziIslemBirimi {
    public void ilkDurumAl();
    public void islemAl(AgArayuzu.Islemler islem);
    private void sicaklikAlgila()
    {

    }
    private void sicaklikAlgilamaBitir()
    {

    }
    public Timer getTimer();
}
