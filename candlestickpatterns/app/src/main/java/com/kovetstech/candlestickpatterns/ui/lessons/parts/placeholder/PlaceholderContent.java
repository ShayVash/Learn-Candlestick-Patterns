package com.kovetstech.candlestickpatterns.ui.lessons.parts.placeholder;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class PlaceholderContent {

    public static final List<PlaceholderItem> BASICS_ITEMS = new ArrayList<PlaceholderItem>();
    public static final Map<String, PlaceholderItem> BASICS_ITEM_MAP = new HashMap<String, PlaceholderItem>();

    public static final List<PlaceholderItem> BULLISH_PATTERNS_ITEMS = new ArrayList<PlaceholderItem>();
    public static final Map<String, PlaceholderItem> BULLISH_PATTERNS_ITEM_MAP = new HashMap<String, PlaceholderItem>();

    public static final List<PlaceholderItem> BEARISH_PATTERNS_ITEMS = new ArrayList<PlaceholderItem>();
    public static final Map<String, PlaceholderItem> BEARISH_PATTERNS_ITEM_MAP = new HashMap<String, PlaceholderItem>();

    static  {
        // Add some sample items.

        addBasicsItem(new PlaceholderItem("0", "How To Read Camdlestick Patterns"));

        addBullishPatternsItem(new PlaceholderItem("0", "Hammer"));
        addBullishPatternsItem(new PlaceholderItem("1", "Morning Star"));
        addBullishPatternsItem(new PlaceholderItem("2", "Three White Soldiers"));
        addBullishPatternsItem(new PlaceholderItem("3", "Shooting Star"));
        addBullishPatternsItem(new PlaceholderItem("4", "Bullish Engulfing"));
        addBullishPatternsItem(new PlaceholderItem("5", "Bullish Three Line Strike"));
        addBullishPatternsItem(new PlaceholderItem("6", "Three Inside Up"));

        addBearishPatternsItem(new PlaceholderItem("0", "Evening Star"));
        addBearishPatternsItem(new PlaceholderItem("1", "Three Black Crows"));
        addBearishPatternsItem(new PlaceholderItem("2", "Hanging Man"));
        addBearishPatternsItem(new PlaceholderItem("3", "Bearish Engulfing"));
        addBearishPatternsItem(new PlaceholderItem("4", "Bearish Three Line Strike"));
        addBearishPatternsItem(new PlaceholderItem("5", "Three Inside Down"));
    }

    private static void addBasicsItem(PlaceholderItem item) {
        BASICS_ITEMS.add(item);
        BASICS_ITEM_MAP.put(item.id, item);
    }
    private static void addBullishPatternsItem(PlaceholderItem item) {
        BULLISH_PATTERNS_ITEMS.add(item);
        BULLISH_PATTERNS_ITEM_MAP.put(item.id, item);
    }
    private static void addBearishPatternsItem(PlaceholderItem item) {
        BEARISH_PATTERNS_ITEMS.add(item);
        BEARISH_PATTERNS_ITEM_MAP.put(item.id, item);
    }


    public static class PlaceholderItem {
        public final String id;
        public final String content;

        public PlaceholderItem(String id, String content) {
            this.id = id;
            this.content = content;
        }

        @NonNull
        @Override
        public String toString() {
            return content;
        }
    }
}