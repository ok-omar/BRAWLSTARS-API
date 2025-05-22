package model.classes;
import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;
import java.util.List;

public class Brawler {
    private int id;
    private String name;
    private List<StarPower> starPowers;
    private List<Gadget> gadgets;
    private transient LocalDateTime updateDate; // Datetime when last updated

    @SerializedName("class")
    private BrawlerClass brawlerClass; // maps to "class" in JSON

    private Rarity rarity; // maps to "rarity" in JSON


    public Brawler(int id, String name, List<StarPower> starPowers, List<Gadget> gadgets) {
        this.id = id;
        this.name = name;
        this.starPowers = starPowers;
        this.gadgets = gadgets;
        this.updateDate = LocalDateTime.now();
    }

    public Brawler(int id, String name, List<StarPower> starPowers, List<Gadget> gadgets, BrawlerClass brawlerClass, Rarity rarity, LocalDateTime updateDate) {
        this.id = id;
        this.name = name;
        this.starPowers = starPowers;
        this.gadgets = gadgets;
        this.brawlerClass = brawlerClass;
        this.rarity = rarity;
        this.updateDate = updateDate;
    }

    public Brawler() {}

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Brawler)) return false;
        Brawler brawler = (Brawler) o;
        return this.id == brawler.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<StarPower> getStarPowers() {
        return starPowers;
    }

    public void setStarPowers(List<StarPower> starPowers) {
        this.starPowers = starPowers;
    }

    public List<Gadget> getGadgets() {
        return gadgets;
    }

    public void setGadgets(List<Gadget> gadgets) {
        this.gadgets = gadgets;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public BrawlerClass getBrawlerClass() {
        return brawlerClass;
    }

    public void setBrawlerClass(BrawlerClass brawlerClass) {
        this.brawlerClass = brawlerClass;
    }
    public Rarity getRarity() {
        return rarity;
    }
    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }
}
