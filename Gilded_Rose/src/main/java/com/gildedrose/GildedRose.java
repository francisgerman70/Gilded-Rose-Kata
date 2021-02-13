package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            handle_Quality(item);

            if_Expired(item);
        }
    }

    private void handle_Quality(Item item) {
        if (!item.name.equals("Aged Brie") && !item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            Decrease_quality_if_item_has_quality(item);
        } else {
            increaseQualityandbackstagepasses(item);
        }
    }

    private void Decrease_quality(Item item) {
        if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
            item.quality = item.quality - 1;
        }
    }

    private void increaseQualityandbackstagepasses(Item item) {
        if (item.quality < 50) {
            item.quality = item.quality + 1;

            Backstage_Update(item);
        }
    }

    private void Backstage_Update(Item item) {
        if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            increase_quality_if_far_from_expiring(item);

            increase_quality_if_close_to_expiry(item);
        }
    }

    private void increase_quality_if_close_to_expiry(Item item) {
        if (item.sellIn < 6) {
            increase_Quality(item);
        }
    }

    private void increase_quality_if_far_from_expiring(Item item) {
        if (item.sellIn < 11) {
            increase_Quality(item);
        }
    }

    private void increase_Quality(Item item) {
        if (item.quality < 50) {
            item.quality = item.quality + 1;
        }
    }

    private void if_Expired(Item item) {
        if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
            item.sellIn = item.sellIn - 1;
        }

        if (item.sellIn < 0) {
            expired(item);
        }
    }

    private void expired(Item item) {
        if (!item.name.equals("Aged Brie")) {
            if (!item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                Decrease_quality_if_item_has_quality(item);
            } else {
                item.quality = 0;
            }
        } else {
            increase_Quality(item);
        }
    }

    private void Decrease_quality_if_item_has_quality(Item item) {
        if (item.quality > 0) {
            Decrease_quality(item);
        }
    }
}