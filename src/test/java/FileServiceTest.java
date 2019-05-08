import lombok.SneakyThrows;
import ofir.gal.app.fs.FileService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = FileService.class)
@TestPropertySource("classpath:movie.test.properties")
public class FileServiceTest {

    @Autowired
    private FileService fileService;

    @Value("${test.dir}")
    private String DIRECTORY;

    @Value("${test.dir.dir.in.dir}")
    private String DIR_IN_DIRECTORY;

    @Value("${test.filename}")
    private String FILE_NAME;


    @Test
    public void getAllFilesInPathTest() {
        List<String> fileNames = fileService.getFilesInPath(DIRECTORY);
        Assert.assertNotNull(fileNames);
        Assert.assertFalse(fileNames.contains(DIR_IN_DIRECTORY));

    }

    @Test
    public void getFileInPathTest() {

        try {
            File fileFromDir = fileService.getFileInPath(DIRECTORY, FILE_NAME);
            Assert.assertEquals(fileFromDir.getName(), FILE_NAME);
        }
        catch (FileNotFoundException f) {
            Assert.fail("File not found in directory");
        }
    }

    @Test
    @SneakyThrows
    public void addFileToPathTest() {
        fileService.addFileToPath(DIRECTORY, "test.test", new ByteArrayInputStream(new byte[] {1,2,3,4}));

        File fileFromService = fileService.getFileInPath(DIRECTORY, "test.test");

        Assert.assertNotNull(fileFromService);

        fileFromService.deleteOnExit();


    }

}
