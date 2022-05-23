package Model;

public class Team {
    private String name;
    private String webSiteAdress;
    private Company[] companies;
    private Driver[] drivers;
    private Car[] cars;
    private Integer nbCompanies;
    private static final Integer NB_COMPANIES_MAX = 10;

    public Team(String name, String webSiteAdress, Company[] companies) {
        setName(name);
        setWebSiteAdress(webSiteAdress);
        setCompanies(companies);
    }

    public Team(String name) {
        setName(name);
        nbCompanies = 0;
        companies = new Company[NB_COMPANIES_MAX];
    }
    public Team(String name, String webSiteAdress) {
        setName(name);
        setWebSiteAdress(webSiteAdress);
        nbCompanies = 0;
        companies = new Company[NB_COMPANIES_MAX];
    }
    public Team(String name , Company company){
        setName(name);
        nbCompanies = 0;
        companies = new Company[NB_COMPANIES_MAX];
        addCompany(company);
    }

    //region getters & setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setWebSiteAdress(String webSiteAdress) {
        this.webSiteAdress = webSiteAdress;
    }

    public Company[] getCompanies() {
        return companies;
    }
    public void setCompanies(Company[] companies) {
        this.companies = companies;
    }

    //endregion

    public void addCompany(Company company){
        companies[nbCompanies] = company;
        nbCompanies++;
    }

}
