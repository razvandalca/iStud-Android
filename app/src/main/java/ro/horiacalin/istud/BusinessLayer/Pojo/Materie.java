package ro.horiacalin.istud.BusinessLayer.Pojo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by horiaacalin on 05/03/2017.
 */

public class Materie implements Serializable{
    private int id;
    private String course_name;
    private String name;
    private int creditNo;
    private String prof_name;
    private String email;
    private String office;
    private List<Grade> grades;

    public Materie(int id, String course_name, String name, int creditNo, String prof_name, String email, String office, List<Grade> grades) {
        this.id = id;
        this.course_name = course_name;
        this.name = name;
        this.creditNo = creditNo;
        this.prof_name = prof_name;
        this.email = email;
        this.office = office;
        this.grades = grades;
    }

    public String getName() {
        return name;
    }

    public int getCreditNo(){
        return creditNo;
    }

    public String getProf_name(){
        return prof_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public void setCreditNo(int creditNo) {
        this.creditNo = creditNo;
    }

    public void setProf_name(String prof_name) {
        this.prof_name = prof_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public void setName(String name) {
        this.name = name;
    }
}