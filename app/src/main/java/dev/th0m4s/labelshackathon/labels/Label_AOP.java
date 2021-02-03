package dev.th0m4s.labelshackathon.labels;

import dev.th0m4s.labelshackathon.R;

class Label_AOP extends ProductLabel {
    @Override
    public String[] getCodes() {
        return new String[] {"aop", "aop-martinique"};
    }

    @Override
    public String getDisplayName() {
        return "AOP";
    }

    @Override
    public int getImage() {
        return R.drawable.aop;
    }
}
