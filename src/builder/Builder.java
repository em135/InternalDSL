package builder;

import executor.Model;
import metamodel.Attribute;
import metamodel.Entity;
import metamodel.EntityList;

import java.util.Stack;

public abstract class Builder {

    private Stack<Object> objects = new Stack<>();

    public Builder entity(String type) {
        Entity entity = new Entity(type);
        objects.add(entity);
        return this;
    }

    public Builder attribute(String name, String columnName, Object type) {
        ((Entity) objects.lastElement()).addAttribute(new Attribute(name, columnName, type));
        return this;
    }

    public Builder entityList(String type) {
        EntityList entityList = new EntityList(type);
        objects.add(entityList);
        return this;
    }

    public Builder end() {
        if (objects.size() == 1) {
            return this;
        }

        Object pop = objects.pop();
        Object peek = objects.peek();

        if (pop instanceof Entity) {
            if (peek instanceof EntityList) {
                ((EntityList) peek).addEntity((Entity) pop);
            } else if (peek instanceof Entity) {
                ((Entity) peek).addEntity((Entity) pop);
            }
        } else if (pop instanceof EntityList) {
            ((Entity) peek).addEntityList((EntityList) pop);
        }
        return this;
    }

    public Model init() {
        return new Model((Entity) objects.firstElement(), null);
    }

    protected abstract void build();
}
