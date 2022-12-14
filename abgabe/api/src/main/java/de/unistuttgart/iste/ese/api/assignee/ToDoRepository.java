package de.unistuttgart.iste.ese.api.assignee

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<ToDo, Long> {
    ToDo findByTitle(String title);
    ToDo findByDescription(String description);

    ToDo findByFinished(boolean finished);

    ToDo findById(long id);
    
    ToDo findByCreatedDate(long createdDate);
    ToDo findByDueDate(long dueDate);
    ToDo findByFinishedDate(long finishedDate);
    
    
}
