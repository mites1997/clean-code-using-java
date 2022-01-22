package com.a.introduction.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GildedRoseRefactoredTest {

	public static final int DEFAULT_QUALITY = 3;
	public static final int DEFAULT_SELL_IN = 15;
	public static final String DEFAULT_ITEM = "DEFAULT_ITEM";

	/**
	 * Method to test the variation in quality of the item for the non expired
	 * Item.
	 * 
	 * The quality should decrease by 1 when the item is not expired
	 * and sell in should decrease by 1.
	 * 
	 */
	@Test
	public void testUnexpiredDefaultItemQualityDecreasesBy1() {
		//setup
		String defaultItem = DEFAULT_ITEM;
		int notExpiredSellIn = DEFAULT_SELL_IN;
		int defaultQuality = DEFAULT_QUALITY;
		GildedRose app = createGildedRoseWithOneItem(defaultItem, notExpiredSellIn, defaultQuality);
		//invoke
		app.updateQuality();

		//verify
		Item expected=new Item(DEFAULT_ITEM,DEFAULT_SELL_IN-1,DEFAULT_QUALITY-1);
		assertItem(expected, app.items[0]);
	}

	private void assertItem(Item expected, Item actual) {
		assertEquals(expected.name, actual.name);
		assertEquals(expected.sellIn , actual.sellIn);
		assertEquals(expected.quality , actual.quality);
	}

	private GildedRose createGildedRoseWithOneItem(String defaultItem, int notExpiredSellIn, int defaultQuality) {
		Item item = new Item(defaultItem, notExpiredSellIn, defaultQuality);
		Item[] items = new Item[] { item };
		GildedRose app = new GildedRose(items);
		return app;
	}

	/**
	 * Method to test the variation in quality of the item for the non expired
	 * Item.
	 * 
	 * The quality should decrease by 2 when the item is expired(Sell in  < 0) and sell in should decrease by 1.
	 * 
	 */
	@Test
	public void testUpdateQualityForExpiredItem() {
		Item item = new Item("DEFAULT_ITEM", -1, 3);
		Item[] items = new Item[] { item };
		GildedRose app = new GildedRose(items);
		app.updateQuality();
		assertEquals("DEFAULT_ITEM", app.items[0].name);
		assertEquals(-2, app.items[0].sellIn);
		assertEquals(1, app.items[0].quality);
	}
}