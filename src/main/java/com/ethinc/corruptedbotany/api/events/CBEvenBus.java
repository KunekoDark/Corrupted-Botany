package com.ethinc.corruptedbotany.api.events;

import com.ethinc.corruptedbotany.CorruptedBotany;
import com.ethinc.corruptedbotany.api.events.lootinjection.MinEssenceInject;
import com.ethinc.corruptedbotany.entities.LesserZombiePlantEntity;
import com.ethinc.corruptedbotany.entities.client.model.LesserZombiePlantEntityModle;
import com.ethinc.corruptedbotany.entities.client.renders.LesserZombiePlantEntityRender;
import com.ethinc.corruptedbotany.registers.EntityRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(modid = CorruptedBotany.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CBEvenBus {
    @SubscribeEvent
    public static void registerInjectsSerializers(@Nonnull final RegistryEvent.Register<GlobalLootModifierSerializer<?>> event)
    {
        event.getRegistry().registerAll(
                new MinEssenceInject.Serializer().setRegistryName(new ResourceLocation(CorruptedBotany.MOD_ID, "min_ess_inj"))
        );
    }

    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event){
        event.put(EntityRegistry.LESSERZOMBIE.get(), LesserZombiePlantEntity.createAttributes().build());
    }

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event){
        event.registerLayerDefinition(LesserZombiePlantEntityModle.LAYER_LOCATION, LesserZombiePlantEntityModle::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerRenders(EntityRenderersEvent.RegisterRenderers event){
        event.registerEntityRenderer(EntityRegistry.LESSERZOMBIE.get(), LesserZombiePlantEntityRender::new);
    }
}
