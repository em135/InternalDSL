package executor;

import metamodel.Entity;

import java.io.File;

public class Model {

    private Entity entity;
    private File file;

    public Model(Entity entity) {
        this.entity = entity;
    }

    public Entity getEntity() {
        return entity;
    }

    public File getFile() {
        return file;
    }


    /**
     * Creates a new File and uses an Executor to interpret the file
     *
     * @param filepath the path of the CSV file
     */
    public void load(String filepath) {
        this.file = new File(filepath);
        new Executor().toJSON(this);
    }
}
