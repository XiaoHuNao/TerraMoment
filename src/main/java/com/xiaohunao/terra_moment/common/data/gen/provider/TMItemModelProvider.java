package com.xiaohunao.terra_moment.common.data.gen.provider;

import com.xiaohunao.terra_moment.TerraMoment;
import com.xiaohunao.terra_moment.common.init.TMItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class TMItemModelProvider extends ItemModelProvider  {
    public TMItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, TerraMoment.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(TMItems.SLIME_RAIN.get());
        simpleItem(TMItems.BLOOD_TEAR.get());
    }

    private void simpleItem(Item item) {
        String path = BuiltInRegistries.ITEM.getKey(item).getPath();
        this.withExistingParent(path, this.mcLoc("item/generated"))
                .texture("layer0", this.modLoc("item/" + path));
    }
}
