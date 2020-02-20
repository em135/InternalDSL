import builder.PersonBuilder;
import com.sun.codemodel.internal.JClassAlreadyExistsException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Starter {
    public static void main(String[] args) throws IOException, JClassAlreadyExistsException {
        PersonBuilder personBuilder = new PersonBuilder();
        personBuilder.run();
    }
}
