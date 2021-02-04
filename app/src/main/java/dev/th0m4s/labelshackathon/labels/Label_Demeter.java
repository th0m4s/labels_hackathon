package dev.th0m4s.labelshackathon.labels;

import dev.th0m4s.labelshackathon.R;

class Label_Demeter extends ProductLabel {
    @Override
    public String[] getCodes() {
        return new String[] {"demeter"};
    }

    @Override
    public String getDisplayName() {
        return "Demeter";
    }

    @Override
    public int getImage() {
        return R.drawable.demeter;
    }

    @Override
    public int getTrust() {
        return 2;
    }

    @Override
    public String getDesc() {
        return "Certifie une agriculture biodynamique : respecte les saisons et les rythmes biologiques pour permettre une regénération des sols.\nDe manière générale, ce label est plus strict que le label européen.";
    }
}
