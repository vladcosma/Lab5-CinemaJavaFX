package Domain;

import java.util.Objects;

public abstract class Entity {

    private String id;

    Entity(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity entity = (Entity) o;
        return Objects.equals(id, entity.id);
    }

    public String getId() {
        return id;
    }
}