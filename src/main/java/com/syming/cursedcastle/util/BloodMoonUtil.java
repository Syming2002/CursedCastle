package com.syming.cursedcastle.util;

import corgitaco.enhancedcelestials.EnhancedCelestialsWorldData;
import corgitaco.enhancedcelestials.api.lunarevent.LunarEvent;
import corgitaco.enhancedcelestials.core.EnhancedCelestialsContext;
import corgitaco.enhancedcelestials.lunarevent.LunarForecast;
import net.minecraft.core.Registry;
import net.minecraft.world.level.Level;

import static corgitaco.enhancedcelestials.api.EnhancedCelestialsRegistry.LUNAR_EVENT_KEY;
import static corgitaco.enhancedcelestials.api.lunarevent.DefaultLunarEvents.BLOOD_MOON;

public class BloodMoonUtil {

    public static boolean isBloodMoon(Level level) {
        EnhancedCelestialsContext enhancedCelestialsContext = ((EnhancedCelestialsWorldData) level).getLunarContext();
        LunarForecast lunarForecast = enhancedCelestialsContext.getLunarForecast();
        LunarEvent currentEvent = (LunarEvent)lunarForecast.getCurrentEvent(level.getRainLevel(1.0F) < 1.0F).get();
        Registry<LunarEvent> bloodMoonRegistry = level.registryAccess()
                .registry(LUNAR_EVENT_KEY).orElseThrow();
        LunarEvent bloodMoon = bloodMoonRegistry.get(BLOOD_MOON);

        return currentEvent == bloodMoon;
    }

}
