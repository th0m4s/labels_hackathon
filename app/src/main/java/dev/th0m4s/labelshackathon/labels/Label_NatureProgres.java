package dev.th0m4s.labelshackathon.labels;

import dev.th0m4s.labelshackathon.R;

class Label_NatureProgres extends ProductLabel {
    @Override
    public String[] getCodes() {
        return new String[] {"nature-progres", "nature-et-progres"};
    }

    @Override
    public String getDisplayName() {
        return "Nature & Progrès";
    }

    @Override
    public int getImage() {
        return R.drawable.nature_progres;
    }

    @Override
    public int getTrust() {
        return 2;
    }

    @Override
    public String getDesc() {
        return "Mention française attestant de l'absence d'OGM, pesticides de synthèse et d'engrais chimiques.\nLes productions doivent aussi respecter les rythmes saisonniers.";
    }
}
