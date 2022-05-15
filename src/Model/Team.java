package Model;

public class Team {
    private String name;
    private String webSiteAdress;
    private Company[] companies;
    private Driver[] drivers;
    private Car[] cars;

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
    public Team(String name , Company company){
        this.name = name;
        this.companies = new Company[1];
        this.companies[0] = company;
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
