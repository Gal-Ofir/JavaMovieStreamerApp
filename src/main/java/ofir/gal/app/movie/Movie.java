package ofir.gal.app.movie;

import lombok.Data;
import lombok.SneakyThrows;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

@Data
public class Movie {

    private String name;
    private String contentType = "plain/text";
    private String extension;
    private String absolutePathToMovie;

    @SneakyThrows
    public Movie(String absolutePathToMovie) {
        String[] split = absolutePathToMovie.split("\\.");
        File movieFile = new File(absolutePathToMovie);
        Path path = movieFile.toPath();
        this.name = movieFile.getName().split("\\.")[0];
        if (Files.probeContentType(path) != null) {
            this.contentType = Files.probeContentType(path);

        }
        this.extension = split[1];
        this.absolutePathToMovie = absolutePathToMovie;
    }

    public String getFullMovieName() {
        return name + "." + extension;
    }
}
