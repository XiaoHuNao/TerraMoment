package com.xiaohunao.terra_moment.common.init;

import com.xiaohunao.heaven_destiny_moment.HeavenDestinyMoment;
import com.xiaohunao.heaven_destiny_moment.client.gui.bar.render.IBarRenderType;
import com.xiaohunao.terra_moment.TerraMoment;
import com.xiaohunao.terra_moment.client.gui.bar.render.GoblinArmyBarRenderType;
import com.xiaohunao.heaven_destiny_moment.common.init.HDMRegistries;
import com.xiaohunao.xhn_lib.api.register.FlexibleHolder;
import com.xiaohunao.xhn_lib.api.register.FlexibleRegister;

public class TMBarRenderTypes {
    public static final FlexibleRegister<IBarRenderType> BAR_RENDER_TYPE = FlexibleRegister.create(HDMRegistries.BAR_RENDER_TYPE, TerraMoment.MODID);

    public static final FlexibleHolder<IBarRenderType, ?> GOBLIN_ARMY_BAR_RENDER_TYPE = BAR_RENDER_TYPE.registerStatic("goblin_army", GoblinArmyBarRenderType::new);
}
