package hu.tb.util;

import hu.tb.i18n.I18N;

import java.util.Locale;

/**
 * Created by Tivadar Bocz on 2016.11.08..
 */
public class I18NText {

    /**
     * This method will produce an internalizated text based on the localization of the UI
     *
     * @param key    KeyValue example: "lbl.start"
     * @param locale Localization example: Locale.en
     * @param i18n
     * @return the internalizated text
     */
    public static String I18NText(String key, Locale locale, I18N i18n) {
        return i18n.getWithLocale(key, locale, null);
    }
}
