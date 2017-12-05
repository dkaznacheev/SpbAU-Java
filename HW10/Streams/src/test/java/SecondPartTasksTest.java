import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static ru.spbau.streams.SecondPartTasks.*;

public class SecondPartTasksTest {

    @Test
    public void testFindQuotes() throws IOException {
        File tempFile1 = File.createTempFile("prefix-", "-suffix");

        FileWriter writer1 = new FileWriter(tempFile1);
        writer1.write("meow\nmeowed\nmeowing");
        writer1.close();

        File tempFile2 = File.createTempFile("prefix-", "-suffix");

        FileWriter writer2 = new FileWriter(tempFile2);
        writer2.write("meows\nhomeowner\nme\now");
        writer2.close();

        List<String> foundQuotes = findQuotes(
                Arrays.asList(tempFile1.getAbsolutePath(),
                              tempFile2.getAbsolutePath()),
                "meow");

        assertTrue(foundQuotes.contains("meow"));
        assertTrue(foundQuotes.contains("meows"));
        assertTrue(foundQuotes.contains("meowed"));
        assertTrue(foundQuotes.contains("meowing"));
        assertTrue(foundQuotes.contains("homeowner"));

        assertFalse(foundQuotes.contains("me"));
        assertFalse(foundQuotes.contains("ow"));

        tempFile1.deleteOnExit();
        tempFile2.deleteOnExit();
    }

    @Test
    public void testPiDividedBy4() {
        assertTrue(Math.abs(0.785 - piDividedBy4()) < 0.05);
    }

    @Test
    public void testFindPrinter() {
        Map<String, List<String>> library = new HashMap<>();
        library.put("Agniya Barto", Arrays.asList(
                "Наша Таня громко плачет:\n" +
                "Уронила в речку мячик.\n" +
                "- Тише, Танечка, не плачь:\n" +
                "Не утонет в речке мяч.",

                "Нет, напрасно мы решили\n" +
                "Прокатить кота в машине:\n" +
                "Кот кататься не привык -\n" +
                "Опрокинул грузовик."
        ));
        library.put("Jim Morrison", Arrays.asList(
                "Shake dreams from your hair\n" +
                "My pretty child, my sweet one.\n" +
                "Choose the day and\n" +
                "choose the sign of your day\n" +
                "The day’s divinity\n" +
                "First thing you see.\n" +
                "A vast radiant beach\n" +
                "in a cool jeweled moon\n" +
                "Couples naked race down by it’s quiet side\n" +
                "And we laugh like soft, mad children\n" +
                "Smug in the woolly cotton brains of infancy\n" +
                "The music and voices are all around us.\n" +
                "Choose, they croon, the Ancient Ones\n" +
                "The time has come again\n" +
                "Choose now, they croon,\n" +
                "Beneath the moon\n" +
                "Beside an ancient lake\n" +
                "Enter again the sweet forest\n" +
                "Enter the hot dream\n" +
                "Come with us\n" +
                "Everything is broken up and dances."
        ));
        library.put("William Carlos Williams", Arrays.asList(
                "so much depends\n" +
                "upon\n" +
                "\n" +
                "a red wheel\n" +
                "barrow\n" +
                "\n" +
                "glazed with rain\n" +
                "water\n" +
                "\n" +
                "beside the white\n" +
                "chickens."
        ));
        library.put("me", Arrays.asList(""));

        assertEquals("Jim Morrison", findPrinter(library));
    }

    @Test
    public void testCalculateGlobalOrder() {
        Map<String, Integer> shop1 = new HashMap<>();
        shop1.put("1", 10);
        shop1.put("2", 20);

        Map<String, Integer> shop2 = new HashMap<>();
        shop2.put("2", 20);
        shop2.put("3", 30);

        Map<String, Integer> shop3 = new HashMap<>();
        shop3.put("1", 0);
        shop3.put("2", 0);
        shop3.put("3", 0);
        shop3.put("4", 0);

        Map<String, Integer> total = calculateGlobalOrder(Arrays.asList(shop1, shop2, shop3));
        Map<String, Integer> expected = new HashMap<>();
        expected.put("1", 10);
        expected.put("2", 40);
        expected.put("3", 30);
        expected.put("4", 0);
        assertEquals(expected, total);
    }
}