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
@Table(name = "todos")
public class ToDo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @NotNull
    @Size(min = 1)
    @Column(name = "title")
    private String title;

    @NotNull
    @Size(min = 1)
    @ColumnName(name = "description")
    private String description;
    
    @NotNull
    @column(name = "finished", nullable = true)
    private boolean finished;
    
    @NotNull
    @Column(name = "createdDate")
    private long createdDate

    @NotNull
    @Column(name = "dueDate")
    private long dueDate


    @NotNull
    @Column(name = "finishedDate")
    private long finishedDate

  
    public ToDo() {}

    public ToDo(String title, String description, boolean finished) {
        this.title = title;
        this.description = description;
        this.finished = finished;
        this.createdDate = System.currentTimeMillis();
        this.dueDate = System.currentTimeMillis();
        this.finishedDate = System.currentTimeMillis();
        
        
        
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFinished(){
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    

    public long getCreatedDate() {
        return createdDate;
    }

    public long getDueDate() {
        return dueDate;
    }

    public long getFinishedDate() {
        return finishedDate;
    }

    public LinkedList<String> getAssigneeList() {
        return assigneeList;
    }

    public void setAssigneeList(LinkedList<String> assigneeList) {
        this.assigneeList = assigneeList;
    }
}
