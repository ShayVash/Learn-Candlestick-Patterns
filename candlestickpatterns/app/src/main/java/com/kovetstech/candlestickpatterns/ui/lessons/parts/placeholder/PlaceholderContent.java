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

    public static final List<PlaceholderItem> INDICATORS_ITEMS = new ArrayList<PlaceholderItem>();
    public static final Map<String, PlaceholderItem> INDICATORS_ITEM_MAP = new HashMap<String, PlaceholderItem>();

    static  {
        // Add some sample items.

        addBasicsItem(new PlaceholderItem("0", "What Is Technical Analysis"));
        addBasicsItem(new PlaceholderItem("1", "How To Read Candlestick Patterns"));
        addBasicsItem(new PlaceholderItem("2","Charting On Different Time Frames"));
        addBasicsItem(new PlaceholderItem("3","How To Identify Up & Down Trends"));

        addBullishPatternsItem(new PlaceholderItem("0", "Hammer"));
        addBullishPatternsItem(new PlaceholderItem("1", "Morning Star"));
        addBullishPatternsItem(new PlaceholderItem("2", "Three White Soldiers"));
        addBullishPatternsItem(new PlaceholderItem("3", "Bullish Engulfing"));
        addBullishPatternsItem(new PlaceholderItem("4", "Bullish Three Line Strike"));
        addBullishPatternsItem(new PlaceholderItem("5", "Three Inside Up"));

        addBearishPatternsItem(new PlaceholderItem("0", "Evening Star"));
        addBearishPatternsItem(new PlaceholderItem("1", "Three Black Crows"));
        addBearishPatternsItem(new PlaceholderItem("2", "Hanging Man"));
        addBullishPatternsItem(new PlaceholderItem("3", "Shooting Star"));
        addBearishPatternsItem(new PlaceholderItem("4", "Bearish Engulfing"));
        addBearishPatternsItem(new PlaceholderItem("5", "Bearish Three Line Strike"));
        addBearishPatternsItem(new PlaceholderItem("6", "Three Inside Down"));

        addIndicatorsItem(new PlaceholderItem("0", "Moving Averages"));
        addIndicatorsItem(new PlaceholderItem("1", "On Balance Volume (OBV)"));
        addIndicatorsItem(new PlaceholderItem("2", "Relative Strength Index (RSI)"));
        addIndicatorsItem(new PlaceholderItem("3", "Stochastic Oscillator"));
        addIndicatorsItem(new PlaceholderItem("4", "Bollinger Bands"));
        addIndicatorsItem(new PlaceholderItem("5", "MACD"));
        addIndicatorsItem(new PlaceholderItem("6", "Fibonacci Retracement"));
        addIndicatorsItem(new PlaceholderItem("7", "Ichimoku Cloud"));

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
    private static void addIndicatorsItem(PlaceholderItem item) {
        INDICATORS_ITEMS.add(item);
        INDICATORS_ITEM_MAP.put(item.id, item);
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