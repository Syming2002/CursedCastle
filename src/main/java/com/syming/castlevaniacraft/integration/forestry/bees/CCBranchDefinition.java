package com.syming.castlevaniacraft.integration.forestry.bees;

import forestry.api.apiculture.BeeManager;
import forestry.api.apiculture.EnumBeeChromosome;
import forestry.api.genetics.AlleleManager;
import forestry.api.genetics.IAllele;
import forestry.api.genetics.IClassification;
import forestry.apiculture.genetics.alleles.AlleleEffects;
import forestry.core.genetics.IBranchDefinition;
import forestry.core.genetics.alleles.AlleleHelper;
import forestry.core.genetics.alleles.EnumAllele;

import java.util.Arrays;
import java.util.Locale;
import java.util.function.Consumer;

import static forestry.api.apiculture.EnumBeeChromosome.*;

public enum CCBranchDefinition implements IBranchDefinition {
    UNDEAD("Inmortui", alleles -> {
        AlleleHelper.getInstance().set(alleles, TEMPERATURE_TOLERANCE, EnumAllele.Tolerance.BOTH_3);
        AlleleHelper.getInstance().set(alleles, FERTILITY, EnumAllele.Fertility.NORMAL);
    }),

    SHADE("Umbra", alleles -> {
       AlleleHelper.getInstance().set(alleles, TEMPERATURE_TOLERANCE, EnumAllele.Tolerance.BOTH_5);
       AlleleHelper.getInstance().set(alleles, FERTILITY, EnumAllele.Fertility.NORMAL);
    }),

    VAMPIRE("Vampirum",
        alleles -> {
            AlleleHelper.getInstance().set(alleles, TEMPERATURE_TOLERANCE, EnumAllele.Tolerance.UP_5);
            AlleleHelper.getInstance().set(alleles, FERTILITY, EnumAllele.Fertility.LOW);
            AlleleHelper.getInstance().set(alleles, TOLERATES_RAIN, true);
            AlleleHelper.getInstance().set(alleles, EFFECT, AlleleEffects.effectAggressive);
        });

    private static IAllele[] defaultTemplate;
    private final IClassification branch;
    private final Consumer<IAllele[]> branchProperties;

    CCBranchDefinition(String scientific, Consumer<IAllele[]> branchProperties) {
        //noinspection ConstantConditions
        this.branch = BeeManager.beeFactory.createBranch(this.name().toLowerCase(Locale.ENGLISH), scientific);
        IClassification parent = AlleleManager.alleleRegistry.getClassification("family.apidae");
        parent.addMemberGroup(this.branch);
        this.branchProperties = branchProperties;
    }

    private void setBranchProperties(IAllele[] template) {
        this.branchProperties.accept(template);
    }

    @Override
    public final IAllele[] getTemplate() {
        IAllele[] template = getDefaultTemplate();
        setBranchProperties(template);
        return template;
    }

    @Override
    public final IClassification getBranch() {
        return branch;
    }

    private static IAllele[] getDefaultTemplate() {
        if (defaultTemplate == null) {
            defaultTemplate = new IAllele[EnumBeeChromosome.values().length];

            AlleleHelper.getInstance().set(defaultTemplate, SPEED, EnumAllele.Speed.SLOWEST);
            AlleleHelper.getInstance().set(defaultTemplate, LIFESPAN, EnumAllele.Lifespan.SHORTER);
            AlleleHelper.getInstance().set(defaultTemplate, FERTILITY, EnumAllele.Fertility.NORMAL);
            AlleleHelper.getInstance().set(defaultTemplate, TEMPERATURE_TOLERANCE, EnumAllele.Tolerance.NONE);
            AlleleHelper.getInstance().set(defaultTemplate, NEVER_SLEEPS, false);
            AlleleHelper.getInstance().set(defaultTemplate, HUMIDITY_TOLERANCE, EnumAllele.Tolerance.NONE);
            AlleleHelper.getInstance().set(defaultTemplate, TOLERATES_RAIN, false);
            AlleleHelper.getInstance().set(defaultTemplate, CAVE_DWELLING, false);
            AlleleHelper.getInstance().set(defaultTemplate, FLOWER_PROVIDER, EnumAllele.Flowers.VANILLA);
            AlleleHelper.getInstance().set(defaultTemplate, FLOWERING, EnumAllele.Flowering.SLOWEST);
            AlleleHelper.getInstance().set(defaultTemplate, TERRITORY, EnumAllele.Territory.AVERAGE);
            AlleleHelper.getInstance().set(defaultTemplate, EFFECT, AlleleEffects.effectNone);
        }
        return Arrays.copyOf(defaultTemplate, defaultTemplate.length);
    }
}
