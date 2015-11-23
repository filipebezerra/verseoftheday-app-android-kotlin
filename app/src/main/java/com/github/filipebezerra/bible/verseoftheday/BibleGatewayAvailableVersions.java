package com.github.filipebezerra.bible.verseoftheday;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * .
 *
 * @author Filipe Bezerra
 * @version #, 23/11/2015
 * @since #
 */
public final class BibleGatewayAvailableVersions {
    private static Map<String, String> VERSIONS;

    static {
        final HashMap<String, String> map = new HashMap<>();
        map.put("en", "ESV");
        map.put("pt", "ARC");
        VERSIONS = Collections.unmodifiableMap(map);
    }

    public static String getVersionByLanguage(String language) {
        if (language != null && VERSIONS.containsKey(language)) {
            return VERSIONS.get(language);
        }
        return null;
    }
}
