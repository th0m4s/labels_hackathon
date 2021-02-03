package dev.th0m4s.labelshackathon.labels;

import dev.th0m4s.labelshackathon.R;

class Label_AOC extends ProductLabel {
    @Override
    public String[] getCodes() {
        return new String[] {"aoc", "aoc-martinique", "aoc-suisse"};
    }

    @Override
    public String getDisplayName() {
        return "AOC";
    }

    @Override
    public int getImage() {
        return R.drawable.aoc;
    }
}
