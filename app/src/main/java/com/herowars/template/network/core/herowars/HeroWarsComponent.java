package com.herowars.template.network.core.herowars;

import com.herowars.template.network.core.NetComponent;
import com.herowars.template.ui.activity.AbsActivity;

import dagger.Component;

@HeroWarsScope
@Component(dependencies = NetComponent.class, modules = HeroWarsModule.class)
public interface HeroWarsComponent {

    void inject(AbsActivity absActivity);
}
