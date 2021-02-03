package dev.th0m4s.labelshackathon.labels;

import dev.th0m4s.labelshackathon.R;

class Label_AgriConfiance extends ProductLabel {
    @Override
    public String[] getCodes() {
        return new String[] {"agri-confiance", "agriconfiance"};
    }

    @Override
    public String getDisplayName() {
        return "Agri Confiance";
    }

    @Override
    public int getImage() {
        return R.drawable.agri_confiance;
    }
}
