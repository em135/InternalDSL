package builder;

import executor.Model;

public class PersonCarBuilder extends Builder {

    /**
     * Creates a metamodel instance
     */
    @Override
    protected void build() {
        Model model =
            entity("Person").
                attribute("first name", "first name", String.class).
                attribute("last name", "last name", String.class).
                entityList("Car").
                    entity("Car").
                        attribute("model", "car1 model", String.class).
                        attribute("year", "car1 year", int.class).
                        entity("Engine").
                            attribute("name", "engine name", String.class).
                            attribute("type", "engine type", String.class).
                            entityList("Oil").
                                entity("Oil").
                                    attribute("quality", "good oil", String.class).
                                end().
                                entity("Oil").
                                    attribute("quality", "bad oil", String.class).
                                end().
                                entity("Oil").
                                    attribute("quality", "fine oil", String.class).
                                end().
                            end().
                        end().
                    end().
                    entity("Car").
                        attribute("model", "car2 model", String.class).
                        attribute("year", "car2 year", int.class).
                    end().
                end().
                entity("Address").
                    attribute("street", "street", String.class).
                    attribute("city", "city", String.class).
                end().
            end().
        init();

        model.load("src/resources/carData1.csv");
        //model.load("src/resources/carData2.csv");
    }

    public static void main(String[] args) {
        new PersonCarBuilder().build();
    }

}
