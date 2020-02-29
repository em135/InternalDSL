package metamodel;

import java.util.ArrayList;
import java.util.List;

public class EntityList {

    private List<Entity> entities;
    private String type;

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

    public String getType() {
        return type;
    }

}
