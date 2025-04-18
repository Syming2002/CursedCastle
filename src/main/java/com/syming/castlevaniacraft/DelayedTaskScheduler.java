package com.syming.castlevaniacraft;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DelayedTaskScheduler {
    private static final List<ScheduledTask> tasks = new ArrayList<>();

    public static void schedule(Runnable task, int delay) {
        tasks.add(new ScheduledTask(task, delay));
    }

    @SubscribeEvent
    public void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        Iterator<ScheduledTask> iter = tasks.iterator();
        while (iter.hasNext()) {
            ScheduledTask task = iter.next();
            task.ticksLeft--;
            if (task.ticksLeft <= 0) {
                task.task.run();
                iter.remove();
            }
        }
    }

    private static class ScheduledTask {
        Runnable task;
        int ticksLeft;

        ScheduledTask(Runnable task, int delay) {
            this.task = task;
            this.ticksLeft = delay;
        }
    }

}
