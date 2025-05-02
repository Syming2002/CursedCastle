package com.syming.castlevaniacraft.integration.forestry.bees;

import com.syming.castlevaniacraft.block.CCBlock;
import com.syming.castlevaniacraft.integration.forestry.genetics.CCBeesFlowers;
import com.syming.castlevaniacraft.item.CCItem;
import com.syming.castlevaniacraft.integration.forestry.items.CCCombType;
import com.syming.castlevaniacraft.integration.forestry.items.CCDropType;
import com.syming.castlevaniacraft.util.CCReferences;
import com.syming.castlevaniacraft.util.handler.RegistryHandler;
import forestry.api.apiculture.*;
import forestry.api.core.EnumHumidity;
import forestry.api.core.EnumTemperature;
import forestry.api.genetics.IAllele;
import forestry.apiculture.ModuleApiculture;
import forestry.apiculture.genetics.Bee;
import forestry.apiculture.genetics.BeeDefinition;
import forestry.apiculture.genetics.IBeeDefinition;
import forestry.apiculture.genetics.alleles.AlleleEffects;
import forestry.apiculture.items.EnumHoneyComb;
import forestry.core.genetics.alleles.AlleleHelper;
import forestry.core.genetics.alleles.EnumAllele;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import org.apache.commons.lang3.text.WordUtils;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Arrays;
import java.util.Locale;
import java.util.function.Consumer;
import java.util.function.Supplier;


public enum CCBeeDefinition implements IBeeDefinition {
    HOLY(CCBranchDefinition.HOLY, "Benedicta", false, new Color(0x8FC7DF), new Color(0xE6E6E6),
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(EnumHoneyComb.HONEY), 0.30F);
                beeSpecies.addProduct(getCCComb(CCCombType.HOLY), 0.10F);
                beeSpecies.addSpecialty(new ItemStack(CCItem.holyWater), 0.01F);
                beeSpecies.addSpecialty(new ItemStack(CCItem.holyBible), 0.001F);
                beeSpecies.setTemperature(EnumTemperature.NORMAL);
                beeSpecies.setHumidity(EnumHumidity.NORMAL);
                beeSpecies.setNocturnal();
                beeSpecies.setHasEffect();
            },
            template -> {
                AlleleHelper.getInstance().set(template, EnumBeeChromosome.FERTILITY, EnumAllele.Fertility.NORMAL);
                AlleleHelper.getInstance().set(template, EnumBeeChromosome.TEMPERATURE_TOLERANCE, EnumAllele.Tolerance.BOTH_2);
                AlleleHelper.getInstance().set(template, EnumBeeChromosome.HUMIDITY_TOLERANCE, EnumAllele.Tolerance.BOTH_2);
                AlleleHelper.getInstance().set(template, EnumBeeChromosome.TOLERATES_RAIN, false);
                AlleleHelper.getInstance().set(template, EnumBeeChromosome.FLOWER_PROVIDER, CCBeesFlowers.HOLY);
            },
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(BeeDefinition.MODEST, BeeDefinition.IMPERIAL, 5);
                mutation.requireResource(CCBlock.HOLY_WATER_FLUID.getDefaultState());
            }),

    ZOMBIE(CCBranchDefinition.UNDEAD, "Zombie", false, new Color(0x00AFAF), new Color(0x799C65),
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(EnumHoneyComb.HONEY), 0.30F);
                beeSpecies.addProduct(getCCComb(CCCombType.ROTTEN), 0.15F);
                beeSpecies.addSpecialty(new ItemStack(Items.ROTTEN_FLESH), 0.10F);
                beeSpecies.addSpecialty(new ItemStack(CCItem.bulletSoul, 1, 0), 0.0055F);
                beeSpecies.setTemperature(EnumTemperature.NORMAL);
                beeSpecies.setHumidity(EnumHumidity.NORMAL);
                beeSpecies.setNocturnal();
            },
            template -> {
                AlleleHelper.getInstance().set(template, EnumBeeChromosome.FERTILITY, EnumAllele.Fertility.NORMAL);
                AlleleHelper.getInstance().set(template, EnumBeeChromosome.TEMPERATURE_TOLERANCE, EnumAllele.Tolerance.DOWN_5);
                AlleleHelper.getInstance().set(template, EnumBeeChromosome.HUMIDITY_TOLERANCE, EnumAllele.Tolerance.BOTH_1);
                AlleleHelper.getInstance().set(template, EnumBeeChromosome.TOLERATES_RAIN, true);
            },
            dis -> {

            }),

    SKELETON(CCBranchDefinition.UNDEAD, "Sceletus", false, new Color(0xC1C1C1), new Color(0x494949),
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(EnumHoneyComb.HONEY), 0.30F);
                beeSpecies.addProduct(getCCComb(CCCombType.BONE), 0.15F);
                beeSpecies.addSpecialty(new ItemStack(Items.BONE), 0.10F);
                beeSpecies.addSpecialty(new ItemStack(Items.SKULL, 1, 0), 0.05F);
                beeSpecies.addSpecialty(new ItemStack(CCItem.bulletSoul, 1, 1), 0.0028F);
                beeSpecies.setTemperature(EnumTemperature.NORMAL);
                beeSpecies.setHumidity(EnumHumidity.NORMAL);
                beeSpecies.setNocturnal();
            },
            template -> {
                AlleleHelper.getInstance().set(template, EnumBeeChromosome.FERTILITY, EnumAllele.Fertility.NORMAL);
                AlleleHelper.getInstance().set(template, EnumBeeChromosome.TEMPERATURE_TOLERANCE, EnumAllele.Tolerance.DOWN_5);
                AlleleHelper.getInstance().set(template, EnumBeeChromosome.HUMIDITY_TOLERANCE, EnumAllele.Tolerance.BOTH_1);
                AlleleHelper.getInstance().set(template, EnumBeeChromosome.TOLERATES_RAIN, true);
                AlleleHelper.getInstance().set(template, EnumBeeChromosome.FLOWER_PROVIDER, CCBeesFlowers.BONE);
            },
            dis -> {

            }),

    FROZEN_SHADE(CCBranchDefinition.SHADE, "Gelida Umbra", false, new Color(0x1298FF), new Color(0x044FB0),
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(EnumHoneyComb.FROZEN), 0.30F);
                beeSpecies.addProduct(getCCComb(CCCombType.FROZEN), 0.15F);
                beeSpecies.setTemperature(EnumTemperature.ICY);
                beeSpecies.setHumidity(EnumHumidity.NORMAL);
                beeSpecies.setNocturnal();
            },
            template -> {
                AlleleHelper.getInstance().set(template, EnumBeeChromosome.FERTILITY, EnumAllele.Fertility.NORMAL);
                AlleleHelper.getInstance().set(template, EnumBeeChromosome.TEMPERATURE_TOLERANCE, EnumAllele.Tolerance.DOWN_5);
                AlleleHelper.getInstance().set(template, EnumBeeChromosome.HUMIDITY_TOLERANCE, EnumAllele.Tolerance.BOTH_1);
                AlleleHelper.getInstance().set(template, EnumBeeChromosome.TOLERATES_RAIN, true);
                AlleleHelper.getInstance().set(template, EnumBeeChromosome.EFFECT, AlleleEffects.effectGlacial);
                AlleleHelper.getInstance().set(template, EnumBeeChromosome.FLOWER_PROVIDER, CCBeesFlowers.ICE);
            },
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(BeeDefinition.GLACIAL, BeeDefinition.DEMONIC, 15);
                mutation.requireResource(Blocks.PACKED_ICE.getDefaultState());
            }),

    HEAT_SHADE(CCBranchDefinition.SHADE, "Calor Umbra", false, new Color(0xF44211), new Color(0xC41105),
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(EnumHoneyComb.SIMMERING), 0.30F);
                beeSpecies.addProduct(getCCComb(CCCombType.WARMED), 0.15F);
                beeSpecies.setTemperature(EnumTemperature.HOT);
                beeSpecies.setHumidity(EnumHumidity.ARID);
                beeSpecies.setNocturnal();
            },
            template -> {
                AlleleHelper.getInstance().set(template, EnumBeeChromosome.FERTILITY, EnumAllele.Fertility.NORMAL);
                AlleleHelper.getInstance().set(template, EnumBeeChromosome.TEMPERATURE_TOLERANCE, EnumAllele.Tolerance.UP_5);
                AlleleHelper.getInstance().set(template, EnumBeeChromosome.HUMIDITY_TOLERANCE, EnumAllele.Tolerance.DOWN_5);
                AlleleHelper.getInstance().set(template, EnumBeeChromosome.FLOWER_PROVIDER, CCBeesFlowers.MAGMA);
                AlleleHelper.getInstance().set(template, EnumBeeChromosome.TOLERATES_RAIN, false);
                AlleleHelper.getInstance().set(template, EnumBeeChromosome.EFFECT, AlleleEffects.effectIgnition);
            },
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(BeeDefinition.SINISTER, BeeDefinition.DEMONIC, 15);
                mutation.requireResource(Blocks.MAGMA.getDefaultState());
            }),

    VAMPIRE(CCBranchDefinition.VAMPIRE, "Vampirum", false, new Color(0x0C0B0F), new Color(0x8E1D26),
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(EnumHoneyComb.HONEY), 0.30F);
                beeSpecies.addProduct(getCCComb(CCCombType.BLOOD), 0.15F);
                beeSpecies.setTemperature(EnumTemperature.HELLISH);
                beeSpecies.setHumidity(EnumHumidity.ARID);
                beeSpecies.setNocturnal();
            },
            template -> {
                AlleleHelper.getInstance().set(template, EnumBeeChromosome.FERTILITY, EnumAllele.Fertility.LOW);
                AlleleHelper.getInstance().set(template, EnumBeeChromosome.TEMPERATURE_TOLERANCE, EnumAllele.Tolerance.UP_5);
                AlleleHelper.getInstance().set(template, EnumBeeChromosome.HUMIDITY_TOLERANCE, EnumAllele.Tolerance.UP_5);
                AlleleHelper.getInstance().set(template, EnumBeeChromosome.TOLERATES_RAIN, true);
                AlleleHelper.getInstance().set(template, EnumBeeChromosome.FLOWER_PROVIDER, CCBeesFlowers.BLOOD);
            },
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(BeeDefinition.DEMONIC, BeeDefinition.SINISTER, 10);
                mutation.requireResource(CCBlock.BLOOD_FLUID.getDefaultState());
            }),

    DRACULA(CCBranchDefinition.VAMPIRE, "Draculum", false, new Color(0x0C0B0F), new Color(0x8E1D26),
            beeSpecies -> {
                beeSpecies.addProduct(getForestryComb(EnumHoneyComb.HONEY), 0.45F);
                beeSpecies.addProduct(getCCComb(CCCombType.BLOOD), 0.30f);
                beeSpecies.setTemperature(EnumTemperature.HELLISH);
                beeSpecies.setHumidity(EnumHumidity.ARID);
                beeSpecies.setNocturnal();
                beeSpecies.setHasEffect();
            },
            template -> {
                AlleleHelper.getInstance().set(template, EnumBeeChromosome.FERTILITY, EnumAllele.Fertility.LOW);
                AlleleHelper.getInstance().set(template, EnumBeeChromosome.TEMPERATURE_TOLERANCE, EnumAllele.Tolerance.UP_5);
                AlleleHelper.getInstance().set(template, EnumBeeChromosome.HUMIDITY_TOLERANCE, EnumAllele.Tolerance.UP_5);
                AlleleHelper.getInstance().set(template, EnumBeeChromosome.TOLERATES_RAIN, true);
                AlleleHelper.getInstance().set(template, EnumBeeChromosome.FLOWER_PROVIDER, CCBeesFlowers.BLOOD);
            },
            dis -> {
                IBeeMutationBuilder mutation = dis.registerMutation(BeeDefinition.IMPERIAL, CCBeeDefinition.VAMPIRE, 5);
                mutation.requireResource(CCBlock.BLOOD_FLUID.getDefaultState());
            });


    private final CCBranchDefinition branch;
    private CCAlleleBeeSpecies species;
    private final Consumer<CCAlleleBeeSpecies> speciesProperties;
    private final Consumer<IAllele[]> alleles;
    private final Consumer<CCBeeDefinition> mutations;
    private IAllele[] template;
    private IBeeGenome genome;
    private final Supplier<Boolean> generationCondition;

    CCBeeDefinition(CCBranchDefinition branch,
                    String binomial,
                    boolean dominant,
                    Color primary,
                    Color secondary,
                    Consumer<CCAlleleBeeSpecies> speciesProperties,
                    Consumer<IAllele[]> alleles,
                    Consumer<CCBeeDefinition> mutations) {
        this(branch, binomial, dominant, primary, secondary, speciesProperties, alleles, mutations, () -> true);
    }

    CCBeeDefinition(CCBranchDefinition branch,
                    String binomial,
                    boolean dominant,
                    Color primary,
                    Color secondary,
                    Consumer<CCAlleleBeeSpecies> speciesProperties,
                    Consumer<IAllele[]> alleles,
                    Consumer<CCBeeDefinition> mutations,
                    Supplier<Boolean> generationCondition) {
        this.alleles = alleles;
        this.mutations = mutations;
        this.speciesProperties = speciesProperties;
        String lowercaseName = this.toString().toLowerCase(Locale.ENGLISH);
        String species = WordUtils.capitalize(lowercaseName);

        String uid = "castlevaniacraft.bee.species" + species;
        String description = "for.bees.description." + lowercaseName;
        String name = "for.bees.species." + lowercaseName;

        this.branch = branch;
        this.species = new CCAlleleBeeSpecies(CCReferences.MODID, uid, name, CCReferences.NAME, description, dominant, branch.getBranch(), binomial, primary.getRGB(), secondary.getRGB());
        this.generationCondition = generationCondition;
    }

    public static void doInit() {

        for (CCBeeDefinition bee : values()) {
            if (bee.isNeedRegister()) {
                bee.init();
            }
        }
        for (CCBeeDefinition bee : values()) {
            if (bee.isNeedRegister()) {
                bee.registerMutations();
            }
        }
    }

    private static ItemStack getForestryComb(EnumHoneyComb type) {
        return ModuleApiculture.getItems().beeComb.get(type, 1);
    }

    public static ItemStack getCCComb(CCCombType type) {
        return new ItemStack(RegistryHandler.COMBS, 1, type.ordinal());
    }

    public static ItemStack getCCDrop(CCDropType type) {
        return new ItemStack(RegistryHandler.DROPS, 1, type.ordinal());
    }

    private void setSpeciesProperties(CCAlleleBeeSpecies beeSpecies) {
        this.speciesProperties.accept(beeSpecies);
    }

    private void setAlleles(IAllele[] template) {
        this.alleles.accept(template);
    }

    private void registerMutations() {
        if (generationCondition.get()) {
            this.mutations.accept(this);
        }
    }

    private boolean isNeedRegister() {
        return branch != null;
    }

    private void init() {
        if (generationCondition.get()) {
            setSpeciesProperties(species);

            template = branch.getTemplate();
            AlleleHelper.getInstance().set(template, EnumBeeChromosome.SPECIES, species);
            setAlleles(template);

            //noinspection ConstantConditions
            genome = BeeManager.beeRoot.templateAsGenome(template);
            BeeManager.beeRoot.registerTemplate(template);

        }
    }

    private IBeeMutationBuilder registerMutation(IBeeDefinition parent1, IBeeDefinition parent2, int chance) {
        return registerMutation(parent1.getGenome().getPrimary(), parent2.getGenome().getPrimary(), chance);
    }

    private IBeeMutationBuilder registerMutation(IAlleleBeeSpecies parent1, IBeeDefinition parent2, int chance) {
        return registerMutation(parent1, parent2.getGenome().getPrimary(), chance);
    }

    private IBeeMutationBuilder registerMutation(IBeeDefinition parent1, IAlleleBeeSpecies parent2, int chance) {
        return registerMutation(parent1.getGenome().getPrimary(), parent2, chance);
    }

    private IBeeMutationBuilder registerMutation(IAlleleBeeSpecies parent1, IAlleleBeeSpecies parent2, int chance) {
        //noinspection ConstantConditions
        return BeeManager.beeMutationFactory.createMutation(parent1, parent2, getTemplate(), chance);
    }

    @Override
    public final IAllele @NotNull [] getTemplate() {
        return Arrays.copyOf(template, template.length);
    }

    @NotNull
    @Override
    public final IBeeGenome getGenome() {
        return genome;
    }

    @NotNull
    @Override
    public final IBee getIndividual() {
        return new Bee(genome);
    }

    @NotNull
    @Override
    public final ItemStack getMemberStack(@NotNull EnumBeeType beeType) {
        IBee bee = getIndividual();
        //noinspection ConstantConditions
        return BeeManager.beeRoot.getMemberStack(bee, beeType);
    }
}
