package Repository;

import Domain.Entity;
import Domain.IValidator;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonFileRepository<T extends Entity> implements IRepository<T> {
    private IValidator<T> validator;
    private String filename;
    private Map<String, T> storage = new HashMap<>();
    private Type type;

    public JsonFileRepository(IValidator<T> validator, String filename, Type type) {
        this.validator = validator;
        this.filename = filename;
        this.type = type;
    }

    private void loadFromFile() {
        storage.clear();
        Gson gson = new Gson();
        try (FileReader in = new FileReader(filename)) {
            try (BufferedReader bufferedReader = new BufferedReader(in)) {
//                String contents = gson.fromJson(bufferedReader.readLine(), Collection<type>);
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    T entity = gson.fromJson(line, type);
                    storage.put(entity.getId(), entity);
                }
            }
        } catch (Exception ex) {
            System.out.println("Load from file error: " + ex.getMessage());
        }
    }

    private void writeToFile() {
        Gson gson = new Gson();
        try (FileWriter out = new FileWriter(filename)) {
            try (BufferedWriter bufferedWriter = new BufferedWriter(out)) {
//                bufferedWriter.write(gson.toJson(storage.values()));
                for (T entity : storage.values()) {
                    bufferedWriter.write(gson.toJson(entity));
                    bufferedWriter.newLine();
                }
            }
        } catch (Exception ex) {
            System.out.println("Write to file error: " + ex.getMessage());
        }
    }

    @Override
    public T getById(String id) {
        loadFromFile();
        return storage.get(id);
    }

    @Override
    public void insert(T entity) {
        loadFromFile();
        if (storage.containsKey(entity.getId())) {
            throw new RepositoryException("A entity with " + entity.getId() + " already exists");
        }
        validator.validate(entity);
        storage.put(entity.getId(), entity);
        writeToFile();
    }

    @Override
    public void update(T entity) {
        loadFromFile();
        if (!storage.containsKey(entity.getId())) {
            throw new RepositoryException(String.format("A entity with %s id doesn't exists!", entity.getId()));
        }
        validator.validate(entity);
        storage.put(entity.getId(), entity);
        writeToFile();
    }

    @Override
    public void remove(String id) {
        loadFromFile();
        if (!storage.containsKey(id)) {
            throw new RuntimeException("There is no entity with the given id to remove.");
        }
        storage.remove(id);
        writeToFile();
    }

    @Override
    public List<T> getAll() {
        loadFromFile();
        return new ArrayList<>(storage.values());
    }
}
