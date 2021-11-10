package dataart.vrn.javaschool.NewsArticles.entities.tags;

public enum Tag {
    SPORT("Sports"),
    FINANCE("Finances"),
    TRAVEL("Travel"),
    POLITICS("Politics"),
    CULTURE("Culture");

    public String code;

    Tag (String code) {
        this.code = code;
    }

    public static Tag fromStringToTag(String s) {
        if (s.equals("Sports")) return SPORT;
        if (s.equals("Finances")) return FINANCE;
        if (s.equals("Travel")) return TRAVEL;
        if (s.equals("Politics")) return POLITICS;
        return CULTURE;
    }

    public String getName() {
        return code;
    }
}
