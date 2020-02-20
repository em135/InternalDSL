package metamodel;

import java.util.List;

public class EntityList {

    List<Entity> entities;

    public EntityList(List<Entity> entities) {
        this.entities = entities;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }
}
