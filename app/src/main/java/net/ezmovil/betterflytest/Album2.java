package net.ezmovil.betterflytest;

public class Album2 {
    private String name;
    private int episode;
    private String uri;

    public Album2() {
    }

    public Album2(String name, int episode, String uri) {
        this.name = name;
        this.episode = episode;
        this.uri = uri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEpisode() {
        return episode;
    }

    public void setEpisode(int episode) {
        this.episode = episode;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}