package com.gildedrose;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {
    private void nDaysPassed(GildedRose app, int n) {
        for (int i = 0; i < n; i++) {
            app.updateQuality();
        }
    }

    @Test
    void foo() {
        Item[] items = new Item[]{new Item("foo", 0, 0)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
        System.out.println(app.items[0].name);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 8, 15, 23, 89, 104, 26, 17, 191, 288})
    void legendaryQualityCheckBetter(int n) {
        Item[] items = new Item[]{new Item("Sulfuras, Hand of Ragnaros", 5, 80)};
        GildedRose app = new GildedRose(items);
        nDaysPassed(app, n);
        assertEquals(80, items[0].quality);

    }


    // Test 2
    @Test
    void Aged_Brie_one_day() {
        Item[] items = new Item[]{new Item("Aged Brie", 2, 0)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(1, app.items[0].quality);

    }

    // Test 1
    @ParameterizedTest
    @ValueSource(ints = {12})
    void Ordinary_Item_lose_quality_two_test(int n) {

        Item[] items = new Item[]{new Item("+5 Dexterity Vest", 10, 20)};
        GildedRose app = new GildedRose(items);

        nDaysPassed(app, n);

        assertEquals(6, items[0].quality);
        System.out.println(app.items[0].quality);
    }

    // Test 4 & 2
    @ParameterizedTest
    @ValueSource(ints = {8, 15, 23, 89, 104, 26, 17, 191, 288, 20, 45, 40, 48, 50, 79, 27, 38, 57, 80, 91})
    void updateQuality_with_AgedBrie_Multiple_days_and_doesnt_exceed_fifty(int n) {

        int result = 0;


        Item[] items = new Item[]{new Item("Aged Brie", 2, 0)};
        GildedRose app = new GildedRose(items);


        nDaysPassed(app, n);
        int sell = app.items[0].sellIn;

        for (int x = 1; x < n; x++) {
            if (result < 50) {

                if (sell < 0) {
                    result += 2;
                } else {
                    result += 1;
                }
            } else {

            }


        }

        assertEquals(result, items[0].quality);
        System.out.println(app.items[0].quality);
    }

    // Test 3 & 1
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 4, 6, 8, 20, 22, 25,28, 50, 30, 35, 38, 40, 42, 44, 46, 49, 60, 80})
    void Ordinary_Item_lose_quality_test_and_not_go_below_zero(int n) {

        Item[] items = new Item[]{new Item("+5 Dexterity Vest", 10, 20)};
        GildedRose app = new GildedRose(items);
        int result = app.items[0].quality - 1;


        nDaysPassed(app, n);
        int sell = app.items[0].sellIn;

        for (int x = 1; x < n; x++) {
            if (result > 0) {
                if (sell < 0) {
                    result -= 2;
                } else {
                    result -= 1;
                }

            } else if (result < 0) {
                result = 0;
            }
        }

        // Then
        assertEquals(result, items[0].quality);
        System.out.println(app.items[0].quality);
    }

    // Test 5
    @ParameterizedTest
    @ValueSource(ints = {12})
    void Backstage_passes_update_after_twelve_days(int n) {

        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 13, 29)};
        GildedRose app = new GildedRose(items);

        nDaysPassed(app, n);


        assertEquals(50, items[0].quality);
        System.out.println(app.items[0].quality);

    }

    //Test 6
    @ParameterizedTest
    @ValueSource(ints = {14})
    void Backstage_passes_update_after_fourteen_days(int n) {

        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 13, 29)};
        GildedRose app = new GildedRose(items);

        nDaysPassed(app, n);

        // Then
        assertEquals(0, items[0].quality);
        System.out.println(app.items[0].quality);

    }

}
