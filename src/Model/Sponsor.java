package Model;

public class Sponsor {
    private Team team;
    private Company company;

    public Sponsor(Team team, Company company) {
        this.team = team;
        this.company = company;
    }

    public Team getTeam() {
        return team;
    }
    public Company getCompany() {
        return company;
    }
}
