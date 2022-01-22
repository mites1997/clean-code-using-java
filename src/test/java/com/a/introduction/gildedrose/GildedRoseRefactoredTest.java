package com.a.introduction.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GildedRoseRefactoredTest {

	public static final int DEFAULT_QUALITY = 3;
	public static final int NOT_EXPIRED_SELL_IN = 15;
	public static final String DEFAULT_ITEM = "DEFAULT_ITEM";
	public static final int EXPIRED_SELL_IN = -1;
	public static final String AGED_BRIE = "Aged Brie";
	public static final int MAXIMUM_QUALITY = 50;


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
		int notExpiredSellIn = NOT_EXPIRED_SELL_IN;
		int defaultQuality = DEFAULT_QUALITY;
		GildedRose app = createGildedRoseWithOneItem(defaultItem, notExpiredSellIn, defaultQuality);
		//invoke
		app.updateQuality();
		//verify
		Item expected=new Item(DEFAULT_ITEM,NOT_EXPIRED_SELL_IN-1,DEFAULT_QUALITY-1);
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
	public void testExpiredDefaultItemQualityDecreasesBy2() {
		//setup
		String defaultItem = DEFAULT_ITEM;
		int expiredSellIn = EXPIRED_SELL_IN;
		int defaultQuality = DEFAULT_QUALITY;
		GildedRose app = createGildedRoseWithOneItem(defaultItem, expiredSellIn, defaultQuality);
		//invoke
		app.updateQuality();
		//verify
		Item expected=new Item(DEFAULT_ITEM,EXPIRED_SELL_IN-1,DEFAULT_QUALITY-2);
		assertItem(expected, app.items[0]);
	}


	@Test
	public void testUnexpiredAgedBrieQualityIncreasesBy1() {
		GildedRose app = createGildedRoseWithOneItem(AGED_BRIE, NOT_EXPIRED_SELL_IN, DEFAULT_QUALITY);
		//invoke
		app.updateQuality();
		//verify
		Item expected=new Item(AGED_BRIE,NOT_EXPIRED_SELL_IN-1,DEFAULT_QUALITY+1);
		assertItem(expected, app.items[0]);
	}
	@Test
	public void testExpiredAgedBrieQualityDecreasesBy2() {
		GildedRose app = createGildedRoseWithOneItem(AGED_BRIE, EXPIRED_SELL_IN, DEFAULT_QUALITY);
		//invoke
		app.updateQuality();
		//verify
		Item expected=new Item(AGED_BRIE,EXPIRED_SELL_IN-1,DEFAULT_QUALITY+2);
		assertItem(expected, app.items[0]);
	}
	@Test
	public void testUnExpiredAgedBrieQualityDoesNotGoBeyondMaximum() {
		GildedRose app = createGildedRoseWithOneItem(AGED_BRIE, NOT_EXPIRED_SELL_IN, MAXIMUM_QUALITY);
		//invoke
		app.updateQuality();
		//verify
		Item expected=new Item(AGED_BRIE,NOT_EXPIRED_SELL_IN-1,MAXIMUM_QUALITY);
		assertItem(expected, app.items[0]);
	}

}