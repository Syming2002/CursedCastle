package com.syming.cursedcastle.util;


import dev.corgitaco.enhancedcelestials2core.EnhancedCelestials;
import dev.corgitaco.enhancedcelestials2core.api.EnhancedCelestialsRegistry;
import dev.corgitaco.enhancedcelestials2core.api.lunarevent.LunarEvent;
import dev.corgitaco.enhancedcelestials2core.api.lunarevent.LunarForecast;
import dev.corgitaco.enhancedcelestials2defaultlunarevents.core.lunarevent.StandardLunarEvents;
import net.minecraft.core.Registry;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.ModList;

public class BloodMoonUtil {

    public static boolean isBloodMoon(Level level) {
        if (ModList.get().isLoaded("enhancedcelestials2defaultlunarevents") && ModList.get().isLoaded("enhancedcelestials2core")) {
            if (EnhancedCelestials.lunarForecastWorldData(level).isPresent()) {
                LunarForecast lunarForecast = EnhancedCelestials.lunarForecastWorldData(level).get();
                LunarEvent currentEvent = lunarForecast.currentLunarEvent();

                Registry<LunarEvent> lunarEventRegistry = level.registryAccess()
                        .registry(EnhancedCelestialsRegistry.LUNAR_EVENT_KEY).orElseThrow();
                LunarEvent bloodMoon = lunarEventRegistry.get(StandardLunarEvents.BLOOD_MOON);

                LunarEvent superBloodMoon = lunarEventRegistry.get(StandardLunarEvents.SUPER_BLOOD_MOON);

                return currentEvent == bloodMoon || currentEvent == superBloodMoon;
            } else {
                return false;
            }
        }

        return false;
    }

}
