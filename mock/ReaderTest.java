package mock;

import org.junit.Test;
import java.io.IOException;
import static org.junit.Assert.*;

/**
 * Created by Asiman. Crested on 12.01.2018.
 */
public class ReaderTest {
    private DataReaderRepository dataReaderRepository = new DataReaderRepository();

    @Test
    public void testGetDataFromInputFile() throws IOException {
        assertTrue(dataReaderRepository.getDataFromFile("input.txt").size() > 0);
    }
}