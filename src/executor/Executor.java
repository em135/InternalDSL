package executor;

import metamodel.Attribute;
import metamodel.Entity;
import metamodel.EntityList;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Executor {

    private Map<String, ArrayList<String>> columnValues = new HashMap<>();
    private StringBuilder JSON = new StringBuilder();
    private int currentRow;
    private int rowCount;

    public void toJSON(Model model) {
        readCSV(model.getFile());
        String objectName = model.getEntity().getName();
        JSON.append("{").append("\"").append(objectName).append("\":[");

        for (currentRow = 0; currentRow < rowCount; currentRow++) {
            interpretEntity(model.getEntity());
        }

        checkForExtraComma();
        JSON.append("]}");

        System.out.println(JSON);
    }

    public void readCSV(File file) {
        try (Scanner scanner = new Scanner(file)) {
            String[] columnNames = scanner.nextLine().split(",");
            for (String columnName : columnNames) {
                columnValues.put(columnName, new ArrayList<>());
            }
            while (scanner.hasNextLine()) {
                String[] cells = scanner.nextLine().split(",");
                for (int i = 0; i < cells.length; i++) {
                    columnValues.get(columnNames[i]).add(cells[i]);
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

        JSON.append("{");
        interpretAttributes(attributes);
        interpretAttributeEntities(entities);
        interpretEntityLists(entityLists);
        checkForExtraComma();
        JSON.append("},");
    }

    private void interpretAttributes(List<Attribute> attributes) {
        for (Attribute attribute : attributes) {
            JSON.append(getValue(attribute));
        }
    }

    private void interpretAttributeEntities(List<Entity> entities) {
        for (Entity e : entities) {
            JSON.append("\"").append(e.getName()).append("\":");
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
            JSON.append("\"").append(el.getType()).append("\":").append("[");
            interpretEntityListEntities(el.getEntities());
            checkForExtraComma();
            JSON.append("]");
        }
    }

    private void checkForExtraComma() {
        int index = JSON.length() - 1;
        if (JSON.lastIndexOf(",") == index) {
            JSON.replace(index, index + 1, "");
        }
    }

}

