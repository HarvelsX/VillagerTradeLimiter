package com.pretzel.dev.villagertradelimiter.listeners;

import com.pretzel.dev.villagertradelimiter.VillagerTradeLimiter;
import com.pretzel.dev.villagertradelimiter.lib.Util;
import com.pretzel.dev.villagertradelimiter.nms.*;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentWrapper;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.List;

public class PlayerListener implements Listener {
    private static final Material[] MATERIALS = new Material[] { Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS, Material.BELL, Material.CHAINMAIL_HELMET, Material.CHAINMAIL_CHESTPLATE, Material.CHAINMAIL_LEGGINGS, Material.CHAINMAIL_BOOTS, Material.SHIELD, Material.DIAMOND_HELMET, Material.DIAMOND_CHESTPLATE, Material.DIAMOND_LEGGINGS, Material.DIAMOND_BOOTS, Material.FILLED_MAP, Material.FISHING_ROD, Material.LEATHER_HELMET, Material.LEATHER_CHESTPLATE, Material.LEATHER_LEGGINGS, Material.LEATHER_BOOTS, Material.LEATHER_HORSE_ARMOR, Material.SADDLE, Material.ENCHANTED_BOOK, Material.STONE_AXE, Material.STONE_SHOVEL, Material.STONE_PICKAXE, Material.STONE_HOE, Material.IRON_AXE, Material.IRON_SHOVEL, Material.IRON_PICKAXE, Material.DIAMOND_AXE, Material.DIAMOND_SHOVEL, Material.DIAMOND_PICKAXE, Material.DIAMOND_HOE, Material.DIAMOND_SWORD};
    private static final Material[] MAX_USES_12 = new Material[]{Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS, Material.IRON_INGOT, Material.BELL, Material.CHAINMAIL_HELMET, Material.CHAINMAIL_CHESTPLATE, Material.CHAINMAIL_LEGGINGS, Material.CHAINMAIL_BOOTS, Material.LAVA_BUCKET, Material.DIAMOND, Material.SHIELD, Material.RABBIT_STEW, Material.DRIED_KELP_BLOCK, Material.SWEET_BERRIES, Material.MAP, Material.FILLED_MAP, Material.COMPASS, Material.ITEM_FRAME, Material.GLOBE_BANNER_PATTERN, Material.WHITE_BANNER, Material.LIGHT_GRAY_BANNER, Material.GRAY_BANNER, Material.BLACK_BANNER, Material.BROWN_BANNER, Material.ORANGE_BANNER, Material.YELLOW_BANNER, Material.LIME_BANNER, Material.GREEN_BANNER, Material.CYAN_BANNER, Material.BLUE_BANNER, Material.LIGHT_BLUE_BANNER, Material.PURPLE_BANNER, Material.MAGENTA_BANNER, Material.PINK_BANNER, Material.RED_BANNER, Material.WHITE_BED, Material.LIGHT_GRAY_BED, Material.GRAY_BED, Material.BLACK_BED, Material.BROWN_BED, Material.ORANGE_BED, Material.YELLOW_BED, Material.LIME_BED, Material.GREEN_BED, Material.CYAN_BED, Material.BLUE_BED, Material.LIGHT_BLUE_BED, Material.PURPLE_BED, Material.MAGENTA_BED, Material.PINK_BED, Material.RED_BED, Material.REDSTONE, Material.GOLD_INGOT, Material.LAPIS_LAZULI, Material.RABBIT_FOOT, Material.GLOWSTONE, Material.SCUTE, Material.GLASS_BOTTLE, Material.ENDER_PEARL, Material.NETHER_WART, Material.EXPERIENCE_BOTTLE, Material.PUMPKIN, Material.PUMPKIN_PIE, Material.MELON, Material.COOKIE, Material.CAKE, Material.SUSPICIOUS_STEW, Material.GOLDEN_CARROT, Material.GLISTERING_MELON_SLICE, Material.CAMPFIRE, Material.TROPICAL_FISH, Material.PUFFERFISH, Material.BIRCH_BOAT, Material.ACACIA_BOAT, Material.OAK_BOAT, Material.DARK_OAK_BOAT, Material.SPRUCE_BOAT, Material.JUNGLE_BOAT, Material.ARROW, Material.FLINT, Material.STRING, Material.TRIPWIRE_HOOK, Material.TIPPED_ARROW, Material.LEATHER_HELMET, Material.LEATHER_CHESTPLATE, Material.LEATHER_LEGGINGS, Material.LEATHER_BOOTS, Material.LEATHER, Material.RABBIT_HIDE, Material.LEATHER_HORSE_ARMOR, Material.SADDLE, Material.BOOK, Material.ENCHANTED_BOOK, Material.BOOKSHELF, Material.INK_SAC, Material.GLASS, Material.WRITABLE_BOOK, Material.CLOCK, Material.NAME_TAG, Material.QUARTZ, Material.QUARTZ_PILLAR, Material.QUARTZ_BLOCK, Material.TERRACOTTA, Material.WHITE_TERRACOTTA, Material.LIGHT_GRAY_TERRACOTTA, Material.GRAY_TERRACOTTA, Material.BLACK_TERRACOTTA, Material.BROWN_TERRACOTTA, Material.ORANGE_TERRACOTTA, Material.YELLOW_TERRACOTTA, Material.LIME_TERRACOTTA, Material.GREEN_TERRACOTTA, Material.CYAN_TERRACOTTA, Material.BLUE_TERRACOTTA, Material.LIGHT_BLUE_TERRACOTTA, Material.PURPLE_TERRACOTTA, Material.MAGENTA_TERRACOTTA, Material.PINK_TERRACOTTA, Material.RED_TERRACOTTA, Material.WHITE_GLAZED_TERRACOTTA, Material.LIGHT_GRAY_GLAZED_TERRACOTTA, Material.GRAY_GLAZED_TERRACOTTA, Material.BLACK_GLAZED_TERRACOTTA, Material.BROWN_GLAZED_TERRACOTTA, Material.ORANGE_GLAZED_TERRACOTTA, Material.YELLOW_GLAZED_TERRACOTTA, Material.LIME_GLAZED_TERRACOTTA, Material.GREEN_GLAZED_TERRACOTTA, Material.CYAN_GLAZED_TERRACOTTA, Material.BLUE_GLAZED_TERRACOTTA, Material.LIGHT_BLUE_GLAZED_TERRACOTTA, Material.PURPLE_GLAZED_TERRACOTTA, Material.MAGENTA_GLAZED_TERRACOTTA, Material.PINK_GLAZED_TERRACOTTA, Material.RED_GLAZED_TERRACOTTA, Material.SHEARS, Material.PAINTING, Material.STONE_AXE, Material.STONE_SHOVEL, Material.STONE_PICKAXE, Material.STONE_HOE};
    private static final Material[] MAX_USES_3 = new Material[]{Material.DIAMOND_HELMET, Material.DIAMOND_CHESTPLATE, Material.DIAMOND_LEGGINGS, Material.DIAMOND_BOOTS, Material.DIAMOND_SWORD, Material.DIAMOND_AXE, Material.DIAMOND_SHOVEL, Material.DIAMOND_PICKAXE, Material.DIAMOND_HOE, Material.IRON_SWORD, Material.IRON_AXE, Material.IRON_SHOVEL, Material.IRON_PICKAXE, Material.FISHING_ROD, Material.BOW, Material.CROSSBOW};

    private final VillagerTradeLimiter instance;

    public PlayerListener(VillagerTradeLimiter instance) {
        this.instance = instance;
    }

    //Handles villager trading event
    @EventHandler
    public void onPlayerInteract(PlayerInteractEntityEvent event) {
        if(!(event.getRightClicked() instanceof Villager)) return;
        final Villager villager = (Villager)event.getRightClicked();
        if(Util.isNPC(villager)) return; //Skips NPCs
        if(villager.getProfession() == Villager.Profession.NONE || villager.getProfession() == Villager.Profession.NITWIT) return; //Skips non-trading villagers
        if(villager.getRecipeCount() == 0) return; //Skips non-trading villagers

        //DisableTrading feature
        if(instance.getCfg().isBoolean("DisableTrading")) {
            if(instance.getCfg().getBoolean("DisableTrading", false)) {
                event.setCancelled(true);
                return;
            }
        } else {
            List<String> disabledWorlds = instance.getCfg().getStringList("DisableTrading");
            for(String world : disabledWorlds) {
                if(event.getPlayer().getWorld().getName().equals(world)) {
                    event.setCancelled(true);
                    return;
                }
            }
        }

        final Player player = event.getPlayer();
        if(Util.isNPC(player)) return; //Skips NPCs
        this.hotv(player);
        this.maxDiscount(villager, player);
        this.maxDemand(villager);
    }

    //Hero of the Village effect limiter feature
    private void hotv(final Player player) {
        final PotionEffectType effect = PotionEffectType.HERO_OF_THE_VILLAGE;
        if(!player.hasPotionEffect(effect)) return; //Skips when player doesn't have HotV

        final int maxHeroLevel = instance.getCfg().getInt("MaxHeroLevel", 1);
        if(maxHeroLevel == 0) player.removePotionEffect(effect);
        if(maxHeroLevel <= 0) return; //Skips when disabled in config.yml

        final PotionEffect pot = player.getPotionEffect(effect);
        if(pot == null) return;
        if(pot.getAmplifier() > maxHeroLevel-1) {
            player.removePotionEffect(effect);
            player.addPotionEffect(new PotionEffect(effect, pot.getDuration(), maxHeroLevel-1));
        }
    }

    //Sets an ingredient for a trade
    private void setIngredient(ConfigurationSection item, NBTCompound recipe, String nbtKey, String itemKey) {
        if(!item.contains(itemKey) || recipe.getCompound(nbtKey).getString("id").equals("minecraft:air")) return;
        if(item.contains(itemKey+".Material")) {
            recipe.getCompound(nbtKey).setString("id", "minecraft:" + item.getString(itemKey+".Material"));
        }
        if(item.contains(itemKey+".Amount")) {
            int cost = item.getInt(itemKey+".Amount");
            cost = Math.min(cost, 64);
            cost = Math.max(cost, 1);
            recipe.getCompound(nbtKey).setInteger("Count", cost);
        }
    }

    //MaxDiscount feature - limits the lowest discounted price to a % of the base price
    private void maxDiscount(final Villager villager, final Player player) {
        int majorPositiveValue = 0, minorPositiveValue = 0, tradingValue = 0, minorNegativeValue = 0, majorNegativeValue = 0;

        NBTEntity nbtEntity = new NBTEntity(villager);
        final NBTEntity playerNBT = new NBTEntity(player);
        final String playerUUID = Util.intArrayToString(playerNBT.getIntArray("UUID"));
        if(nbtEntity.hasKey("Gossips")) {
            NBTCompoundList gossips = nbtEntity.getCompoundList("Gossips");
            for(NBTCompound gossip : gossips) {
                final String type = gossip.getString("Type");
                final String targetUUID = Util.intArrayToString(gossip.getIntArray("Target"));
                final int value = gossip.getInteger("Value");
                if(targetUUID.equals(playerUUID)) {
                    switch (type) {
                        case "trading": tradingValue = value; break;
                        case "minor_positive": minorPositiveValue = value; break;
                        case "minor_negative": minorNegativeValue = value; break;
                        case "major_positive": majorPositiveValue = value; break;
                        case "major_negative": majorNegativeValue = value; break;
                        default: break;
                    }
                }
            }
        }

        final ConfigurationSection overrides = instance.getCfg().getConfigurationSection("Overrides");
        final NBTEntity villagerNBT = new NBTEntity(villager);
        NBTCompoundList recipes = villagerNBT.getCompound("Offers").getCompoundList("Recipes");
        for(final NBTCompound recipe : recipes) {
            final int ingredientAmount = recipe.getCompound("buy").getInteger("Count");
            final float priceMultiplier = this.getPriceMultiplier(recipe);
            final int valueModifier = 5 * majorPositiveValue + minorPositiveValue + tradingValue - minorNegativeValue - 5 * majorNegativeValue;
            final float finalValue = ingredientAmount - priceMultiplier * valueModifier;

            boolean disabled = false;
            double maxDiscount = instance.getCfg().getDouble("MaxDiscount", 0.3);
            int maxUses = instance.getCfg().getInt("MaxUses", -1);
            if (overrides != null) {
                for (final String override : overrides.getKeys(false)) {
                    final ConfigurationSection item = this.getItem(recipe, override);
                    if (item != null) {
                        //Set whether trade is disabled and max discount
                        disabled = item.getBoolean("Disabled", false);
                        maxDiscount = item.getDouble("MaxDiscount", maxDiscount);
                        maxUses = item.getInt("MaxUses", maxUses);

                        //Set 1st and 2nd ingredients
                        setIngredient(item, recipe, "buy", "Item1");
                        setIngredient(item, recipe, "buyB", "Item2");
                        break;
                    }
                }
            }

            //Set max uses
            if(maxUses >= 0) {
                recipe.setInteger("maxUses", maxUses);
            } else {
                if(disabled) recipe.setInteger("maxUses", 0);
                else {
                    int uses = 16;
                    Material buyMaterial = recipe.getItemStack("buy").getType();
                    Material sellMaterial = recipe.getItemStack("sell").getType();
                    if (Arrays.asList(MAX_USES_12).contains(buyMaterial) || Arrays.asList(MAX_USES_12).contains(sellMaterial)) {
                        uses = 12;
                    } else if (Arrays.asList(MAX_USES_3).contains(buyMaterial) || Arrays.asList(MAX_USES_3).contains(sellMaterial)) {
                        uses = 3;
                    }
                    recipe.setInteger("maxUses", uses);
                }
            }

            //Set max discount
            if (maxDiscount >= 0.0 && maxDiscount <= 1.0) {
                if (finalValue < ingredientAmount * (1.0 - maxDiscount) && finalValue != ingredientAmount) {
                    recipe.setFloat("priceMultiplier", ingredientAmount * (float) maxDiscount / valueModifier);
                } else {
                    recipe.setFloat("priceMultiplier", priceMultiplier);
                }
            } else {
                recipe.setFloat("priceMultiplier", priceMultiplier);
            }
        }
    }

    //MaxDemand feature - limits demand-based price increases
    private void maxDemand(final Villager villager) {
        final NBTEntity villagerNBT = new NBTEntity(villager);
        final ConfigurationSection overrides = instance.getCfg().getConfigurationSection("Overrides");
        if (villagerNBT.hasKey("Offers")) {
            NBTCompoundList recipes = villagerNBT.getCompound("Offers").getCompoundList("Recipes");
            for (NBTCompound recipe : recipes) {
                final int demand = recipe.getInteger("demand");
                int maxDemand = instance.getCfg().getInt("MaxDemand", -1);
                if (overrides != null) {
                    for (String override : overrides.getKeys(false)) {
                        final ConfigurationSection item = this.getItem(recipe, override);
                        if(item != null) {
                            maxDemand = item.getInt("MaxDemand", maxDemand);
                            break;
                        }
                    }
                }
                if(maxDemand >= 0 && demand > maxDemand) {
                    recipe.setInteger("demand", maxDemand);
                }
            }
        }
    }

    //Returns the price multiplier for a given trade
    private float getPriceMultiplier(final NBTCompound recipe) {
        float p = 0.05f;
        final Material type = recipe.getItemStack("sell").getType();
        for(int length = MATERIALS.length, i = 0; i < length; ++i) {
            if(type == MATERIALS[i]) {
                p = 0.2f;
                break;
            }
        }
        return p;
    }

    //Returns the configured settings for a trade
    private ConfigurationSection getItem(final NBTCompound recipe, final String k) {
        final ConfigurationSection item = instance.getCfg().getConfigurationSection("Overrides."+k);
        if(item == null) return null;

        if(!k.contains("_")) {
            //Return the item if the item name is valid
            if(this.verify(recipe, Material.matchMaterial(k))) return item;
            return null;
        }

        final String[] words = k.split("_");
        try {
            //Return the enchanted book item if there's a number in the item name
            final int level = Integer.parseInt(words[words.length-1]);
            if(recipe.getItemStack("sell").getType() == Material.ENCHANTED_BOOK) {
                final EnchantmentStorageMeta meta = (EnchantmentStorageMeta) recipe.getItemStack("sell").getItemMeta();
                final Enchantment enchantment = EnchantmentWrapper.getByKey(NamespacedKey.minecraft(k.substring(0, k.lastIndexOf("_"))));
                if (meta == null || enchantment == null) return null;
                if (meta.hasStoredEnchant(enchantment) && meta.getStoredEnchantLevel(enchantment) == level) return item;
            }
        } catch(NumberFormatException e) {
            //Return the item if the item name is valid
            if(this.verify(recipe, Material.matchMaterial(k)))
                return item;
            return null;
        } catch(Exception e2) {
            //Send an error message
            Util.errorMsg(e2);
        }
        return null;
    }

    //Verifies that an item exists in the villager's trade
    private boolean verify(final NBTCompound recipe, final Material material) {
        return ((recipe.getItemStack("sell").getType() == material) || (recipe.getItemStack("buy").getType() == material));
    }
}
