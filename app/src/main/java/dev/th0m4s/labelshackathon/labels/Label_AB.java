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

    @Override
    public int getTrust() {
        return 1;
    }

    @Override
    public String getDesc() {
        return "Label Français aujourd'hui calqué sur le label européen depuis 2009.\nSouvent utilisé en plus à des fins de communication en plus du label européen.";
    }
}
