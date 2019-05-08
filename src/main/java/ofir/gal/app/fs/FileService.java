package ofir.gal.app.fs;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileService {

    public List<String> getFilesInPath(String directory) {
        String[] fileNamesArray = new File(directory).list();

        if (fileNamesArray != null) {
            List<String> fileNames = Arrays.asList(fileNamesArray);

            return fileNames.stream()
                    .filter(x -> !new File(Paths.get(directory, x).toUri()).isDirectory()).collect(Collectors.toList());
        }
        return null;
    }

    public File getFileInPath(String directory, String fileName) throws FileNotFoundException {

        File file = new File(Paths.get(directory, fileName).toUri());
        if (file.exists()) {
            return file;
        }
        throw new FileNotFoundException("The request file was not found");
    }

    public void addFileToPath(String directory, String fileName, InputStream inputStream) throws IOException {

        File file = Paths.get(directory, fileName).toFile();
        FileUtils.copyInputStreamToFile(inputStream, file);

    }


}
