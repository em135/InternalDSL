package builder;

import executor.Model;
import metamodel.Attribute;
import metamodel.Entity;
import metamodel.EntityList;

import java.util.Stack;

public abstract class Builder {

    /**
     * Keeps track of created Entities and EntityLists objects that do not belong to another object yet
     */
    private Stack<Object> objects = new Stack<>();

    /**
     * Creates an Entity object and adds it to the stack
     *
     * @param type the type of the Entity
     * @return this
     */
    public Builder entity(String type) {
        Entity entity = new Entity(type);
        objects.add(entity);
        return this;
    }

    /**
     * Adds a new Attribute to the current Entity
     *
     * @param name       the name of the Attribute
     * @param columnName the name of the column in a CSV file which the Attribute will get its value from
     * @param type       the type of the Attribute
     * @return this
     */
    public Builder attribute(String name, String columnName, Object type) {
        ((Entity) objects.lastElement()).addAttribute(new Attribute(name, columnName, type));
        return this;
    }

    /**
     * Creates an EntityList object and adds it to the stack
     *
     * @param type the type of the EntityList
     * @return this
     */
    public Builder entityList(String type) {
        EntityList entityList = new EntityList(type);
        objects.add(entityList);
        return this;
    }

    /**
     * Use to indicate that an Entity or EntityList is finished.
     * The last element on the stack is removed and added to the element before it
     *
     * @return this
     */
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

    /**
     * Creates a Model based on the last element
     *
     * @return this
     */
    public Model init() {
        return new Model((Entity) objects.firstElement());
    }

    protected abstract void build();
}
