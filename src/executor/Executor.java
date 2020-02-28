package executor;

import metamodel.Attribute;
import metamodel.Entity;
import metamodel.EntityList;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Executor {

    private Map<String, ArrayList<String>> columnValues = new HashMap<>();
    private StringBuilder json = new StringBuilder();
    private Model model;
    private int currentRow;
    private int rowCount;

    public Executor(Model model) {
        this.model = model;
    }

    public void toJson() {
        readCSV();
        String objectName = model.getEntity().getName();
        json.append("{").append("\"").append(objectName).append("\":[");

        for (currentRow = 0; currentRow < rowCount; currentRow++) {
            interpretEntity(model.getEntity());
            checkForExtraComma();
            json.append(",");

        }
        checkForExtraComma();
        json.append("]}");

        System.out.println(json);
    }

    public void readCSV() {
        File file = model.getFile();

        try (Scanner scanner = new Scanner(file)) {
            String[] columnNames = scanner.nextLine().split(",");
            for (String columnName : columnNames) {
                columnValues.put(columnName, new ArrayList<>());
            }
            while (scanner.hasNextLine()) {
                String[] row = scanner.nextLine().split(",");
                for (int i = 0; i < row.length; i++) {
                    columnValues.get(columnNames[i]).add(row[i]);
                }

            }
            rowCount = columnValues.get(columnNames[0]).size();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String getValue(Attribute attribute) {
        String cellValue = columnValues.get(attribute.getColumnName()).get(currentRow);

        String attributeJSON = "\"" + attribute.getName() + "\":";
        if (attribute.getType() != String.class) {
            return attributeJSON + cellValue + ",";
        }
        return attributeJSON + "\"" + cellValue + "\",";
    }

    private void interpretEntity(Entity entity) {
        List<Attribute> attributes = entity.getAttributes();
        List<Entity> entities = entity.getEntities();
        List<EntityList> entityLists = entity.getEntityLists();

        json.append("{");
        interpretAttributes(attributes);
        interpretAttributeEntities(entities);
        interpretEntityLists(entityLists);
        checkForExtraComma();
        json.append("},");
    }

    private void interpretAttributes(List<Attribute> attributes) {
        for (Attribute attribute : attributes) {
            json.append(getValue(attribute));
        }
    }

    private void interpretAttributeEntities(List<Entity> entities) {
        for (Entity e : entities) {
            json.append("\"").append(e.getName()).append("\":");
            interpretEntity(e);
        }
    }

    private void interpretEntityListEntities(List<Entity> entities) {
        for (Entity e : entities) {
            interpretEntity(e);
        }
    }

    private void interpretEntityLists(List<EntityList> entityLists) {
        for (EntityList el : entityLists) {
            json.append("\"").append(el.getType()).append("\":").append("[");
            interpretEntityListEntities(el.getEntities());
            checkForExtraComma();
            json.append("]");
        }
    }

    private void checkForExtraComma() {
        int index = json.length() - 1;
        if (json.lastIndexOf(",") == index) {
            json.replace(index, index + 1, "");
        }
    }

}

