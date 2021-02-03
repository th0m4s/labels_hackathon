package dev.th0m4s.labelshackathon.labels;

import dev.th0m4s.labelshackathon.R;

class Label_FRBIO01 extends ProductLabel {
    @Override
    public String[] getCodes() {
        return new String[] {"fr-bio-01", "fr-bio-10", "fr-bio-15", "fr-bio-16", "fr-bio-12", "fr-bio-13", "fr-bio-07", "fr-bio-11"};
    }

    @Override
    public String getDisplayName() {
        return "FR-BIO-01";
    }

    @Override
    public int getImage() {
        return R.drawable.fr_bio_01;
    }
}
