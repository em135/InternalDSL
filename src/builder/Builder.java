package builder;

import executor.Model;
import metamodel.Attribute;
import metamodel.Entity;
import metamodel.EntityList;

import java.util.Stack;

public abstract class Builder {

    private Stack<Object> entities = new Stack<>();

    public Builder entity(String type) {
        Entity entity = new Entity(type);
        entities.add(entity);
        return this;
    }

    public Builder attribute(String name, String columnName, Object type) {
        ((Entity) entities.lastElement()).addAttribute(new Attribute(name, columnName, type));
        return this;
    }

    public Builder entityList(String type) {
        EntityList entityList = new EntityList(type);
        entities.add(entityList);
        return this;
    }

    public Builder end() {
        if (entities.size() == 1) {
            return this;
        }

        Object entity = entities.pop();
        Object currentType = entities.peek();

        if (entity instanceof Entity) {
            if (currentType instanceof EntityList) {
                ((EntityList) entities.peek()).addEntity((Entity) entity);
            } else if (currentType instanceof Entity) {
                ((Entity) entities.peek()).addEntity((Entity) entity);
            }
        } else if (entity instanceof EntityList) {
            ((Entity) entities.peek()).addEntityList((EntityList) entity);
        }
        return this;
    }

    public Model init(){
        return new Model((Entity) entities.firstElement(), null);
    }

    protected abstract void build();

}
