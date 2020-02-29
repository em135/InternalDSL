package executor;

import metamodel.Entity;

import java.io.File;

public class Model {

    private Entity entity;
    private File file;

    public Model(Entity entity, File file) {
        this.entity = entity;
        this.file = file;
    }

    public Entity getEntity() {
        return entity;
    }

    public File getFile() {
        return file;
    }

    public void load(String filepath) {
        this.file = new File(filepath);
        new Executor().toJSON(this);
    }
}
