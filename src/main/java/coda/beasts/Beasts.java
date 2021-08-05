package coda.beasts;

import coda.beasts.client.ClientEvents;
import coda.beasts.init.BeastsBlocks;
import coda.beasts.init.BeastsEntities;
import coda.beasts.init.BeastsItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Beasts.MOD_ID)
public class Beasts {
    public static final String MOD_ID = "beasts";

    public Beasts() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;

        bus.addListener(this::registerClient);
        bus.addListener(this::registerCommon);
        bus.addListener(this::registerEntityAttributes);

       forgeBus.addListener(this::registerBiomes);

        BeastsItems.ITEMS.register(bus);
        BeastsBlocks.BLOCKS.register(bus);
        BeastsEntities.ENTITIES.register(bus);
    }

    private void registerCommon(FMLCommonSetupEvent event) {

    }

    private void registerBiomes(BiomeLoadingEvent event) {

    }

    private void registerEntityAttributes(EntityAttributeCreationEvent event) {

    }

    private void registerClient(FMLClientSetupEvent event) {
         ClientEvents.init();
    }

    public final static ItemGroup GROUP = new ItemGroup(MOD_ID) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Items.SAND);
        }
    };
}