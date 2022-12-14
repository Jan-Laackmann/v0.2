package de.unistuttgart.iste.ese.api.assignee

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Assignee, Long> {
    Assignee findByName(String name);
    Assignee findByPrename(String prename);
    
    Assignee findByEmail(String email);

    Assignee findById(long id);
}
