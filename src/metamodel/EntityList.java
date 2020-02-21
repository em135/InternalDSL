package metamodel;

import java.util.ArrayList;
import java.util.List;

public class EntityList {

    private List<Entity> entities;

    public EntityList() {
        entities = new ArrayList<>();
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }

    @Override
    public String toString() {
        return "EntityList{" +
                "entities=" + entities +
                '}';
    }
}
