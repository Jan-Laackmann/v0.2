package de.unistuttgart.iste.ese.api.assignee

import java.util.List;
import javax.annotation.PostConstruct;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class AssigneeController {

    @Autowired
    private AssigneeRepository assigneeRepository;

    
    @PostConstruct
    public void init() {

        // check if DB is empty
        long numberOfAssignees = assigneeRepository.count();
        if (numberOfAssignees == 0) {
            
            Assignee assignee1 = new Assignee("Ana Cristina", "Franco da Silva", "ana-cristina.franco-da-silva@iste.uni-stuttgart.de");
            assigneeRepository.save(assignee1);

            Assignee assignee2 = new Assignee("Justus", "Bogner", 
                    "justus.bogner@iste.uni-stuttgart.de");
            assigneeRepository.save(assignee2);
        }
    }

    // get all users
    @GetMapping("/assignees")
    public List<Assignee> getAssignees() {
        List<Assignee> allAssignees = (List<Assignee>) assigneeRepository.findAll();
        return allAssignees;
    }

    // get a single user
    @GetMapping("/assignees/{id}")
    public Assignee getAssignee(@PathVariable("id") long id) {

        Assignee searchedAssignee = assigneeRepository.findById(id);
        if (searchedAssignee != null) {
            return searchedAssignee;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("Assignee with ID %s not found!", id));
    }

    // create a user
    @PostMapping("/assignees")
    @ResponseStatus(HttpStatus.CREATED)
    public Assignee createAssignee(@Valid @RequestBody Assignee requestBody) {
        Assignee assignee = new Assignee(requestBody.getName(), requestBody.getId(), requestBody.getPrename(),
                requestBody.getEmailAdress);
        Assignee savedAssignee = assigneeRepository.save(assignee);
        return savedAssignee;
    }

    // update a user
    @PutMapping("/assignees/{id}")
    public Assignee updateAssignee(@PathVariable("id") long id, @Valid @RequestBody Assignee requestBody) {
        requestBody.setId(id);
        Assignee assigneeToUpdate = assigneeRepository.findById(id);
        if (assigneeToUpdate != null) {
            Assignee savedAssignee = assigneeRepository.save(requestBody);
            return savedAssignee;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("Assignee with ID %s not found!", id));
    }

    // delete a user
    @DeleteMapping("/assignees/{id}")
    public Assignee deleteAssignee(@PathVariable("id") long id) {

        Assignee assigneeToDelete = assigneeRepository.findById(id);
        if (assigneeToDelete != null) {
            assigneeRepository.deleteById(id);
            return assigneeToDelete;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("Assignee with ID %s not found!", id));
    }
}
