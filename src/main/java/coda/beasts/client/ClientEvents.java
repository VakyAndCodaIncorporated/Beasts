package coda.beasts.client;

import coda.beasts.Beasts;
import coda.beasts.common.items.BeastsSpawnEggItem;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = Beasts.MOD_ID)
public class ClientEvents {

    @OnlyIn(Dist.CLIENT)
    public static void init() {

    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void itemColors(ColorHandlerEvent.Item event) {
        ItemColors handler = event.getItemColors();
        IItemColor eggColor = (stack, tintIndex) -> ((BeastsSpawnEggItem) stack.getItem()).getColor(tintIndex);
        for (BeastsSpawnEggItem e : BeastsSpawnEggItem.SPAWN_EGGS) handler.register(eggColor, e);
    }
}
