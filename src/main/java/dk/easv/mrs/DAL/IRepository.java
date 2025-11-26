package dk.easv.mrs.DAL;

// Project imports

// Java imports
import java.util.List;

/**
 * Basic and generic CRUD operations
 */
public interface IRepository<T> {

    List<T> getAll() throws Exception;
    T create(T objectToBeCreated) throws Exception;
    void update(T objectToUpdated) throws Exception;
    void delete(T objectToBeDeleted) throws Exception;
}
