package builder;

import executor.Model;

public class PersonCarBuilder extends Builder {

    @Override
    protected void build() {
        Model model =
            entity("person").
                attribute("first name", "first name", String.class).
                attribute("last name", "last name", String.class).
                entityList("car").
                    entity("car").
                        attribute("model", "car1 model", String.class).
                        attribute("year", "car1 year", int.class).
                        entity("engine").
                            attribute("name", "engine name", String.class).
                            attribute("type", "engine type", String.class).
                            entityList("oil").
                                entity("oil").
                                    attribute("quality", "good oil", String.class).
                                end().
                                entity("oil").
                                    attribute("quality", "bad oil", String.class).
                                end().
                                entity("oil").
                                    attribute("quality", "fine oil", String.class).
                                end().
                            end().
                        end().
                    end().
                    entity("car").
                        attribute("model", "car2 model", String.class).
                        attribute("year", "car2 year", int.class).
                    end().
                end().
                entity("address").
                    attribute("street", "street", String.class).
                    attribute("city", "city", String.class).
                end().
            end().
        init();

        model.load("src/resources/carData1.resources");
        //model.load("src/resources/carData2.resources");
    }

    public static void main(String[] args) {
        new PersonCarBuilder().build();
    }

}
