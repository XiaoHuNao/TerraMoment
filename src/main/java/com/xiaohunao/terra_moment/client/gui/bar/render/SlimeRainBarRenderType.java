package com.xiaohunao.terra_moment.client.gui.bar.render;

import com.xiaohunao.heaven_destiny_moment.client.gui.bar.MomentBar;
import com.xiaohunao.heaven_destiny_moment.client.gui.bar.render.IBarRenderType;
import com.xiaohunao.heaven_destiny_moment.common.moment.MomentInstance;
import com.xiaohunao.terra_moment.TerraMoment;
import com.xiaohunao.terra_moment.common.moment.Instance.SlimeRainInstance;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;

public class SlimeRainBarRenderType implements IBarRenderType {
    public static final ResourceLocation SLIME_RAIN_BAR_TEXTURE = ResourceLocation.fromNamespaceAndPath(TerraMoment.MODID,"textures/gui/bars/slime_rain_bar.png");
    //倒计时总时间
    public static final int TICK_TOTAL = 15 * 60 * 20;

    public static final int BAR_WIDTH = 196;
    public static final int BAR_HEIGHT = 30;

    public static final int BAR_STRIP_WIDTH = 180;
    public static final int BAR_STRIP_TIME_HEIGHT = 4;
    public static final int BAR_STRIP_KILL_HEIGHT = 7;


    @Override
    public void renderBar(GuiGraphics guiGraphics, MomentBar bar, MomentInstance momentInstance, int index) {
        Minecraft minecraft = Minecraft.getInstance();
        if (!(momentInstance instanceof SlimeRainInstance slimeRainInstance)){
            return;
        }

        //居中
        int width = minecraft.getWindow().getGuiScaledWidth();
        int height = minecraft.getWindow().getGuiScaledHeight();

        int x = (width - BAR_WIDTH) / 2;
        int y = index * (BAR_HEIGHT + 10);


        guiGraphics.blit(SLIME_RAIN_BAR_TEXTURE,x,y, 0, 0, 196, 30, 256, 64);


        long tick = slimeRainInstance.getTick();
        float percent = Math.max(0, Math.min(1, (float)(TICK_TOTAL - tick) / TICK_TOTAL));
        int renderWidth = (int) (BAR_STRIP_WIDTH * percent);
        guiGraphics.blit(SLIME_RAIN_BAR_TEXTURE, x + 14, y + 8, 14, 37, 170, 4, 256, 64);


//        Map<IActuator, KillEntityCondition.RequiredKill> tryRequiredKill = momentInstance.getTryRequiredKill();



    }
}
