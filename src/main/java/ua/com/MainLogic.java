package ua.com;

import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;

public class MainLogic {

    private final NavigableMap<Integer, Integer> ask = new TreeMap();
    private final NavigableMap<Integer, Integer> bid = new TreeMap();

    public StringBuilder request(List<String> arrays) {
        StringBuilder stringBuilder = new StringBuilder("");
        switch (arrays.get(0)) {
            case "u":
                if (arrays.get(3).equals("bid")) {
                    if (Integer.parseInt(arrays.get(2)) == 0) {
                        bid.remove(Integer.parseInt(arrays.get(1)));
                    } else {
                        bid.put(Integer.parseInt(arrays.get(1)), Integer.parseInt(arrays.get(2)));
                    }
                } else if (arrays.get(3).equals("ask")) {
                    if (Integer.parseInt(arrays.get(2)) == 0) {
                        ask.remove(Integer.parseInt(arrays.get(1)));
                    } else {
                        ask.put(Integer.valueOf(arrays.get(1)), Integer.valueOf(arrays.get(2)));
                    }
                }
                break;
            case "q":
                switch (arrays.get(1)) {
                    case "best_bid" -> {
                        stringBuilder.append(bid.lastEntry().getKey());
                        stringBuilder.append(",");
                        stringBuilder.append(bid.getOrDefault(bid.lastEntry().getKey(), 0));
                        return stringBuilder;
                    }
                    case "best_ask" -> {
                        stringBuilder.append(ask.firstEntry().getKey());
                        stringBuilder.append(",");
                        stringBuilder.append(ask.getOrDefault(ask.firstEntry().getKey(), 0));
                        return stringBuilder;
                    }
                    case "size" -> {
                        int size = Integer.parseInt(arrays.get(2));
                        stringBuilder.append(Math.abs(bid.getOrDefault(size, 0) - ask.getOrDefault(size, 0)));
                        return stringBuilder;
                    }
                }
                break;
            case "o":
                if (arrays.get(1).equals("sell")) {
                    sell(arrays);
                } else if (arrays.get(1).equals("buy")) {
                    buy(arrays);
                }
                break;
        }
        return null;
    }

    private void buy(List<String> arrays) {
        int sharesCount = Integer.parseInt(arrays.get(2));
        while (sharesCount != 0) {
            if (ask.get(ask.firstEntry().getKey()) <= sharesCount) {
                sharesCount -= ask.get(ask.firstEntry().getKey());
                ask.remove(ask.firstEntry().getKey());
            } else {
                ask.put(ask.firstEntry().getKey(), ask.get(ask.firstEntry().getKey()) - sharesCount);
                sharesCount = 0;
            }
        }
    }

    private void sell(List<String> arrays) {
        int sharesCount = Integer.parseInt(arrays.get(2));
        while (sharesCount != 0) {
            if (bid.get(bid.lastEntry().getKey()) <= sharesCount) {
                sharesCount -= bid.get(bid.lastEntry().getKey());
                bid.remove(bid.lastEntry().getKey());
            } else {
                bid.put(bid.lastEntry().getKey(), bid.get(bid.lastEntry().getKey()) - sharesCount);
                sharesCount = 0;
            }
        }
    }
}
