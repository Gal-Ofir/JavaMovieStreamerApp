package ofir.gal.app.movie;

import lombok.SneakyThrows;
import ofir.gal.app.fs.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
@PropertySource(value = "classpath:movie.properties")
public class MovieService {

    @Value("${movies.directory}")
    private String moviesDirectory;

    private FileService fileService;

    @Autowired
    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }

    @SneakyThrows
    List<Movie> getMovies() {
        List<String> movieList = fileService.getFilesInPath(moviesDirectory);
        List<Movie> movies = new ArrayList<>();

        for(String movieFromString : movieList) {
            movies.add(new Movie(Paths.get(moviesDirectory,movieFromString).toString()));
        }

        return movies;

    }

    @SneakyThrows
     void addMovieToDirectory(String movieName, InputStream inputStream) {
        fileService.addFileToPath(moviesDirectory, movieName, inputStream);
    }

    @SneakyThrows
    Movie getMovieByName(String movieName) {
        return new Movie(fileService.getFileInPath(moviesDirectory, movieName).getAbsolutePath());
    }

}
