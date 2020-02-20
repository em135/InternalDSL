package builder;

import metamodel.Attribute;
import metamodel.Entity;
import metamodel.EntityList;

import java.io.File;
import java.util.Stack;

public abstract class Builder {

    private File file;

    private int index;


    private EntityList currentEntityList;

    //private Stack<Entity> entities = new Stack<>();
    // private Stack<EntityList> entityLists = new Stack<>();

    private Stack<Object> entities = new Stack<>();

    // TODO handle error better
    public Builder load(String pathname) {
        file = new File(pathname);
        return this;
    }

    public Builder entity(String type) {
        Entity entity = new Entity(type);


        entities.add(entity);
        //currentType = entity;
        return this;
    }

    public Builder attribute(String columnName, Object type) {
        ((Entity) entities.lastElement()).addAttribute(new Attribute(columnName, type));
        return this;
    }

    public Builder entityList(String type) {
        EntityList entityList = new EntityList();
        entities.add(entityList);
        //currentType = entityList;
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

    public Entity last(){
        return (Entity) entities.firstElement();
    }

    public Model validate() {
        return new Model(file);
    }

    protected abstract void build();

}
