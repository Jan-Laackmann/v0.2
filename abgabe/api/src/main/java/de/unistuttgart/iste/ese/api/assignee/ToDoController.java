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
public class ToDoController {

    @Autowired
    private ToDoRepository toDoRepository;

    
    @PostConstruct
    public void init() {

        // check if DB is empty
        long numberOfToDos = toDoRepository.count();
        if (numberOfToDos == 0) {
            
            ToDo todo1 = new ToDo("Ich bin ein Buch", "Das ist ein Buch", true);
            toDoRepository.save(todo1);

            ToDo todo2 = new ToDo("Ich bin kein Buch", "Ich mag kein Buch", false);
            toDoRepository.save(todo2);
        }
    }

    // get all todos
    @GetMapping("/todos")
    public List<ToDo> getToDos() {
        List<ToDo> allToDos = (List<ToDo>) toDoRepository.findAll();
        return allToDos;
    }

    // get a single todo
    @GetMapping("/todos/{id}")
    public ToDo getToDo(@PathVariable("id") long id) {

        ToDo searchedToDo = toDoRepository.findById(id);
        if (searchedToDo != null) {
            return searchedToDo;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
            String.format("ToDos with ID %s not found!", id));
    }

    // create a todos
    @PostMapping("/todos")
    @ResponseStatus(HttpStatus.CREATED)
    public Todo createToDo(@Valid @RequestBody ToDo requestBody) {
        ToDo toDo = new ToDo(requestBody.getTitle(), requestBody.getId(), requestBody.getDescription(),
            requestBody.getFinished());
        ToDo savedToDo = toDoRepository.save(toDo);
        return savedToDo;
    }

    // update a todo
    @PutMapping("/todos/{id}")
    public ToDo updateToDo(@PathVariable("id") long id, @Valid @RequestBody Assignee requestBody) {
        requestBody.setId(id);
        ToDo toDoToUpdate = toDoRepository.findById(id);
        if (toDoToUpdate != null) {
            ToDo savedToDo = toDoRepository.save(requestBody);
            return savedToDo;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
            String.format("Todos with ID %s not found!", id));
    }

    // delete a todo
    @DeleteMapping("/todos/{id}")
    public ToDo deleteToDo(@PathVariable("id") long id) {

        ToDo toDoToDelete = toDoRepository.findById(id);
        if (toDoToDelete != null) {
            toDoRepository.deleteById(id);
            return toDoToDelete;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
            String.format("Todo with ID %s not found!", id));
    }
}
