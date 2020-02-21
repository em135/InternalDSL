package builder;

import executor.Model;
import metamodel.Attribute;
import metamodel.Entity;
import metamodel.EntityList;

import java.io.File;
import java.util.Stack;

public abstract class Builder {

    private File file;
    private Stack<Object> entities = new Stack<>();

    // TODO handle error better
    public Builder load(String pathname) {
        file = new File(pathname);
        return this;
    }

    public Builder entity(String type) {
        Entity entity = new Entity(type);
        entities.add(entity);
        return this;
    }

    public Builder attribute(String columnName, Object type) {
        ((Entity) entities.lastElement()).addAttribute(new Attribute(columnName, type));
        return this;
    }

    public Builder entityList(String type) {
        EntityList entityList = new EntityList();
        entities.add(entityList);
        return this;
    }

    public Builder end() {
        if (entities.size() == 1) {
            return this;
        }

        Object entity = entities.pop();
        Object currentType = entities.lastElement();

        if (entity instanceof Entity) {
            if (currentType instanceof EntityList) {
                ((EntityList) entities.lastElement()).addEntity((Entity) entity);
            } else if (currentType instanceof Entity) {
                ((Entity) entities.lastElement()).addEntity((Entity) entity);
            }
        } else if (entity instanceof EntityList) {
            ((Entity) entities.lastElement()).addEntityList((EntityList) entity);
        }
        return this;
    }

    public Model init(){
        return new Model((Entity) entities.firstElement(), file);
    }

    protected abstract void build();

}
