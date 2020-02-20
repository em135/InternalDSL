package metamodel;

import java.util.ArrayList;
import java.util.List;

public class Entity {

    private String name;
    private List<Attribute> attributes;
    private List<Entity> entities;
    private List<EntityList> entityLists;

    public Entity(String name) {
        this.name = name;
        this.attributes = new ArrayList<>();
        this.entities = new ArrayList<>();
        this.entityLists = new ArrayList<>();
    }

    public void addAttribute(Attribute attribute) {
        attributes.add(attribute);
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public void addEntityList(EntityList entityList) {
        entityLists.add(entityList);
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

    public List<Entity> getEntities() {
        return entities;
    }

    public List<EntityList> getEntityLists() {
        return entityLists;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "name='" + name + '\'' +
                ", attributes=" + attributes +
                ", entities=" + entities +
                ", entityLists=" + entityLists +
                '}';
    }
}
