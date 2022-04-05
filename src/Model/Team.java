package Model;

public class Team {
    private String name;
    private String webSiteAdress;

    public Team(String name) {
        this.name = name;
    }

    public Team(String name, String webSiteAdress) {
        this.name = name;
        this.webSiteAdress = webSiteAdress;
    }
}
