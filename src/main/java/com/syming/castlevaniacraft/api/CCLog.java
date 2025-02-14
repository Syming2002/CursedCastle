package com.syming.castlevaniacraft.api;

import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public final class CCLog {

    public static Logger logger;

    private CCLog() {}

    public static void init(@NotNull Logger modLogger) {
        logger = modLogger;
    }

}
