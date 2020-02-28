package metamodel;

public class Attribute {

    private String name;
    private String columnName;
    private Object type;

    public Attribute(String name, String columnName, Object type) {
        this.name = name;
        this.columnName = columnName;
        this.type = type;
    }

    public String getColumnName() {
        return columnName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public Object getType() {
        return type;
    }

    public void setType(Object type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Attribute{" +
                "columnName='" + columnName + '\'' +
                ", type=" + type +
                '}';
    }
}
