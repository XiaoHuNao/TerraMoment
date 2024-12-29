package com.xiaohunao.terra_moment.common.init;

import com.xiaohunao.terra_moment.TerraMoment;
import com.xiaohunao.terra_moment.common.item.BloodyTearItem;
import com.xiaohunao.terra_moment.common.item.GelDelicaciesItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class TMItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(TerraMoment.MODID);

    public static final DeferredHolder<Item,GelDelicaciesItem> SLIME_RAIN = ITEMS.register("slime_rain", GelDelicaciesItem::new);
    public static final DeferredHolder<Item,BloodyTearItem> BLOOD_TEAR = ITEMS.register("blood_tear", BloodyTearItem::new);
//    public static final DeferredHolder<Item,Item> SANDSTORM = ITEMS.register("sandstorm", () -> new EventConsumableItem(ModMoments.SANDSTORM));
//    public static final DeferredHolder<Item,Item> GOBLIN_ARMY = ITEMS.register("goblin_army", () -> new EventConsumableItem(ModMoments.GOBLIN_ARMY));
//    public static final DeferredHolder<Item,Item> FROST_LEGION = ITEMS.register("frost_legion", () -> new EventConsumableItem(ModMoments.FROST_LEGION));
//    public static final DeferredHolder<Item,Item> SOLAR_ECLIPSE = ITEMS.register("solar_eclipse", () -> new EventConsumableItem(ModMoments.SOLAR_ECLIPSE));
//    public static final DeferredHolder<Item,Item> PIRATE_INVASION = ITEMS.register("pirate_invasion", () -> new EventConsumableItem(ModMoments.PIRATE_INVASION));
//    public static final DeferredHolder<Item,Item> PUMPKIN_MOON = ITEMS.register("pumpkin_moon", () -> new EventConsumableItem(ModMoments.PUMPKIN_MOON));
//    public static final DeferredHolder<Item,Item> FROST_MOON = ITEMS.register("frost_moon", () -> new EventConsumableItem(ModMoments.FRO'ST_MOON));
//    public static final DeferredHolder<Item,Item> MARTIAN_MADNESS = ITEMS.register("martian_madness", () -> new EventConsumableItem(ModMoments.MARTIAN_MADNESS));
//    public static final DeferredHolder<Item,Item> LUNAR_EVENTS = ITEMS.register("lunar_events", () -> new EventConsumableItem(ModMoments.LUNAR_EVENTS));

}
