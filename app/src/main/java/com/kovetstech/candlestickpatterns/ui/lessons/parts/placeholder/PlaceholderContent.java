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

    public static final List<PlaceholderItem> FUNDAMENTAL_ITEMS = new ArrayList<PlaceholderItem>();
    public static final Map<String, PlaceholderItem> FUNDAMENTAL_ITEM_MAP = new HashMap<String, PlaceholderItem>();

    static  {
        // Add some sample items.
        addBasicsItem(new PlaceholderItem("0", "What Is Technical Analysis", "48 Sec To Read"));
        addBasicsItem(new PlaceholderItem("1", "Key Terms Used In Technical Analysis", "1m 13s To Read"));
        addBasicsItem(new PlaceholderItem("2", "The Limitations Of Technical Analysis", "1m 30s To Read"));
        addBasicsItem(new PlaceholderItem("3", "How To Read Candlestick Patterns", "1m 28s To Read"));
        addBasicsItem(new PlaceholderItem("4","Charting On Different Time Frames", "2m 33s To Read"));
        addBasicsItem(new PlaceholderItem("5","How To Identify Up & Down Trends", "1m 39s To Read"));
        addBasicsItem(new PlaceholderItem("6","Support & Resistance", "2m 55s To Read"));
        addBasicsItem(new PlaceholderItem("7","Basics Test", ""));

        addBullishPatternsItem(new PlaceholderItem("0", "Hammer", "52s To Read"));
        addBullishPatternsItem(new PlaceholderItem("1", "Morning Star", "1m To Read"));
        addBullishPatternsItem(new PlaceholderItem("2", "Three White Soldiers", "42s To Read"));
        addBullishPatternsItem(new PlaceholderItem("3", "Bullish Engulfing", "41s To Read"));
        addBullishPatternsItem(new PlaceholderItem("4", "Bullish Three Line Strike", "1m 14s To Read"));
        addBullishPatternsItem(new PlaceholderItem("5", "Three Inside Up", "55s To Read"));
        addBullishPatternsItem(new PlaceholderItem("6", "Bullish Patterns Test", ""));

        addBearishPatternsItem(new PlaceholderItem("0", "Evening Star", "1m 9s To Read"));
        addBearishPatternsItem(new PlaceholderItem("1", "Three Black Crows", "41s To Read"));
        addBearishPatternsItem(new PlaceholderItem("2", "Hanging Man", "54s To Read"));
        addBearishPatternsItem(new PlaceholderItem("3", "Shooting Star", "58s To Read"));
        addBearishPatternsItem(new PlaceholderItem("4", "Bearish Engulfing", "42s To Read"));
        addBearishPatternsItem(new PlaceholderItem("5", "Bearish Three Line Strike","1m 7s To Read"));
        addBearishPatternsItem(new PlaceholderItem("6", "Three Inside Down", "55s To Read"));
        addBearishPatternsItem(new PlaceholderItem("7", "Bearish Patterns Test", ""));

        addIndicatorsItem(new PlaceholderItem("0", "Moving Averages", "1m 31s To Read"));
        addIndicatorsItem(new PlaceholderItem("1", "On Balance Volume (OBV)", "1m 10s To Read"));
        addIndicatorsItem(new PlaceholderItem("2", "Relative Strength Index (RSI)", "1m To Read"));
        addIndicatorsItem(new PlaceholderItem("3", "Stochastic Oscillator", "1m 35s To Read"));
        addIndicatorsItem(new PlaceholderItem("4", "Bollinger Bands", "1m 41s To Read"));
        addIndicatorsItem(new PlaceholderItem("5", "MACD", "1m 35s To Read"));
        addIndicatorsItem(new PlaceholderItem("6", "Fibonacci Retracement", "1m 25s To Read"));
        addIndicatorsItem(new PlaceholderItem("7", "Ichimoku Cloud", "1m 46s To Read"));
        addIndicatorsItem(new PlaceholderItem("8", "Indicators Test", ""));

        addFundamentalItem(new PlaceholderItem("0", "Fundamental Analysis Basics", "2m 7s To Read"));
        addFundamentalItem(new PlaceholderItem("1", "Financial Statements", "2m 34s To Read"));
        addFundamentalItem(new PlaceholderItem("2", "Company Fundamentals", "1m 41s To Read"));
        addFundamentalItem(new PlaceholderItem("7", "Fundamental Analysis Test", ""));
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
    private static void addFundamentalItem(PlaceholderItem item) {
        FUNDAMENTAL_ITEMS.add(item);
        FUNDAMENTAL_ITEM_MAP.put(item.id, item);
    }

    public static class PlaceholderItem {
        public final String id;
        public final String content;
        public final String read_time;

        public PlaceholderItem(String id, String content, String read_time) {
            this.id = id;
            this.content = content;
            this.read_time = read_time;
        }

        @NonNull
        @Override
        public String toString() {
            return content;
        }
    }
}