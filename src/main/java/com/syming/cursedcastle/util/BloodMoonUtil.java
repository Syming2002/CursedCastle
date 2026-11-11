package com.syming.cursedcastle.util;

import dev.corgitaco.enhancedcelestials2core.EnhancedCelestials;
import dev.corgitaco.enhancedcelestials2core.api.lunarevent.LunarEvent;
import dev.corgitaco.enhancedcelestials2core.api.lunarevent.LunarForecast;
import net.minecraft.core.Registry;
import net.minecraft.world.level.Level;

import static dev.corgitaco.enhancedcelestials2core.api.EnhancedCelestialsRegistry.LUNAR_EVENT_KEY;
import static dev.corgitaco.enhancedcelestials2defaultlunarevents.core.lunarevent.StandardLunarEvents.BLOOD_MOON;


public class BloodMoonUtil {

    public static boolean isBloodMoon(Level level) {
        if (EnhancedCelestials.lunarForecastWorldData(level).isPresent()) {
            LunarForecast lunarForecast = EnhancedCelestials.lunarForecastWorldData(level).get();
            LunarEvent currentEvent = lunarForecast.currentLunarEvent();
            Registry<LunarEvent> bloodMoonRegistry = level.registryAccess()
                    .registry(LUNAR_EVENT_KEY).orElseThrow();
            LunarEvent bloodMoon = bloodMoonRegistry.get(BLOOD_MOON);

            return currentEvent == bloodMoon;
        } else  {
            return false;
        }
    }
}
