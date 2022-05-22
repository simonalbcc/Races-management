package Model;

import java.util.ArrayList;

public class Team {
    private String name;
    private String webSiteAdress;
    private Company[] companies;
    private Driver[] drivers;
    private Car[] cars;
    private Integer nbCompagnies;

    public Team(String name) {
        setName(name);
        nbCompagnies = 0;
    }
    public Team(String name, String webSiteAdress) {
        setName(name);
        setWebSiteAdress(webSiteAdress);
        nbCompagnies = 0;
    }
    public Team(String name, String webSiteAdress, Company[] companies) {
        setName(name);
        setWebSiteAdress(webSiteAdress);
        setCompanies(companies);
        nbCompagnies = companies.length;
    }
    public Team(String name , Company company){
        setName(name);
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
        companies[nbCompagnies] = company;
        nbCompagnies++;
    }

}
