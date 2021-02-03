package dev.th0m4s.labelshackathon.labels;

import dev.th0m4s.labelshackathon.R;

class Label_BioCoherence extends ProductLabel {
    @Override
    public String[] getCodes() {
        return new String[] {"bio-coherence"};
    }

    @Override
    public String getDisplayName() {
        return "Bio Cohérence";
    }

    @Override
    public int getImage() {
        return R.drawable.bio_coherence;
    }
}
