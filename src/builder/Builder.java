package builder;

import metamodel.Attribute;
import metamodel.Entity;
import metamodel.EntityList;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public abstract class Builder {

    private File file;
    private Entity currentEntity;
    private Attribute currentAttribute;
    private EntityList currentEntityList;

    // TODO handle error better
    public Builder load(String pathname) {
        file = new File(pathname);
        return this;
    }

    public Builder entity(String type) {
        Entity entity = new Entity()
        return this;
    }

    public Builder attribute(String columnName, Object type) {
        return this;
    }

    public Builder entityList(String type) {
        return this;
    }

    public Builder end() {
        return this;
    }

    public Model validate() {
        return new Model(file);
    }

    protected abstract void build();

}
