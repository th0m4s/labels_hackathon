package dev.th0m4s.labelshackathon.labels;

import dev.th0m4s.labelshackathon.R;

class Label_AB extends ProductLabel {
    @Override
    public String[] getCodes() {
        return new String[] {"ab-agriculture-biologique", "agriculture-bio", "agriculture-bio-ab"};
    }

    @Override
    public String getDisplayName() {
        return "Agriculture Biologique";
    }

    @Override
    public int getImage() {
        return R.drawable.ab;
    }
}
