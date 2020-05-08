package com.basepckg;

public class Eyleyici implements IEyleyici{
    private boolean sogutucuDurum;

    public Eyleyici()
    {
        sogutucuDurum = false;
    }

    public void sogutucuAc()
    {
        sogutucuDurum = true;
    }

    public void sogutucuKapat()
    {
        sogutucuDurum = false;
    }

    public boolean sogutucuAcikmi() {
        return sogutucuDurum;
    }
}
