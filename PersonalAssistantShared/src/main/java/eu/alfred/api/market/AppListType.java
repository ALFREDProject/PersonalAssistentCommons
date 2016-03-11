package eu.alfred.api.market;

public enum AppListType {
    DEFAULT(0, "default"),
    INSTALLED(1, "installed"),
    UPDATABLE(2, "updatable"),
    MOST_POPULAR(3, "most popular"),
    LATEST(4, "latest"),
    SEARCH(5, "search");

    private int index;
    private String name;

    private AppListType(int index, String name) {
        this.index = index;
        this.name = name;
    }

    public int getIndex() {
        return this.index;
    }

    public String getName() {
        return this.name;
    }
}