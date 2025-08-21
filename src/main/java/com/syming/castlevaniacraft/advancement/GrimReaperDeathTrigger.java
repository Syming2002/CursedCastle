package com.syming.castlevaniacraft.advancement;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import net.minecraft.advancements.ICriterionTrigger;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.advancements.critereon.AbstractCriterionInstance;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;

import java.util.Map;
import java.util.Set;

public class GrimReaperDeathTrigger implements ICriterionTrigger<GrimReaperDeathTrigger.Instance> {

    public static final ResourceLocation ID = new ResourceLocation("grim_reaper_death");
    private final Map<PlayerAdvancements, GrimReaperDeathTrigger.Listeners> listenersMap = Maps.newHashMap();

    @Override
    public ResourceLocation getId() {
        return ID;
    }

    @Override
    public void addListener(PlayerAdvancements playerAdvancements, Listener<GrimReaperDeathTrigger.Instance> listener) {
        GrimReaperDeathTrigger.Listeners listeners = this.listenersMap.computeIfAbsent(playerAdvancements, Listeners::new);
        listeners.add(listener);
    }

    @Override
    public void removeListener(PlayerAdvancements playerAdvancementsIn, Listener<Instance> listener) {
        GrimReaperDeathTrigger.Listeners listeners = this.listenersMap.get(playerAdvancementsIn);
        if (listeners != null) {
            listenersMap.remove(listener);
            if (listenersMap.isEmpty()) {
                this.listenersMap.remove(playerAdvancementsIn);
            }
        }
    }

    @Override
    public void removeAllListeners(PlayerAdvancements playerAdvancementsIn) {
        this.listenersMap.remove(playerAdvancementsIn);
    }

    @Override
    public Instance deserializeInstance(JsonObject json, JsonDeserializationContext context) {
        return new GrimReaperDeathTrigger.Instance();
    }

    public void trigger(EntityPlayerMP playerMP) {
        GrimReaperDeathTrigger.Listeners listeners = this.listenersMap.get(playerMP.getAdvancements());
        if (listeners != null) {
            listeners.trigger();
        }
    }

    public static class Instance extends AbstractCriterionInstance {
        public Instance() {
            super(GrimReaperDeathTrigger.ID);
        }
    }

    public static class Listeners {
        private final PlayerAdvancements playerAdvancements;
        private final Set<Listener<GrimReaperDeathTrigger.Instance>> listeners = Sets.newHashSet();


        public Listeners(PlayerAdvancements playerAdvancements) {
            this.playerAdvancements = playerAdvancements;
        }

        public boolean isEmpty() {
            return this.listeners.isEmpty();
        }

        public void add(Listener<GrimReaperDeathTrigger.Instance> listener) {
            this.listeners.add(listener);
        }

        public void remove(Listener<GrimReaperDeathTrigger.Instance> listener) {
            this.listeners.remove(listener);
        }

        public void trigger() {
            for (Listener<GrimReaperDeathTrigger.Instance> listener : Lists.newArrayList(this.listeners)) {
                listener.grantCriterion(this.playerAdvancements);
            }
        }
    }
}
