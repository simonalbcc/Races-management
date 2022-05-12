package Model;

public class Team {
    private String name;
    private String webSiteAdress;
    private Company[] companies;

    public Team(String name) {
        this.name = name;
    }

    public Team(String name, String webSiteAdress) {
        this.name = name;
        this.webSiteAdress = webSiteAdress;
    }

    public Team(String name, String webSiteAdress, Company[] companies) {
        this.name = name;
        this.webSiteAdress = webSiteAdress;
        this.companies = companies;
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getWebSiteAdress() {
        return webSiteAdress;
    }
    public void setWebSiteAdress(String webSiteAdress) {
        this.webSiteAdress = webSiteAdress;
    }

    public Company[] getCompanies() {
        return companies;
    }
}
