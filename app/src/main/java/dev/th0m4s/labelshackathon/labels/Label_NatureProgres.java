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
}
