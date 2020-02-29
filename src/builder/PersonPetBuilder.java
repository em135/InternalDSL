package builder;

import executor.Model;

public class PersonPetBuilder extends Builder {

    /**
     * Creates a metamodel instance
     */
    @Override
    protected void build() {
        Model model =
            entity("Person").
                attribute("first name", "first name", String.class).
                attribute("last name", "last name", String.class).
                entityList("Pet").
                    entity("Pet").
                        attribute("type", "pet1 type", String.class).
                        attribute("gender", "pet1 gender", String.class).
                        attribute("age", "pet1 age", int.class).
                        entityList("Color").
                            entity("Color").
                                attribute("color", "pet1 color1", String.class).
                            end().
                            entity("Color").
                                attribute("color", "pet1 color2", String.class).
                            end().
                            entity("Color").
                                attribute("color", "pet1 color3", String.class).
                            end().
                        end().
                    end().
                    entity("Pet").
                        attribute("type", "pet2 type", String.class).
                        attribute("age", "pet2 age", int.class).
                        attribute("age", "pet2 age", int.class).
                        entityList("Color").
                            entity("Color").
                                attribute("color", "pet2 color1", String.class).
                            end().
                            entity("Color").
                                attribute("color", "pet2 color2", String.class).
                            end().
                            entity("Color").
                                attribute("color", "pet2 color3", String.class).
                            end().
                        end().
                    end().
                end().
            end().
        init();

        model.load("src/resources/petData1.resources");
        //model.load("src/resources/petData2.resources");
    }

    public static void main(String[] args) {
        new PersonPetBuilder().build();
    }

}
