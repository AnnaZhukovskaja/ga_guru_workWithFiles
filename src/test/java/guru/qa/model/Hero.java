package guru.qa.model;

public class Hero {

    private String squad;
    private String home;
    private Integer formed;
    private String base;
    private Member member;

    public String getSquad() {
        return squad;
    }

    public void setSquad(String squad) {
        this.squad = squad;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public Integer getFormed() {
        return formed;
    }

    public void setFormed(Integer formed) {
        this.formed = formed;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}