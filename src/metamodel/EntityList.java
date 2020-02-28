package metamodel;

import java.util.ArrayList;
import java.util.List;

public class EntityList {

    private List<Entity> entities;
    private String type;

    public EntityList() {
        entities = new ArrayList<>();
    }


    public EntityList(String type) {
        entities = new ArrayList<>();
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "EntityList{" +
                "entities=" + entities +
                '}';
    }
}
