package kz.lab;

/** HTML-ден тазарту және әдепкі мән беру көмекші әдістері. */
public class Util {
    public static String esc(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;")
                .replace("\"", "&quot;").replace("'", "&#39;").trim();
    }

    public static String orDefault(String s, String def) {
        return (s == null || s.isBlank()) ? def : esc(s);
    }
}
