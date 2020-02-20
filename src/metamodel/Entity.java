package metamodel;

import java.util.ArrayList;
import java.util.List;

public class Entity {

    private String name;
    private List<Attribute> attributes;
    private List<Entity> entities;
    private List<EntityList> entityLists;

    public Entity(String name, List<Attribute> attributes, List<Entity> entities, List<EntityList> entityLists) {
        this.name = name;
        this.attributes = new ArrayList<>();
        this.entities = new ArrayList<>();
        this.entityLists = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }

    public List<EntityList> getEntityLists() {
        return entityLists;
    }

    public void setEntityLists(List<EntityList> entityLists) {
        this.entityLists = entityLists;
    }
}
