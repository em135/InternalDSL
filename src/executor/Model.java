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

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
