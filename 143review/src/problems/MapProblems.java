package problems;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * See the spec on the website for example behavior.
 */
public class MapProblems {

    /**
     * Returns true if any string appears at least 3 times in the given list; false otherwise.
     */
    public static boolean contains3(List<String> list) {
        // TODO replace this with your code
        // throw new UnsupportedOperationException("Not implemented yet.");

        Map<String, Integer> map = new HashMap<>();
        for (String str: list) {
            if (map.containsKey(str)) {
                if (2 == map.get(str)) {
                    return true;
                }
                map.put(str, map.get(str) + 1);
            } else {
                map.put(str, 1);
            }
        }

        return false;
    }

    /**
     * Returns a map containing the intersection of the two input maps.
     * A key-value pair exists in the output iff the same key-value pair exists in both input maps.
     */
    public static Map<String, Integer> intersect(Map<String, Integer> m1, Map<String, Integer> m2) {
        // TODO replace this with your code
        // throw new UnsupportedOperationException("Not implemented yet.");

        Map<String, Integer> result = new HashMap<String, Integer>();
        for (Map.Entry<String, Integer> entry: m1.entrySet()) {
            if (m2.containsKey(entry.getKey()) && m2.get(entry.getKey()) == entry.getValue()) {
                result.put(entry.getKey(), entry.getValue());
            }
        }
        return result;
    }
}
