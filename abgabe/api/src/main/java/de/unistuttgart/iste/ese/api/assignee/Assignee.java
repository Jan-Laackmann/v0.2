package de.unistuttgart.iste.ese.api.assignee

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "assignees")
public class Assignee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @NotNull
    @Size(min = 1)
    @Column(name = "name")
    private String name;
    
    @NotNull
    @Size(min = 1) 
    @ColumnName(name = "prename")
    private String prename;
    
    @NotNull
    @Pattern (regexp = "uni-stuttgart.de")
    @ColumnName(name = "emailAdress")
    private String emailAdress;
    

    public Assignee() {}

    public Assignee(String prename,String name, Sring emailAdress) {
        
        this.prename = prename;
        this.name = name;
        this.emailAdress = emailAdress;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmailAdress(){
        return emailAdress;
    }

    public void setEmailAdress(String emailAdress) {
        this.emailAdress = emailAdress;
    }

    public String getPrename() {
        return prename;
    }

    public void setPrename(String prename) {
        this.prename = prename;
    }

}
