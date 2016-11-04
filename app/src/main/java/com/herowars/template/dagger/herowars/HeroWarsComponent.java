package com.herowars.template.dagger.herowars;

import com.herowars.template.activity.AbsActivity;
import com.herowars.template.dagger.base.NetComponent;

import dagger.Component;

@HeroWarsScope
@Component(dependencies = NetComponent.class, modules = HeroWarsModule.class)
public interface HeroWarsComponent {

    void inject(AbsActivity absActivity);
}
